package com.balinasoft.data.net;


import com.balinasoft.data.entity.LoggedInUser;
import com.balinasoft.data.entity.User;
import com.balinasoft.data.errors.Error;


import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class RestService {

    private RestApi mRestApi;
    private ErrorTransformers mErrorTransformers;

    @Inject
    public RestService(RestApi restApi, ErrorTransformers errorTransformers) {
        mRestApi = restApi;
        mErrorTransformers = errorTransformers;
    }

    public Observable<LoggedInUser> signIn (String login, String password){
        return mRestApi
                .logIn("application/json", "application/json", new User(login, password))
                .compose(mErrorTransformers.<LoggedInUser> parseHttpError());
    }

    public Observable<LoggedInUser> signUp (String login, String password){
        return mRestApi
                .register("application/json", "application/json", new User(login, password))
                .compose(mErrorTransformers.<LoggedInUser> parseHttpError());
    }







}
