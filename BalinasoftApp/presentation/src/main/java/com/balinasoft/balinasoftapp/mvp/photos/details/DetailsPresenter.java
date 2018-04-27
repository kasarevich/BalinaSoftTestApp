package com.balinasoft.balinasoftapp.mvp.photos.details;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.data.errors.Error;
import com.balinasoft.domain.interactors.RemovePhotoUseCase;


import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject
    RemovePhotoUseCase mRemovePhotoUseCase;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public void getViews(String url, Long date){
        getViewState().initViews(date, url);
    }

    public void removeImage(int id) {
        getViewState().showDeleteDialog("Are u really want to delete it?", id);

    }

    public void delete(int id){

        Log.e("presenter", String.valueOf(id));
        mRemovePhotoUseCase.remove(id).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }
            @Override
            public void onError(Throwable e) {
                Log.e("presenter", e.toString());
                String message = "Unknown error";
                if (e instanceof Error) {
                    message = ((Error) e).getMyError().toString();
                }
                getViewState().showMessage(message);
            }

            @Override
            public void onComplete() {
                getViewState().showMessage("deleted");
                getViewState().back();
            }
        });


    }
}
