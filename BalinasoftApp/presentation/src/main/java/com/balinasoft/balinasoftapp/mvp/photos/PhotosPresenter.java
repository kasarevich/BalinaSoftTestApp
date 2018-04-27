package com.balinasoft.balinasoftapp.mvp.photos;


import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.mvp.photos.details.DetailsFragment;
import com.balinasoft.domain.entity.Photo;
import com.balinasoft.domain.interactors.GetPhotosUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class PhotosPresenter extends BasePresenter<PhotosView>{

    @Inject
    GetPhotosUseCase mGetPhotosUseCase;



    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    public void onItemSelected(Photo photo) {
        //FIXME when db will be created, fix with getById method
        getViewState().navigateToDetailActivity(DetailsFragment.newInstance(photo.getUrl(), photo.getDate(), photo.getId()));
    }

    public void getNextPhotos(String offset) {
        getViewState().showProgress();
        mGetPhotosUseCase.get(offset).subscribe(new Observer<List<Photo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<Photo> photos) {
                getViewState().addItems(photos);
                getViewState().hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideProgress();
                getViewState().showMessage("ERROR" + e.toString());
            }

            @Override
            public void onComplete() {
                getViewState().hideProgress();
            }
        });
    }

}
