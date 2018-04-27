package com.balinasoft.balinasoftapp.mvp.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.balinasoft.balinasoftapp.R;

public class MapFragment extends MvpAppCompatFragment implements MapView {

    public static MapFragment newInstance() {
        final MapFragment fragment = new MapFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}
