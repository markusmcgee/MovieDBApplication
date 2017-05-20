package com.pnpc.mdba.app.presenter;

/**
 * Created by markusmcgee on 5/19/17.
 */
public interface Presenter<T extends BaseViewModel> {

    void setViewModel(T viewModel);

    void start();

    void stop();
}
