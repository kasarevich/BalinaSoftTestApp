package com.balinasoft.balinasoftapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.views.PhotosView;

public class PhotosFragment extends MvpAppCompatFragment implements PhotosView {

    public static PhotosFragment newInstance() {
        final PhotosFragment fragment = new PhotosFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos, container, false);
    }
}
