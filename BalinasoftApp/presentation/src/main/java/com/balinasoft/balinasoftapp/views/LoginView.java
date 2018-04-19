package com.balinasoft.balinasoftapp.views;

import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.MvpView;

@StateStrategyType(SkipStrategy.class)
public interface LoginView extends MvpView {

    void getLoginAndPassword();
    void startSignIn();
    void finishSignIn();
    void showMessageToUser(String msg);
    void showDialog(String msg, String title);
    void startNaviActivity();

}
