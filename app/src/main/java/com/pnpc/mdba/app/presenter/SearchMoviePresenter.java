package com.pnpc.mdba.app.presenter;

import android.util.Log;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.model.MovieSearchResponse;
import com.pnpc.mdba.app.service.MovieDBClient;
import com.pnpc.mdba.app.service.MovieDBScheduler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class SearchMoviePresenter implements Presenter<SearchMoviePresenter.ViewModel> {

    private static final String TAG = "MoviePresenter";
    private final CompositeDisposable disposable;
    private ViewModel viewModel;
    private String searchQueryText;
    private int page = 1;


    public interface ViewModel extends BaseViewModel {
        void setResponse(MovieSearchResponse response);
    }

    @Inject
    MovieDBClient movieDBClient;

    public SearchMoviePresenter() {
        ((MovieDBApplication) MovieDBApplication.getAppContext()).getApplicationComponent().inject(this);
        disposable = new CompositeDisposable();
    }

    public void setSearchQueryText(String searchQueryText) {
        this.searchQueryText = searchQueryText;
    }

    public void setPageRequest(int page) {
        this.page = page;
    }


    @Override
    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void start() {
        disposable.clear();
        disposable.add(movieDBClient
                .searchMovies(BuildConfig.MOVIE_DB_API_KEY, MovieDBApplication.getDeviceLocale(), false, searchQueryText, Integer.toString(page))
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
