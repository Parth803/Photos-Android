package com.example.android10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import model.Model;
import model.Photo;

public class SearchActivity extends AppCompatActivity {


    ListView listOfSearchedPhotos;

    ArrayAdapter<Photo> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listOfSearchedPhotos = findViewById(R.id.listOfSearchedPhotos);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Model.currentUser.getAllPhotos());
        listOfSearchedPhotos.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("[type=value AND/OR type=value]");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // SEARCH THROUGH USER'S PHOTOS AFTER CHECKING QUERY USING SAME METHOD
                if (query.isEmpty() || query.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2} TO \\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}") || query.matches("\\S+=\\S+") || query.matches("\\S+=\\S+ AND \\S+=\\S+") || query.matches("\\S+=\\S+ OR \\S+=\\S+")) {


                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
