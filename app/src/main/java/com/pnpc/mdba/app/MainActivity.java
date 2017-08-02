package com.pnpc.mdba.app;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.pnpc.mdba.app.activity.MovieActivity;
import com.pnpc.mdba.app.adapter.GenreAdapter;
import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.presenter.GenrePresenter;
import com.pnpc.mdba.app.view.BaseActivity;

import java.util.List;

import butterknife.BindView;


/**
 * Created by markusmcgee on 5/19/17.
 */

public class MainActivity extends BaseActivity implements GenrePresenter.ViewModel, SearchView.OnQueryTextListener {

    private final String TAG = "MainActivity";

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.result_list)
    RecyclerView resultList;

    GenrePresenter genrePresenter;


    private GenreAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchView.setOnQueryTextListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (genrePresenter == null) {
            genrePresenter = new GenrePresenter();
            genrePresenter.setViewModel(this);
        }
        genrePresenter.start();
    }

    @Override
    public void setResponse(List<Genre> genreList) {
        if (adapter == null)
            adapter = new GenreAdapter();
        resultList.setAdapter(adapter);
        adapter.setData(genreList);
        resultList.setLayoutManager(new LinearLayoutManager(this));
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
    public void error(String errorMessage) {
        Toast.makeText(this, errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Intent intent;
        intent = new Intent(MovieDBApplication.getAppContext(), MovieActivity.class);
        intent.putExtra(MovieActivity.EXTRAS_MOVIE_SEARCH_TEXT, query);
        startActivity(intent);

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
