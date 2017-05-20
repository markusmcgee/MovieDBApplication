package com.pnpc.mdba.app;

import android.app.Application;
import android.content.Context;

import com.pnpc.mdba.app.di.ApplicationComponent;
import com.pnpc.mdba.app.di.ApplicationModule;
import com.pnpc.mdba.app.di.DaggerApplicationComponent;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class MovieDBApplication extends Application {

    private static MovieDBApplication app;
    private ApplicationComponent applicationComponent;

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
