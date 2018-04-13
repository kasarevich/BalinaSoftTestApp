package com.balinasoft.domain.repositories;

import io.reactivex.Observable;

public interface LoginRepository {

    Observable<Integer> signIn (String login, String password);

    Observable<Integer> signUp (String login, String password);

}
