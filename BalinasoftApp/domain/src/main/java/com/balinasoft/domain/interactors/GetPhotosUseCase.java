package com.balinasoft.domain.interactors;

import com.balinasoft.domain.entity.Photo;
import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.PhotosRepository;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetPhotosUseCase extends BaseUseCase {

    private PhotosRepository mPhotosRepository;
    @Inject
    public GetPhotosUseCase(PostExecutionThread postExecutionThread, PhotosRepository photosRepository) {
        super(postExecutionThread);
        mPhotosRepository = photosRepository;
    }

    public Observable<List<Photo>> get (String page){
        return mPhotosRepository.getPhotos(page).subscribeOn(threadExecution).observeOn(postExecutionThread);
    }



}
