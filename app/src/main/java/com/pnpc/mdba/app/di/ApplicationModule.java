package com.pnpc.mdba.app.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pnpc.mdba.app.BuildConfig;
import com.pnpc.mdba.app.presenter.MoviePresenter;
import com.pnpc.mdba.app.service.MovieDBClient;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by markusmcgee on 5/19/17.
 */

@Module
public class ApplicationModule {
    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(4, TimeUnit.MINUTES);
        builder.readTimeout(4, TimeUnit.MINUTES);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_DB_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    MovieDBClient providesMovieDBClient(Retrofit retrofit){
        return retrofit.create(MovieDBClient.class);
    }


}
