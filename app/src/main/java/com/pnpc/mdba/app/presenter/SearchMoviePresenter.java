package com.pnpc.mdba.app.presenter;

import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.model.MovieSearchResponse;
import com.pnpc.mdba.app.service.MovieDBClient;
import com.pnpc.mdba.app.service.MovieDBScheduler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class SearchMoviePresenter implements Presenter<SearchMoviePresenter.ViewModel> {

    private static final String TAG = "MoviePresenter";
    private final CompositeDisposable disposable;
    private ViewModel viewModel;
    private String searchQueryText;


    public interface ViewModel extends BaseViewModel {
        void setResponse(MovieSearchResponse response);
    }

    @Inject
    MovieDBClient movieDBClient;

    public SearchMoviePresenter() {
        ((MovieDBApplication) MovieDBApplication.getAppContext()).getApplicationComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    public SearchMoviePresenter(String searchQueryText) {
        ((MovieDBApplication) MovieDBApplication.getAppContext()).getApplicationComponent().inject(this);
        disposable = new CompositeDisposable();
        this.searchQueryText = searchQueryText;
    }

    public void setSearchQueryText(String searchQueryText) {
        this.searchQueryText = searchQueryText;
    }


    @Override
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        disposable.clear();
        disposable.add(movieDBClient
                .searchMovies(BuildConfig.MOVIE_DB_API_KEY, MovieDBApplication.getDeviceLocale(), false, searchQueryText)
                .subscribeOn(MovieDBScheduler.background())
                .unsubscribeOn(MovieDBScheduler.background())
                .observeOn(MovieDBScheduler.main())
                .subscribeWith(new DisposableObserver<MovieSearchResponse>() {

                    @Override
                    public void onNext(MovieSearchResponse response) {
                        viewModel.setResponse(response);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void stop() {

    }

}
