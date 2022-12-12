package com.example.android10;

import android.app.Application;
import android.util.Log;

import java.io.File;

import model.Album;
import model.Model;

public class PhotosLibrary extends Application {
    private static File filesDirectory;

    @Override
    public void onCreate() {
        super.onCreate();
        filesDirectory = this.getFilesDir();
        Model.init();
        try {
            Model.currentUser.albums.get(0).photos.get(0).addTag("location", "new york");
            Model.currentUser.albums.add(new Album("Mermaid Man"));
        } catch (Exception e) {
            Log.i("Exception", e.getMessage());
        }
    }

    public static File getFilesDirectory() {
        return filesDirectory;
    }
}


