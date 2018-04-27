package com.balinasoft.balinasoftapp.mvp.photos;


import android.view.LayoutInflater;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.balinasoft.balinasoftapp.mvp.photos.details.DetailsFragment;
import com.balinasoft.domain.entity.Photo;

import java.util.List;

@StateStrategyType(SkipStrategy.class)
public interface PhotosView extends MvpView {

    void showProgress();
    void hideProgress();
    void navigateToDetailActivity(DetailsFragment fragment);
    void addItems(List<Photo> photos);
    void showMessage(String msg);
}
