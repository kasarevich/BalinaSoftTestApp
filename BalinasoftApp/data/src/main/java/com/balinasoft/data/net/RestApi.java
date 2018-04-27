package com.balinasoft.data.net;

import com.balinasoft.data.entity.auth.LoggedInUser;
import com.balinasoft.data.entity.auth.User;
import com.balinasoft.data.entity.images.DeleteResponce;
import com.balinasoft.data.entity.images.Image;
import com.balinasoft.data.entity.images.RootImage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {
    @POST("api/account/signin")
    Observable<LoggedInUser> logIn(@Header("Accept") String accept,
                                   @Header("Content-Type") String acceptContent,
                                   @Body User user);

    @POST("api/account/signup")
    Observable<LoggedInUser> register(@Header("Accept") String accept,
                                      @Header("Content-Type") String acceptContent,
                                      @Body User user);

    @GET("api/image")
    Observable<RootImage> getImages(@Header("Accept") String accept,
                                    @Header("Access-Token") String token,
                                    @Query("page") String page);

    @DELETE("api/image/{id}")
    Observable<DeleteResponce> deleteImage(@Header("Accept") String accept,
                                           @Header("Access-Token") String token,
                                           @Path("id") int id);


}
