package com.pnpc.mdba.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.pnpc.mdba.app.R;
import com.pnpc.mdba.app.adapter.MovieAdapter;
import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.model.MovieSearchResponse;
import com.pnpc.mdba.app.presenter.SearchMoviePresenter;
import com.pnpc.mdba.app.view.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by markusmcgee on 5/22/17.
 */

public class MovieActivity extends BaseActivity implements SearchMoviePresenter.ViewModel, MovieAdapter.MovieListener {

    public static final String EXTRAS_MOVIE_SEARCH_TEXT = "EXTRAS_MOVIE_SEARCH_TEXT";
    private static int CURRENT_PAGE_NO = 1;

    @BindView(R.id.result_list)
    RecyclerView resultList;

    @BindView(R.id.movie_search_text)
    EditText movieSearchText;

    SearchMoviePresenter searchMoviePresenter;
    private MovieSearchResponse response;

    private MovieAdapter adapter;
    private LinearLayoutManager layoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        layoutManager = new LinearLayoutManager(this);
        resultList.setLayoutManager(layoutManager);
        resultList.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private boolean isLoading = false;
    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        }
    };

    private boolean isLastPage() {
        return CURRENT_PAGE_NO == response.totalPages;
    }

    private void loadMoreItems() {
        CURRENT_PAGE_NO++;
        doMovieSearch();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @OnClick(R.id.search)
    public void onSearchButtonClick() {
        doMovieSearch();
    }

    private void doMovieSearch() {
        if (searchMoviePresenter == null)
            searchMoviePresenter = new SearchMoviePresenter();

        searchMoviePresenter.setSearchQueryText(movieSearchText.getText().toString());
        searchMoviePresenter.setPageRequest(CURRENT_PAGE_NO);
        searchMoviePresenter.setViewModel(this);

        searchMoviePresenter.start();
        isLoading = true;
    }

    @Override
    public void setResponse(MovieSearchResponse response) {

        this.response = response;

        if (adapter == null)
            adapter = new MovieAdapter(this);

        resultList.setAdapter(adapter);

        if (CURRENT_PAGE_NO == 1) {
            adapter.setData(response);
        }
        else if (CURRENT_PAGE_NO != response.totalPages) {

            for (Movie movie : response.movieList) {
                adapter.addDataItem(movie);
            }
            adapter.notifyItemRangeChanged(1, adapter.getItemCount());
        }
        isLoading = false;

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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMovieClick(int movieId) {
        Intent intent  = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE_ID, movieId);
        startActivity(intent);
    }
}
