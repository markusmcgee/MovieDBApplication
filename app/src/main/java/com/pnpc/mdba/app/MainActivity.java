package com.pnpc.mdba.app;


import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.presenter.MoviePresenter;
import com.pnpc.mdba.app.presenter.Presenter;
import com.pnpc.mdba.app.view.BaseActivity;


/**
 * Created by markusmcgee on 5/19/17.
 */

public class MainActivity extends BaseActivity implements Presenter<MoviePresenter.ViewModel>,MoviePresenter.ViewModel {

    MoviePresenter moviePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        moviePresenter = new MoviePresenter();
        moviePresenter.setViewModel(this);
        moviePresenter.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void inject(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_activity;
    }


    @Override
    public void setViewModel(MoviePresenter.ViewModel viewModel) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void error(String errorMessage) {

    }

    @Override
    public void dismissLoading() {

    }
}
