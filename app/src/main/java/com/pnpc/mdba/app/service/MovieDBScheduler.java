package com.pnpc.mdba.app.service;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by markusmcgee on 5/19/17.
 */

public class MovieDBScheduler {
    public static Scheduler background() {
        return Schedulers.io();
    }

    public static Scheduler main() {
        return AndroidSchedulers.mainThread();
    }
}
