package com.balinasoft.balinasoftapp.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.presenters.RegisterPresenter;
import com.balinasoft.balinasoftapp.views.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends MvpAppCompatFragment implements LoginView {
    @BindView(R.id.login_edit_text)
    EditText mLoginEditText;
    @BindView(R.id.password_edit_text)
    EditText mPasswordEditText;
    @BindView(R.id.repeat_password_edit_text)
    EditText mRepeatEditText;
    @BindView(R.id.login_button)
    Button mLoginButton;

    ProgressDialog mProgress;

    @InjectPresenter

    RegisterPresenter mRegisterPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        mProgress = new ProgressDialog(getContext());
        mLoginButton.setOnClickListener(mLoginButton -> getLoginAndPassword());
    }


    @Override
    public void startSignIn() {
        mProgress.setTitle("Log in");
        mProgress.setMessage("Waiting for server");
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.setButton(Dialog.BUTTON_POSITIVE, "CLOSE", (dialog, which) -> mProgress.dismiss());
        mProgress.show();
        mProgress.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.GONE);
    }

    @Override
    public void finishSignIn() {
        mProgress.dismiss();
    }

    @Override
    public void showLoginError(String msg) {
        mProgress.setTitle("ERROR");
        mProgress.setMessage(msg);
        mProgress.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.VISIBLE);
    }


    @Override
    public void getLoginAndPassword() {
        mRegisterPresenter.checkFields(mLoginEditText.getText().toString(), mPasswordEditText.getText().toString(), mRepeatEditText.getText().toString());
    }


    @Override
    public void showMessageToUser(String msg) {


    }
}