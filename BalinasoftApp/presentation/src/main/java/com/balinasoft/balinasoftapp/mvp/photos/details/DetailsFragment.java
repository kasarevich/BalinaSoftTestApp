package com.balinasoft.balinasoftapp.mvp.photos.details;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.domain.entity.Photo;
import com.bumptech.glide.Glide;

import java.io.Serializable;

public class DetailsFragment extends MvpAppCompatFragment implements DetailsView {


    private static final String ARG_URL = "url";
    private static final String ARG_DATE = "date";
    private static final String ARG_ID = "id";

    @InjectPresenter
    DetailsPresenter mDetailsPresenter;

    ImageView mImageView;
    TextView mTextView;
    AlertDialog.Builder mDialogBuilder;


    public static DetailsFragment newInstance(String url, Long date, int id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_URL, url);
        args.putSerializable(ARG_DATE, date);
        args.putSerializable(ARG_ID, id);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        mImageView = view.findViewById(R.id.detail_image);
        mTextView = view.findViewById(R.id.date_details);

        mImageView.setOnLongClickListener(v -> {
            mDetailsPresenter.removeImage((int) getArguments().getSerializable(ARG_ID));
            //FIXME with db wix by getPhotoByIDMethod
            return true;
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String url = (String) getArguments().getSerializable(ARG_URL);
        Long date = (Long) getArguments().getSerializable(ARG_DATE);
        mDetailsPresenter.getViews(url, date);
    }

    @Override
    public void initViews(Long date, String url){
        mTextView.setText(date.toString());
        Glide
                .with(this)
                .load(url)
                .into(mImageView);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDeleteDialog(String msg, int id) {
        mDialogBuilder = new AlertDialog.Builder(getContext());
        mDialogBuilder
                .setMessage(msg)
                .setNegativeButton(R.string.close,
                        (dialog, which) -> dialog.cancel())
                .setPositiveButton(R.string.ok, (dialog, which) -> mDetailsPresenter.delete(id));

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void back(){
        assert getFragmentManager() != null;
        getFragmentManager().popBackStack();
    }


}
