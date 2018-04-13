package com.balinasoft.domain.interactors;

import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class SignInUseCase extends BaseUseCase {

    private LoginRepository mLoginRepository;

    @Inject
    public SignInUseCase(PostExecutionThread postExecutionThread, LoginRepository loginRepository) {
        super(postExecutionThread);
        this.mLoginRepository = loginRepository;
    }

    public Observable<Integer> get (String login, String password){
        return mLoginRepository.signIn(login, password).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }

}
