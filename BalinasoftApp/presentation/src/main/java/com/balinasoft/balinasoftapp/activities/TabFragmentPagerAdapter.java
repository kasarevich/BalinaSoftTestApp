package com.balinasoft.balinasoftapp.activities;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.balinasoft.balinasoftapp.fragments.LoginFragment;
import com.balinasoft.balinasoftapp.fragments.RegisterFragment;


public class TabFragmentPagerAdapter extends FragmentPagerAdapter {




    final String TAB_LOGIN = "Login";
    final String TAB_REGISTER = "Register";

    final int PAGE_COUNT = 2;

    public TabFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new LoginFragment();
        }else return new RegisterFragment();
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return TAB_LOGIN;
        }else return TAB_REGISTER;
    }
}
