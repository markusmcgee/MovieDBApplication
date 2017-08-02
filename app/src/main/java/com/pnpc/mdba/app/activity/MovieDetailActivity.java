package com.pnpc.mdba.app.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pnpc.mdba.app.R;
import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.presenter.MoviePresenter;
import com.pnpc.mdba.app.view.BaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by markusmcgee on 5/22/17.
 */


/*
* Name: Movie Detail Activity
* Description: Activity used to display movie detail information.  Could have been a fragment or custom view.
* */
public class MovieDetailActivity extends BaseActivity implements MoviePresenter.ViewModel {

    private static final String TAG = "MovieDetailActivity";

    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";
    MoviePresenter moviePresenter;
    private Movie movie;

    @BindView(R.id.overview)
    TextView overview;

    @BindView(R.id.release_date)
    TextView releaseDate;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.popularity)
    TextView popularity;

    @BindView(R.id.vote_count)
    TextView voteCount;
    private List<Genre> genreList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        String genreString = getSharedPreference().getString(GENRE_LIST, "");
        if (genreString.length() > 0)
            genreList = new Gson().fromJson(genreString, new TypeToken<List<Genre>>() {
            }.getType());

        Log.d(TAG, "debug");

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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void error() {
        Toast.makeText(this, getString(R.string.error_occured), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
        if (movie.getOverview() != null)
            overview.setText(movie.getOverview());

        if (movie.getTitle() != null)
            title.setText(movie.getTitle());

        if (movie.getVoteAverage() != null)
            voteCount.setText(movie.getVoteAverage().toString());

        if (movie.getPopularity() != null)
            popularity.setText(movie.getPopularity().toString());

        if (movie.getReleaseDate() != null)
            releaseDate.setText(movie.getReleaseDate());

    }
}