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

public class MovieDiscoverSearchPresenter implements Presenter<MovieDiscoverSearchPresenter.ViewModel> {

    private static final String TAG = "MoviePresenter";
    private final CompositeDisposable disposable;
    private int genre;
    private ViewModel viewModel;


    public interface ViewModel extends BaseViewModel {
        //Todo: Add additional help to interface
    }

    @Inject
    MovieDBClient movieDBClient;

    public MovieDiscoverSearchPresenter(int genre) {
        ((MovieDBApplication) MovieDBApplication.getAppContext()).getApplicationComponent().inject(this);
        disposable = new CompositeDisposable();
        this.genre = genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }


    @Override
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        disposable.clear();
        disposable.add(movieDBClient
                .searchMovieDiscover(BuildConfig.MOVIE_DB_API_KEY, MovieDBApplication.getDeviceLocale(), genre, false, true)
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
