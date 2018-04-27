package com.balinasoft.balinasoftapp.injection;


import com.balinasoft.balinasoftapp.mvp.auth.LoginPresenter;
import com.balinasoft.balinasoftapp.mvp.auth.RegisterPresenter;
import com.balinasoft.balinasoftapp.mvp.photos.PhotosPresenter;
import com.balinasoft.balinasoftapp.mvp.photos.details.DetailsPresenter;
import com.balinasoft.balinasoftapp.mvp.photos.details.DetailsView;
import com.balinasoft.balinasoftapp.mvp.screen_main.NaviPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {


    void inject(LoginPresenter loginPresenter);
    void inject(RegisterPresenter registerPresenter);
    void inject(PhotosPresenter photosPresenter);
    void inject(NaviPresenter naviPresenter);
    void inject(DetailsPresenter detailsPresenter);
}