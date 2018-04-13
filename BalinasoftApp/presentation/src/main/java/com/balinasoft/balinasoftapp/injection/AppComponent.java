package com.balinasoft.balinasoftapp.injection;


import com.balinasoft.balinasoftapp.presenters.LoginPresenter;
import com.balinasoft.balinasoftapp.presenters.RegisterPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(LoginPresenter loginPresenter);
    void inject(RegisterPresenter registerPresenter);
}