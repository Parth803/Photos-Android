package com.example.android10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import model.Album;

public class MainActivity extends AppCompatActivity {

    ArrayList<Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // get albums from storage
        albums = new ArrayList<Album>();
        albums.add(new Album("Gay = Happy"));

        // Create adapter passing in the sample user data
        HomeAdapter adapter = new HomeAdapter(albums);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}