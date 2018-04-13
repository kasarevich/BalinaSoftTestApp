package com.balinasoft.data.net;


import com.balinasoft.data.entity.LoggedInUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RestService {

    private RestApi mRestApi;

    @Inject
    public RestService(RestApi restApi) {
        mRestApi = restApi;
    }

    public Observable<LoggedInUser> signIn (String login, String password){
        return mRestApi.logIn(login, password);
    }

    public Observable<LoggedInUser> signUp (String login, String password){
        return mRestApi.register(login, password);
    }







}
