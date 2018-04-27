package com.balinasoft.balinasoftapp.mvp.photos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.mvp.map.MapFragment;
import com.balinasoft.balinasoftapp.mvp.photos.details.DetailsFragment;
import com.balinasoft.domain.entity.Photo;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;

public class PhotosFragment extends MvpAppCompatFragment implements PhotosView {

    @InjectPresenter
    PhotosPresenter mPhotosPresenter;

    ProgressBar mProgressBar;

    RecyclerView mRecyclerView;

    Adapter mAdapter;

    public static PhotosFragment newInstance() {
        final PhotosFragment fragment = new PhotosFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_photos);
        mProgressBar = view.findViewById(R.id.progress_photos);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);

        mAdapter = new Adapter(new ArrayList<>(), R.layout.photo_item);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mAdapter.setRecyclerItemClickListener(photo -> mPhotosPresenter.onItemSelected(photo));
        getPhotos();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDetailActivity(DetailsFragment fragment) {
        getFragmentManager().beginTransaction().replace(R.id.container,fragment).addToBackStack(null).commit();
    }

    @Override
    public void addItems(List<Photo> photos) {
        mAdapter.addItems(photos);
    }


    public void getPhotos(){
        mPhotosPresenter.getNextPhotos("0");
    }


}

