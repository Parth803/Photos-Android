package com.example.android10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("[MM/DD/YYYY hh:mm:ss TO MM/DD/YYYY hh:mm:ss] or [type=value AND/OR type=value]");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // SEARCH THROUGH USER'S PHOTOS AFTER CHECKING QUERY USING SAME METHOD
                if (query.isEmpty() || query.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2} TO \\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}") || query.matches("\\S+=\\S+") || query.matches("\\S+=\\S+ AND \\S+=\\S+") || query.matches("\\S+=\\S+ OR \\S+=\\S+")) {
                   // Change to search activity
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}

