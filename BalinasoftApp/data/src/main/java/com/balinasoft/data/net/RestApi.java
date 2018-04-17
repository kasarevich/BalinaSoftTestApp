package com.balinasoft.data.net;

import com.balinasoft.data.entity.LoggedInUser;
import com.balinasoft.data.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApi {
    @POST("api/account/signin")
    Observable<LoggedInUser> logIn(@Header("Accept") String accept, @Header("Content-Type") String acceptContent, @Body User user);

    @POST("api/account/signup")
    Observable<LoggedInUser> register(@Header("Accept") String accept, @Header("Content-Type") String acceptContent, @Body User user);
}
