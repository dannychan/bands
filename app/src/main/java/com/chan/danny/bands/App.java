package com.chan.danny.bands;

import android.app.Application;
import android.content.Context;

/**
 * Created by dannychan on 4/14/16.
 */
public class App extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        mContext = this.getApplicationContext();
    }
}