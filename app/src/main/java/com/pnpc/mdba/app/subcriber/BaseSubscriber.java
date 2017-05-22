package com.pnpc.mdba.app.subcriber;


import com.pnpc.mdba.app.presenter.BaseViewModel;

import org.reactivestreams.Subscriber;

/**
 * Created by markusmcgee on 5/19/17.
 */

public abstract class BaseSubscriber <T> implements Subscriber<T> {

    private final BaseViewModel baseViewModel;

    public BaseSubscriber(BaseViewModel baseViewModel){
        this.baseViewModel = baseViewModel;
    }


    @Override
    public void onError(Throwable t) {
        baseViewModel.error(t.getMessage());
    }
}
