package com.phoenix.rxweather.util;

import okhttp3.OkHttpClient;

/**
 * okhttp client holder
 */
public class OKHttpManager {

    private static OKHttpManager mInstance;

    private static OkHttpClient mClient;


    private OKHttpManager() {
    }

    public static OKHttpManager getInstance() {
        if(mInstance == null) {
            mInstance = new OKHttpManager();
        }
        return mInstance;
    }

    public OkHttpClient getClient() {
        if(mClient == null) {
            mClient = new OkHttpClient();
        }
        return mClient;
    }
}
