package com.balinasoft.domain.repositories;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface LoginRepository {

    Completable signIn (String login, String password);

    Completable signUp (String login, String password);

}
