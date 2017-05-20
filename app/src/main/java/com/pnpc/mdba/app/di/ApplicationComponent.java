package com.pnpc.mdba.app.di;

import com.pnpc.mdba.app.MainActivity;
import com.pnpc.mdba.app.presenter.MoviePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by markusmcgee on 5/19/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MoviePresenter moviePresenter);

    void inject(MainActivity mainActivity);

}
