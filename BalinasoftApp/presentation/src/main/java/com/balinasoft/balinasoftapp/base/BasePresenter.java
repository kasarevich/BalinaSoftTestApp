package com.balinasoft.balinasoftapp.base;


import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View> {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BasePresenter() {
        createInject();
    }

    public abstract void createInject();

    protected void unsubscribeOnDestroy(@NonNull Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
