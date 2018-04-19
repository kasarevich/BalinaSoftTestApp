package com.balinasoft.balinasoftapp.injection;


import com.balinasoft.balinasoftapp.activities.StartActivity;
import com.balinasoft.balinasoftapp.fragments.LoginFragment;
import com.balinasoft.balinasoftapp.fragments.RegisterFragment;
import com.balinasoft.balinasoftapp.presenters.LoginPresenter;
import com.balinasoft.balinasoftapp.presenters.RegisterPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(StartActivity startActivity);
    void inject(LoginPresenter loginPresenter);
    void inject(RegisterPresenter registerPresenter);
}