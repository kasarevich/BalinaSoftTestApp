package com.balinasoft.balinasoftapp.mvp.screen_main;

import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.balinasoft.balinasoftapp.R;
import com.balinasoft.balinasoftapp.app.App;
import com.balinasoft.balinasoftapp.base.BasePresenter;
import com.balinasoft.balinasoftapp.mvp.map.MapFragment;
import com.balinasoft.balinasoftapp.mvp.photos.PhotosFragment;
import com.balinasoft.domain.interactors.GetUsernameUseCase;

import javax.inject.Inject;

@InjectViewState
public class NaviPresenter extends BasePresenter<NaviView> {

    @Inject
    GetUsernameUseCase mGetUsernameUseCase;

    @Override
    public void createInject() {
        App.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().navigateToFragment(PhotosFragment.newInstance());
    }

    public void selectDrawerItem(MenuItem item){
        switch (item.getItemId()){
            case R.id.nav_photos:
                getViewState().navigateToFragment(PhotosFragment.newInstance());
                break;
            case R.id.nav_map:
                getViewState().navigateToFragment(MapFragment.newInstance());
                break;
        }
    }

    public void getUsername(){
        getViewState().setHeaderTitle(mGetUsernameUseCase.getUsername());
    }
}
