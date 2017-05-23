package com.pnpc.mdba.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pnpc.mdba.app.R;
import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.presenter.MoviePresenter;
import com.pnpc.mdba.app.view.BaseActivity;

/**
 * Created by markusmcgee on 5/22/17.
 */

public class MovieDetailActivity extends BaseActivity implements MoviePresenter.ViewModel {

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";
    MoviePresenter moviePresenter;
    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        moviePresenter = new MoviePresenter(getIntent().getExtras().getInt(EXTRA_MOVIE_ID));
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
    public int getLayoutId() {
        return R.layout.movie_detail_view;
    }

    @Override
    protected void inject(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void error(String errorMessage) {

    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}