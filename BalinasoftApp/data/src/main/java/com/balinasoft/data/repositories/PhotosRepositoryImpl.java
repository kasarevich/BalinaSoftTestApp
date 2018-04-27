package com.balinasoft.data.repositories;

import android.content.Context;
import android.util.Log;

import com.balinasoft.data.entity.images.Image;
import com.balinasoft.data.entity.images.RootImage;
import com.balinasoft.data.net.RestService;
import com.balinasoft.data.utils.AuthUtils;
import com.balinasoft.domain.entity.Photo;
import com.balinasoft.domain.repositories.PhotosRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class PhotosRepositoryImpl implements PhotosRepository {

    private RestService restService;
    private Context context;

    @Inject
    public PhotosRepositoryImpl(Context context, RestService restService) {
        this.context = context;
        this.restService = restService;
    }

    @Override
    public String getUsername() {
        return AuthUtils.getUsername(context);
    }

    @Override
    public Observable<List<Photo>> getPhotos(String page) {
        return restService.getImages(page, AuthUtils.getToken(context)).map((RootImage root) -> {
            List<Image> images = root.getData();
            List<Photo> photos = new ArrayList<>();

            for (Image image : images) {
                Log.e("Image entities", image.getUrl());
                Photo photo = new Photo();
                photo.setId(image.getId());
                photo.setDate(image.getDate());
                photo.setUrl(image.getUrl());
                photos.add(photo);
            }
            return photos;
        });
    }


    @Override
    public Completable deletePhoto(int id) {
        return Completable.fromObservable(restService.removeImage( AuthUtils.getToken(context), id));
    }
}