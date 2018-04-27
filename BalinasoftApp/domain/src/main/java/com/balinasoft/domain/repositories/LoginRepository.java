package com.balinasoft.domain.repositories;

import com.balinasoft.domain.entity.Photo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface LoginRepository {

    Completable signIn (String login, String password);

    Completable signUp (String login, String password);

}
