package com.balinasoft.domain.interactors;

import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class SignUpUseCase extends BaseUseCase {
    private LoginRepository mLoginRepository;

    @Inject
    public SignUpUseCase(PostExecutionThread postExecutionThread, LoginRepository loginRepository) {
        super(postExecutionThread);
        this.mLoginRepository = loginRepository;
    }

    public Completable get (String login, String password){
        return mLoginRepository.signUp(login, password).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }

}