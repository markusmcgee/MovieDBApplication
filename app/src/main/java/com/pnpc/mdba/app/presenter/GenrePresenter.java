package com.pnpc.mdba.app.presenter;

import android.util.Log;

import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.service.MovieDBClient;
import com.pnpc.mdba.app.service.MovieDBScheduler;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class GenrePresenter implements Presenter<GenrePresenter.ViewModel> {

    private static final String TAG = "GenrePresenter";
    private final CompositeDisposable disposable;
    private ViewModel viewModel;


    public interface ViewModel extends BaseViewModel {
        //Todo: Add additional help to interface
    }

    @Inject
    MovieDBClient movieDBClient;

    public GenrePresenter() {
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
                .getMovieGenres(BuildConfig.MOVIE_DB_API_KEY, MovieDBApplication.getDeviceLocale())
                .subscribeOn(MovieDBScheduler.background())
                .unsubscribeOn(MovieDBScheduler.background())
                .observeOn(MovieDBScheduler.main())
                .subscribeWith(new DisposableObserver<Genre>() {

                    @Override
                    public void onNext(Genre value) {
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
