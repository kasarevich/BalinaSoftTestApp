package com.balinasoft.balinasoftapp.mvp.auth;

import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.utils.Validation;
import com.balinasoft.balinasoftapp.mvp.auth.LoginView;
import com.balinasoft.data.errors.Error;
import com.balinasoft.domain.interactors.SignUpUseCase;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
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
            getViewState().finishSignIn();
            getViewState().showDialog("Incorrect email", "Error");
        }else if(!Validation.checkForRepeat(pass1, pass2)){
            getViewState().finishSignIn();
            getViewState().showDialog("Passwords do not coincide", "Error");
        }else if(!Validation.checkPassword(pass1)){
            getViewState().finishSignIn();
            getViewState().showDialog("Passwords can't be shorter than 8 characters", "Error");
        }else {
            mSignUpUseCase.get(login, pass1).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onError(Throwable e) {
                    String title = "Error";
                    String message = "Unknown error";
                    if(e instanceof Error){
                        message = ((Error) e).getMyError().toString();
                    }
                    getViewState().finishSignIn();
                    getViewState().showDialog(message, title);
                }

                @Override
                public void onComplete() {
                    getViewState().finishSignIn();
                    getViewState().showMessageToUser("U R registered");
                    getViewState().startNaviActivity();
                }
            });
        }

    }

}
