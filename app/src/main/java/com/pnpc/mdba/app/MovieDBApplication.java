package com.pnpc.mdba.app;

import android.app.Application;
import android.content.Context;

import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.di.ApplicationModule;
import com.pnpc.mdba.app.di.DaggerApplicationComponent;

import java.util.Locale;

/**
 * Created by markusmcgee on 5/19/17.
 */

/*
* Name: Movie DB Application
*
* Description: This application demonstrates how to use DI - Dagger, Retrofit and RxJava for Android.
* The application attempts to follow MVP - Model View Presenter with Observerable callbacks.
*
* Notes: Main Application class used for Application.  Dagger dependency injection setup begins here.
*/
public class MovieDBApplication extends Application {

    private static MovieDBApplication app;
    private ApplicationComponent applicationComponent;

    /*
    * getDeviceLocale is used to pull local to be used in API calls to MovieDB backend.
    * */
    public static String getDeviceLocale() {
        return Locale.getDefault().toString().replace("_", "-");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        app = (MovieDBApplication) getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static Context getAppContext() {
        return app;
    }


}
