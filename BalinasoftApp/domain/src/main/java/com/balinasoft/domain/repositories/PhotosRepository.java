package com.balinasoft.domain.repositories;

import com.balinasoft.domain.entity.Photo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface PhotosRepository {

    Observable<List<Photo>> getPhotos(String page);
    String getUsername();
    Completable deletePhoto(int id);
}
