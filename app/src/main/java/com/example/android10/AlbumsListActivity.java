package com.example.android10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import adapter.AlbumsList;
import model.Model;

public class AlbumsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumslist);

        RecyclerView rvContacts = findViewById(R.id.rvContacts);

        // Create adapter passing in the sample user data
        AlbumsList adapter = new AlbumsList(Model.currentUser.albums);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}

