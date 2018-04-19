package com.balinasoft.balinasoftapp.activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.app.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StartActivity extends AppCompatActivity {


    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.sign_tabs)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


}
