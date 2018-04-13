package com.balinasoft.data.repositories;

import android.content.Context;

import com.balinasoft.data.entity.LoggedInUser;
import com.balinasoft.data.net.RestService;
import com.balinasoft.data.utils.AuthUtils;
import com.balinasoft.domain.repositories.LoginRepository;


import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginRepositoryImpl implements LoginRepository{
    private RestService restService;
    private Context context;

    /*
    fixme возможно здесь добавить String token
    если решу делать кнопку выйти, то продумать переход к меню регистрации
    */

    @Inject
    public LoginRepositoryImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public Observable<Integer> signIn (String login, String password){
        return restService.signIn(login, password).map(this::map);
    }

    @Override
    public Observable<Integer> signUp (String login, String password){
        return restService.signUp(login, password).map(this::map);
    }

    private Integer map(LoggedInUser user) {
        Integer response = user.getStatus();
       /* if (response == 200){
            AuthUtils.saveToken(context, user.getUserData().getToken()); // сохранение токена
        }*/
        return response;
    }


}
