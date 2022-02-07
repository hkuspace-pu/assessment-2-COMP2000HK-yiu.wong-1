package com.example.comp2000hkcw2.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton
public class ServiceGenerator {
    private static ServiceGenerator instance = new ServiceGenerator();

    private Service service;
    private static Retrofit retrofit;
    private static Gson gson;

    private ServiceGenerator() {

        gson = new GsonBuilder().create();

        if (retrofit == null) {
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
