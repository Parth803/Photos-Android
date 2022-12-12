package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android10.R;

import adapter.AlbumsListAdapter;
import model.Model;

public class AlbumsListActivity extends AppCompatActivity {
    public static AlbumsListAdapter adapter;
    public static RecyclerView listOfAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumslist);
        Model.logOut();

        listOfAlbums = findViewById(R.id.rvContacts);

        // Create adapter passing in the sample user data
        adapter = new AlbumsListAdapter(Model.currentUser.albums);

        listOfAlbums.setAdapter(adapter);

        listOfAlbums.setLayoutManager(new LinearLayoutManager(this));
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
        Model.initNextScene(true);
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public static void refresh(Context c) {
        adapter = new AlbumsListAdapter(Model.currentUser.albums);
        listOfAlbums.setAdapter(adapter);
        listOfAlbums.setLayoutManager(new LinearLayoutManager(c));
    }
}
