package com.balinasoft.balinasoftapp.mvp.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balinasoft.balinasoftapp.R;

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
    AlertDialog.Builder mDialogBuilder;

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
        mProgress.setTitle(getString(R.string.registration));
        mProgress.setMessage(getString(R.string.waiting_for_server));
        mProgress.setCanceledOnTouchOutside(false);
        mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgress.show();
    }

    @Override
    public void finishSignIn() {
        mProgress.dismiss();
    }


    @Override
    public void getLoginAndPassword() {
        mRegisterPresenter.checkFields(mLoginEditText.getText().toString(), mPasswordEditText.getText().toString(), mRepeatEditText.getText().toString());
    }

    @Override
    public void showDialog(String msg, String title) {
        mDialogBuilder = new AlertDialog.Builder(getContext());
        mDialogBuilder
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton(getString(R.string.close),
                        (dialog, which) -> dialog.cancel());

        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void showMessageToUser(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNaviActivity(){
        startActivity(new Intent(getString(R.string.intent_goto_navi)));
    }

}