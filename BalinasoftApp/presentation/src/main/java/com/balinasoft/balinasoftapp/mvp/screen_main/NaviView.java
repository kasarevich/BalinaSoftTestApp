package com.balinasoft.balinasoftapp.mvp.screen_main;

import android.support.v4.app.Fragment;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(SkipStrategy.class)
public interface NaviView extends MvpView {
    public void setHeaderTitle(String title);
    public void navigateToFragment (Fragment fragment);
}
