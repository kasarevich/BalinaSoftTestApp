package com.balinasoft.data.net;


import com.balinasoft.data.entity.auth.LoggedInUser;
import com.balinasoft.data.entity.auth.User;
import com.balinasoft.data.entity.images.DeleteResponce;
import com.balinasoft.data.entity.images.Image;
import com.balinasoft.data.entity.images.RootImage;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.http.Path;

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
                .compose(mErrorTransformers.parseHttpError());
    }

    public Observable<LoggedInUser> signUp (String login, String password){
        return mRestApi
                .register("application/json", "application/json", new User(login, password))
                .compose(mErrorTransformers.<LoggedInUser> parseHttpError());
    }

    public Observable<RootImage> getImages (String page, String token){
        return mRestApi
                .getImages("*/*", token, page);
    }

    public Observable<DeleteResponce> removeImage (String token, int id){
        return mRestApi
                .deleteImage("*/*", token, id);
    }







}
