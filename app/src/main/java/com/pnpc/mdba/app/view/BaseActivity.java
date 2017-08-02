package com.pnpc.mdba.app.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pnpc.mdba.app.di.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by markusmcgee on 5/19/17.
 */
/*
* Name: Base Activity
* Description: Base Activity used to stub out common functionality across Activities.
* */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String GENRE_LIST = "GENRE_LIST";

    /*
    * The onCreate contains the main ButterKnife bind command.  All Activities will automatically ButterKnife bind.
    */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    protected abstract void inject(ApplicationComponent component);

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public int getLayoutId() {
        return 0;
    }

    public SharedPreferences getSharedPreference(){
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences;
    }

}
