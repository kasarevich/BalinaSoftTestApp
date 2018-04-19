package com.balinasoft.balinasoftapp.activities;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.fragments.MapFragment;
import com.balinasoft.balinasoftapp.fragments.PhotosFragment;
import com.balinasoft.balinasoftapp.views.NaviView;
import com.balinasoft.data.utils.AuthUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class NaviActivity extends MvpAppCompatActivity implements NaviView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setupNavigationContent(mNavigationView);

        View header = mNavigationView.getHeaderView(0);
        TextView username = header.findViewById(R.id.username);
        username.setText(AuthUtils.getUsername(this));

        getSupportFragmentManager().beginTransaction().replace(R.id.container, PhotosFragment.newInstance()).commit();
    }

    private void setupNavigationContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(item -> {
            selectDrawerItem(item);
            return true;
        });
    }

    private void selectDrawerItem(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.nav_photos:
                manager.beginTransaction().replace(R.id.container, PhotosFragment.newInstance()).commit();
                break;
            case R.id.nav_map:
                manager.beginTransaction().replace(R.id.container, MapFragment.newInstance()).commit();
                break;
        }
        item.setChecked(true);
        setTitle(item.getTitle());
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
