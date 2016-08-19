package com.code.wing.baseapp.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startApp();
        finish();
    }

    public abstract void startApp();

}