package com.example.android10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import model.Album;
import adapter.AlbumsListAdapter;
import model.Model;
import activity.SearchActivity;

public class MainAlbumsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Model.init();
        try {
            Model.currentUser.albums.get(0).photos.get(0).addTag("location", "new york");
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumslist);

        RecyclerView rvContacts = findViewById(R.id.rvContacts);

        // Create adapter passing in the sample user data
        AlbumsListAdapter adapter = new AlbumsListAdapter(Model.currentUser.albums);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchbutton, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_button) {
            openSearch(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openSearch(Context context) {
        // NAVIGATE TO NEXT VIEW BY CALLING CHANGE VIEW FUNCTION IN MAIN
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}