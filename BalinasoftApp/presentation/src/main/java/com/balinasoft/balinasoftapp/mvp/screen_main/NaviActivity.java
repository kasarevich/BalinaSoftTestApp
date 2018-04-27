package com.balinasoft.balinasoftapp.mvp.screen_main;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;


import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.mvp.map.MapFragment;
import com.balinasoft.balinasoftapp.mvp.photos.PhotosFragment;
import com.balinasoft.data.utils.AuthUtils;


import butterknife.BindView;
import butterknife.ButterKnife;

public class NaviActivity extends MvpAppCompatActivity implements NaviView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;

    TextView username;

    @InjectPresenter
    NaviPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setupNavigationContent(mNavigationView);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToat("FABFAB");
            }
        });

        View header = mNavigationView.getHeaderView(0);
        username = header.findViewById(R.id.username);
        mPresenter.getUsername();

    }

    private void setupNavigationContent(NavigationView navigationView) {
            navigationView.setNavigationItemSelectedListener(item -> {
                mPresenter.selectDrawerItem(item);
                item.setChecked(true);
                setTitle(item.getTitle());
                mDrawer.closeDrawers();
                return true;
        });
    }


    @Override
    public void setHeaderTitle(String title){
        username.setText(title);
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

    public void navigateToFragment (Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    public void showToat(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
