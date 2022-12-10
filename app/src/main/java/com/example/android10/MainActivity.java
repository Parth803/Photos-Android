package com.example.android10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import adapter.Albums;
import model.Album;
import model.Model;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albums);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // get albums from storage
        ArrayList<Album> albums = Model.currentUser.albums;
        albums.add(new Album("Gay = Happy"));

        // Create adapter passing in the sample user data
        Albums adapter = new Albums(albums);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}