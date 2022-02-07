package com.example.comp2000hkcw2.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton
public class ServiceGenerator {
    private static ServiceGenerator instance = new ServiceGenerator();

    private Service service;
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private ServiceGenerator() {

        if (retrofit == null) {

            int timeOut = 5 * 60;
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(timeOut, TimeUnit.SECONDS)
                    .writeTimeout(timeOut, TimeUnit.SECONDS)
                    .readTimeout(timeOut, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            this.service = retrofit.create(Service.class);
        }
    }

    public static ServiceGenerator getInstance() {
        return instance;
    }

    public Service getService() {
        return this.service;
    }

}
