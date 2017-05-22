package com.pnpc.mdba.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pnpc.mdba.app.R;
import com.pnpc.mdba.app.adapter.MovieAdapter;
import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.model.MovieSearchResponse;
import com.pnpc.mdba.app.presenter.SearchMoviePresenter;
import com.pnpc.mdba.app.view.BaseActivity;

import butterknife.BindView;

/**
 * Created by markusmcgee on 5/22/17.
 */

public class MovieActivity extends BaseActivity implements SearchMoviePresenter.ViewModel {

    public static final String EXTRAS_MOVIE_SEARCH_TEXT = "EXTRAS_MOVIE_SEARCH_TEXT";

    @BindView(R.id.result_list)
    RecyclerView resultList;

    SearchMoviePresenter searchMoviePresenter;
    private MovieSearchResponse response;

    private MovieAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (searchMoviePresenter == null) {
            searchMoviePresenter = new SearchMoviePresenter(getIntent().getExtras().getString(EXTRAS_MOVIE_SEARCH_TEXT, "Star Wars"));
            searchMoviePresenter.setViewModel(this);
        }
        searchMoviePresenter.start();
    }

    @Override
    public void setResponse(MovieSearchResponse response) {
        if (adapter == null)
            adapter = new MovieAdapter();
        resultList.setAdapter(adapter);
        adapter.setData(response);
        resultList.setLayoutManager(new LinearLayoutManager(this));
        this.response = response;
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
        return R.layout.movie_activity;
    }

    @Override
    protected void inject(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void error(String errorMessage) {

    }

}
