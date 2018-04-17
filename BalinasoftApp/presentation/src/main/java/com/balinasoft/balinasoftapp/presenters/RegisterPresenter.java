package com.balinasoft.balinasoftapp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.utils.Validation;
import com.balinasoft.balinasoftapp.views.LoginView;
import com.balinasoft.domain.interactors.SignUpUseCase;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class RegisterPresenter  extends BasePresenter<LoginView> {

    @Inject
    SignUpUseCase mSignUpUseCase;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }


    public void checkFields(String login, String pass1, String pass2){

        getViewState().startSignIn();
        if (!Validation.checkLogin(login)){
            getViewState().showLoginError("Incorrect email");
        }else if(!Validation.checkForRepeat(pass1, pass2)){
            getViewState().showLoginError("Passwords do not coincide");
        }else if(!Validation.checkPassword(pass1)){
            getViewState().showLoginError("Passwords can't be shorter than 8 characters");
        }else {

            mSignUpUseCase.get(login, pass1).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().finishSignIn();
                    getViewState().showMessageToUser(e.toString());
                }

                @Override
                public void onComplete() {
                    getViewState().finishSignIn();
                    getViewState().showMessageToUser("ur registered");

                }
            });
        }



    }

}
