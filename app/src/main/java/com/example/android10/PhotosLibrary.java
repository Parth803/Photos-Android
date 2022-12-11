package com.example.android10;

import android.app.Application;

import model.Model;

public class PhotosLibrary extends Application {

    public PhotosLibrary() {
        // this method fires only once per application start.
        // getApplicationContext returns null here
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // this method fires once as well as constructor
        // but also application has context here
        Model.init();
    }
}



