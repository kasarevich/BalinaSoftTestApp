package com.balinasoft.domain.interactors;

import android.util.Log;

import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.PhotosRepository;

import javax.inject.Inject;
import io.reactivex.Completable;

public class RemovePhotoUseCase extends BaseUseCase{

    private PhotosRepository mPhotosRepository;

    @Inject
    public RemovePhotoUseCase(PostExecutionThread postExecutionThread, PhotosRepository photosRepository) {
        super(postExecutionThread);
        mPhotosRepository = photosRepository;
    }

    public Completable remove (int id){
        return mPhotosRepository
                .deletePhoto(id)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
