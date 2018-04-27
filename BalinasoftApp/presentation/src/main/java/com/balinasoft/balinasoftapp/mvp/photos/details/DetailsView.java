package com.balinasoft.balinasoftapp.mvp.photos.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface DetailsView extends MvpView{
    void initViews(Long date, String url);
    @StateStrategyType(SkipStrategy.class)
    void showMessage(String message);
    @StateStrategyType(SkipStrategy.class)
    void showDeleteDialog(String msg, int id);
    void back();
}
