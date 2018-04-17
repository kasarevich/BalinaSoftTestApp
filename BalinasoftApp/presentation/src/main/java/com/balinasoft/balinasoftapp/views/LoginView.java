package com.balinasoft.balinasoftapp.views;

import com.arellomobile.mvp.MvpView;


public interface LoginView extends MvpView {

    void getLoginAndPassword();
    void startSignIn();
    void finishSignIn();
    void showMessageToUser(String msg);
    void showDialog(String msg, String title);

}
