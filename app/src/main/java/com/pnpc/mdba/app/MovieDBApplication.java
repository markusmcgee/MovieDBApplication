package com.pnpc.mdba.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.di.ApplicationModule;
import com.pnpc.mdba.app.di.DaggerApplicationComponent;
import com.pnpc.mdba.app.model.Genre;

import java.util.List;
import java.util.Locale;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class MovieDBApplication extends Application {

    private static MovieDBApplication app;
    private ApplicationComponent applicationComponent;

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
