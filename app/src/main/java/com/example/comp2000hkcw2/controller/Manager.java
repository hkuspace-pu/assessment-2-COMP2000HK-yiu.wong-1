package com.example.comp2000hkcw2.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//singleton
public class Manager {
    private static Manager instance = new Manager();

    private Service service;

    private Manager() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://web.socem.plymouth.ac.uk/COMP2000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(Service.class);
    }

    public static Manager getInstance() {
        return instance;
    }

    public Service getService() {
        return this.service;
    }

}
