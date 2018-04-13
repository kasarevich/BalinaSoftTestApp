package com.balinasoft.data.net;

import com.balinasoft.data.entity.LoggedInUser;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface RestApi {

    @POST("v1/api/account/signin")
    Observable<LoggedInUser> logIn(@Field("login") String login, @Field("password") String password);

    @POST("v1/api/account/signup")
    Observable<LoggedInUser> register(@Field("login") String login, @Field("password") String password);

}
