package com.balinasoft.balinasoftapp.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.balinasoft.balinasoftapp.executor.UIThread;
import com.balinasoft.data.net.RestApi;
import com.balinasoft.data.net.RestService;
import com.balinasoft.data.repositories.LoginRepositoryImpl;
import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.LoginRepository;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private Context context;
    public AppModule(@NonNull Context context) {
        this.context = context;
    }


    @Provides
    @Singleton
    public Context getContext() {
        return context;
    }

    @NonNull
    @Provides
    @Singleton
    public PostExecutionThread getUiThread() {
        return new UIThread();
    }

    @NonNull
    @Provides
    @Singleton
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://junior.balinasoft.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    @NonNull
    @Provides
    @Singleton
    public RestApi getRestApi(Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
    @NonNull
    @Provides
    public LoginRepository getLoginRepository(Context context, RestService restService) {
        return new LoginRepositoryImpl(context, restService);
    }

}