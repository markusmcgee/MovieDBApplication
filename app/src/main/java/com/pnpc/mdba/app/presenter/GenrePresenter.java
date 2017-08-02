package com.pnpc.mdba.app.presenter;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.model.Genre;
import com.pnpc.mdba.app.model.GenreResponse;
import com.pnpc.mdba.app.model.Movie;
import com.pnpc.mdba.app.service.MovieDBClient;
import com.pnpc.mdba.app.service.MovieDBScheduler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        void setResponse(List<Genre> genreList);
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
                .subscribeWith(new DisposableObserver<GenreResponse>() {

                    @Override
                    public void onNext(GenreResponse value) {
                        viewModel.setResponse(value.genreList);
                    }

                    @Override
                    public void onError(Throwable e) {

                        String errorMessage = "";
                        JSONObject jObjError = null;

                        try {
                            String jsonString = ((HttpException) e).response().errorBody().string();
                            if (!jsonString.isEmpty()) {
                                jObjError = new JSONObject(jsonString);
                            }
                            errorMessage = ((JSONArray) jObjError.get("errors")).get(0).toString();
                        }
                        catch (JSONException jsone) {
                            jsone.printStackTrace();
                        }
                        catch (IOException ioe) {
                            ioe.printStackTrace();
                        }

                        if (!errorMessage.isEmpty())
                            viewModel.error(errorMessage);
                        else
                            viewModel.error();
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void stop() {
        disposable.clear();
    }

}
