package com.balinasoft.domain.interactors;

import com.balinasoft.domain.executor.PostExecutionThread;
import com.balinasoft.domain.repositories.PhotosRepository;

import javax.inject.Inject;

public class GetUsernameUseCase extends BaseUseCase {

    private PhotosRepository mPhotosRepository;

    @Inject
    public GetUsernameUseCase(PostExecutionThread postExecutionThread, PhotosRepository photosRepository) {
        super(postExecutionThread);
        mPhotosRepository = photosRepository;
    }

    public String getUsername(){
        return mPhotosRepository.getUsername();
    }
}
