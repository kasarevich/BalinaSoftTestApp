package com.balinasoft.balinasoftapp.presenters;


import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.utils.Validation;
import com.balinasoft.balinasoftapp.views.LoginView;
import com.balinasoft.data.errors.Error;
import com.balinasoft.domain.interactors.SignInUseCase;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    SignInUseCase mSignInUseCase;


    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public void checkLogin(String login, String pas) {
        getViewState().startSignIn();
        if (!Validation.checkLogin(login)) {
            getViewState().finishSignIn();
            getViewState().showDialog("Incorrect email", "Error");
        } else if (!Validation.checkPassword(pas)) {
            getViewState().finishSignIn();
            getViewState().showDialog("Passwords can not be shorter than 8 characters", "Error");
        } else {
            mSignInUseCase.get(login, pas).subscribe(new CompletableObserver() {
                @Override
                public void onSubscribe(Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onError(Throwable e) {
                    String title = "Error";
                    String message = "Unknown error";
                    if (e instanceof Error) {
                        message = ((Error) e).getMyError().toString();
                    }
                    getViewState().finishSignIn();
                    getViewState().showDialog(message, title);
                }

                @Override
                public void onComplete() {
                    getViewState().finishSignIn();
                    getViewState().showMessageToUser("U R loggined");
                    getViewState().startNaviActivity();
                }
            });
        }
    }

}


