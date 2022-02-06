package com.example.comp2000hkcw2.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton
public class ServiceGenerator {
    private static ServiceGenerator instance = new ServiceGenerator();

    private Interface service;
    private static Gson gson;
    private static Retrofit retrofit;

    private ServiceGenerator() {

        gson = new GsonBuilder()
                .create();

        if(retrofit == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            this.service = retrofit.create(Interface.class);
        }
    }

    public static ServiceGenerator getInstance() {
        return instance;
    }

    public Interface getService() {
        return this.service;
    }

}
