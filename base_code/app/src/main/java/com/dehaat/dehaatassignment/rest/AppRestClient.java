package com.dehaat.dehaatassignment.rest;

import android.text.TextUtils;
import android.util.Log;

import com.dehaat.dehaatassignment.model.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRestClient {

    private static AppRestClient mInstance;
    int cacheSize = 10 * 1024 * 1024; // 10 MB
    private AppRestClientService appRestClientService;
    private static Retrofit retrofit=null;

    private AppRestClient() {
        setRestClient();
    }

    public static Retrofit getInstance() {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static void setAppRestClientNull() {
        mInstance = null;
    }

    private void setRestClient() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        appRestClientService = retrofit.create(AppRestClientService.class);
    }
}
