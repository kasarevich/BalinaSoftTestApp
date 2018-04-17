package com.balinasoft.data.net;

import com.balinasoft.data.errors.ErrorResponse;
import com.balinasoft.data.errors.Error;
import com.balinasoft.data.errors.ErrorType;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.Response;

public class ErrorTransformers {

    private Gson gson;

    @Inject
    public ErrorTransformers(Gson gson){
        this.gson = gson;
    }

    @Singleton
    public <Model> ObservableTransformer<Model, Model> parseHttpError() {

        return new ObservableTransformer<Model, Model>(){

            @Override
            public ObservableSource<Model> apply(Observable<Model> upstream) {

                return upstream.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Model>>() {
                    @Override
                    public ObservableSource<? extends Model> apply(Throwable throwable) throws Exception {
                        if (throwable instanceof SocketTimeoutException) {
                            return Observable.error(new Error(ErrorType.SERVER_NOT_AVAILABLE));
                        } else if (throwable instanceof IOException){
                            return Observable.error(new Error(ErrorType.NO_INTERNET));
                        } else if (throwable instanceof HttpException) {
                            HttpException httpException = (HttpException) throwable;
                            Response response = httpException.response();
                            String bodyError = response.errorBody().string();
                            ErrorResponse errorResponse = gson.fromJson(bodyError, ErrorResponse.class);
                                switch (errorResponse.getError()){
                                    case "security.signin.incorrect":
                                        return Observable.error(new Error(ErrorType.SIGN_IN_INCORRECT));

                                    case "validation-error":
                                        return Observable.error(new Error(ErrorType.VALIDATION_ERROR));

                                    case "security.signup.login-already-use":
                                        return Observable.error(new Error(ErrorType.LOGIN_ALREADY_USE));

                                    default:
                                        return Observable.error(new Error(ErrorType.UNKNOWN));
                                }
                        } else {
                            return Observable.error(new Error(ErrorType.UNKNOWN));
                        }
                    }
                });
            }
        };
    }
}
