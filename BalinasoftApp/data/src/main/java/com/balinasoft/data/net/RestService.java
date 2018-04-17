package com.balinasoft.data.net;


import com.balinasoft.data.entity.LoggedInUser;
import com.balinasoft.data.entity.User;


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
        return mRestApi.logIn("application/json", "application/json", new User(login, password));
    }

    public Observable<LoggedInUser> signUp (String login, String password){
        return mRestApi.register("application/json", "application/json", new User(login, password));
    }







}
