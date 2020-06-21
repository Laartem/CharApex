package com.github.charapex.presentation.view;

import android.app.Application;
import android.os.SystemClock;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        SystemClock.sleep(2000);
    }
}
