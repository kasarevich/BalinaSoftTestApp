package com.balinasoft.balinasoftapp.presenters;


import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.utils.Validation;
import com.balinasoft.balinasoftapp.views.LoginView;
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
        if (!Validation.checkLogin(login)){
            getViewState().showLoginError("Incorrect email");
        }else if(!Validation.checkPassword(pas)){
            getViewState().showLoginError("Passwords can not be shorter than 8 characters");
        }else {
           mSignInUseCase.get(login, pas).subscribe(new CompletableObserver() {
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
                   getViewState().showMessageToUser("U are loggined");
               }
           });
        }
    }


}
