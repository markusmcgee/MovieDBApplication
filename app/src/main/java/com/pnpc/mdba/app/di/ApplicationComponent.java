package com.pnpc.mdba.app.di;

import com.pnpc.mdba.app.MainActivity;
import com.pnpc.mdba.app.activity.MovieActivity;
import com.pnpc.mdba.app.activity.MovieDetailActivity;
import com.pnpc.mdba.app.presenter.GenrePresenter;
import com.pnpc.mdba.app.presenter.MovieDiscoverSearchPresenter;
import com.pnpc.mdba.app.presenter.MoviePresenter;
import com.pnpc.mdba.app.presenter.SearchMoviePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by markusmcgee on 5/19/17.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(MovieActivity movieActivity);


    void inject(GenrePresenter genrePresenter);

    void inject(MoviePresenter moviePresenter);

    void inject(MovieDiscoverSearchPresenter movieDiscoverSearchPresenter);

    void inject(SearchMoviePresenter searchMoviePresenter);

    void inject(MovieDetailActivity movieDetailActivity);
}
