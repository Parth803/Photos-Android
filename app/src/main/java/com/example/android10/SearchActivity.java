package com.example.android10;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Model;
import model.Photo;

public class SearchActivity extends AppCompatActivity {


    ListView listOfSearchedPhotos;

    ArrayAdapter<Photo> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listOfSearchedPhotos = findViewById(R.id.listOfSearchedPhotos);
        arrayAdapter = new ArrayAdapter<Photo>(this, android.R.layout.simple_list_item_1, Model.currentUser.getAllPhotos());
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
            public boolean onQueryTextSubmit(String searchQuery) {
                // SEARCH THROUGH USER'S PHOTOS AFTER CHECKING QUERY USING SAME METHOD
                if (searchQuery.isEmpty()) {
                    arrayAdapter.getFilter().filter("");
                } else if (searchQuery.matches("\\S+=\\S+")) {
                    Pattern p = Pattern.compile("(\\S+)=(\\S+)");
                    Matcher m = p.matcher(searchQuery);
                    if (m.find()) {
                        try {
                            arrayAdapter.clear();
                            arrayAdapter.addAll(Model.currentUser.getPhotosByTag(m.group(1), m.group(2)));
                        } catch (Exception e) {
                            throw new RuntimeException("Error parsing searchQuery for a tag");
                        }
                    }
                } else if (searchQuery.matches("\\S+=\\S+ AND \\S+=\\S+")) {
                    Pattern p = Pattern.compile("(\\S+)=(\\S+) AND (\\S+)=(\\S+)");
                    Matcher m = p.matcher(searchQuery);
                    if (m.find()) {
                        try {
                            arrayAdapter.clear();
                            arrayAdapter.addAll(Model.currentUser.getPhotosByTags(m.group(1), m.group(2), m.group(3),m.group(4), true));
                        } catch (Exception e) {
                            throw new RuntimeException("Error parsing searchQuery for tags using AND");
                        }
                    }
                } else if (searchQuery.matches("\\S+=\\S+ OR \\S+=\\S+")) {
                    Pattern p = Pattern.compile("(\\S+)=(\\S+) OR (\\S+)=(\\S+)");
                    Matcher m = p.matcher(searchQuery);
                    if (m.find()) {
                        try {
                            arrayAdapter.clear();
                            arrayAdapter.addAll(Model.currentUser.getPhotosByTags(m.group(1), m.group(2), m.group(3),m.group(4), false));
                        } catch (Exception e) {
                            throw new RuntimeException("Error parsing searchQuery for tags using OR");
                        }
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                if (searchQuery.isEmpty()) {
                    arrayAdapter.getFilter().filter("");
                }
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}
