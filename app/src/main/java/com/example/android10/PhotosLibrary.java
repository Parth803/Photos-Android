package com.example.android10;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

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
            Model.currentUser.albums.add(new Album("Mermaid Man"));
            Model.currentUser.albums.get(0).photos.get(0).addTag("location", "new york");
        } catch (Exception e) {
            Log.i("Exception", e.getMessage());
        }
    }

    public static File getFilesDirectory() {
        return filesDirectory;
    }

    public static void errorAlert(Exception e, Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle(e.getMessage());

        builder.setPositiveButton("OK", (dialog, which) -> {
            dialog.cancel();
            dialog.dismiss();
        });

        builder.show();
    }
}


