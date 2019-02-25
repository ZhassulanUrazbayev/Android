package com.hfad.firstmidproject.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {
    private Retrofit mRetrofit;
    public static ApiHelper sApiHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        mRetrofit = new Retrofit.Builder().baseUrl("https://api.exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sApiHelper = mRetrofit.create(ApiHelper.class);

    }

}
