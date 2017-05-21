package com.pnpc.mdba.app.presenter;

import android.util.Log;

import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.service.MovieDBClient;
import com.pnpc.mdba.app.service.MovieDBScheduler;

import io.reactivex.observers.DisposableObserver;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class MoviePresenter implements Presenter<MoviePresenter.ViewModel> {

    private static final String TAG = "MoviePresenter";
    private final CompositeDisposable disposable;
    private ViewModel viewModel;

    public interface ViewModel extends BaseViewModel {
        //Todo: Add additional help to interface
    }

    @Inject
    MovieDBClient movieDBClient;

    @Inject
    public MoviePresenter() {
        ((MovieDBApplication) MovieDBApplication.getAppContext()).getApplicationComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    @Override
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        disposable.clear();
        disposable.add(movieDBClient
                .getMovie(550, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(MovieDBScheduler.background())
                .unsubscribeOn(MovieDBScheduler.background())
                .observeOn(MovieDBScheduler.main())
                .subscribeWith(new DisposableObserver<Movie>() {

                    @Override
                    public void onNext(Movie value) {
                        Log.d(TAG, "debug");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "debug");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "debug");
                    }
                })
        );
    }

    @Override
    public void stop() {

    }

}