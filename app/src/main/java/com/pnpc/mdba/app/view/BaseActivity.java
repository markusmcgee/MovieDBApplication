package com.pnpc.mdba.app.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.pnpc.mdba.app.MovieDBApplication;
import com.pnpc.mdba.app.di.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by markusmcgee on 5/19/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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

}
