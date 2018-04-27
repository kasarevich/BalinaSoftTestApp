package com.balinasoft.data.repositories;

import android.content.Context;

import com.balinasoft.data.entity.auth.LoggedInUser;
import com.balinasoft.data.net.RestService;
import com.balinasoft.data.utils.AuthUtils;
import com.balinasoft.domain.repositories.LoginRepository;


import javax.inject.Inject;

import io.reactivex.Completable;

public class LoginRepositoryImpl implements LoginRepository{
    private RestService restService;
    private Context context;

    @Inject
    public LoginRepositoryImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Completable signIn (String login, String password){
           AuthUtils.saveUsername(context, login);
           return Completable.fromObservable(restService.signIn(login, password).map(this::map));
    }

    @Override
    public Completable signUp (String login, String password){
        AuthUtils.saveUsername(context, login); //Fixme юскейсом грузить в навиг вью
        return Completable.fromObservable(restService.signUp(login, password).map(this::map));
    }

    private LoggedInUser map(LoggedInUser user) {
        AuthUtils.saveToken(context, user.getUserData().getToken()); // сохранение токена
        return user;
    }

}
