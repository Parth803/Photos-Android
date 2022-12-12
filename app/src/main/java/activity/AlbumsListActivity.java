package activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
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
    public Menu optionsMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.albumslist);
        Model.logOut();

        listOfAlbums = findViewById(R.id.rvContacts);

        adapter = new AlbumsListAdapter(Model.currentUser.albums);

        listOfAlbums.setAdapter(adapter);

        listOfAlbums.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.albumslistmenu, menu);
        optionsMenu = menu;
        MenuItem menuItem = menu.findItem(R.id.action_create);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Enter new album name...");
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                optionsMenu.findItem(R.id.search_button).setVisible(true);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_button) {
            openSearch(this);
        } else if (id == R.id.action_create) {
            optionsMenu.findItem(R.id.search_button).setVisible(false);
            createAlbum(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openSearch(Context context) {
        // NAVIGATE TO NEXT VIEW BY CALLING CHANGE VIEW FUNCTION IN MAIN
        Model.initNextScene(true);
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    public void createAlbum(Context context) {
        // FILL IN WITH POP UP XML TO CREATE NEW ALBUM
        refresh(context);
    }

    public static void refresh(Context context) {
        adapter = new AlbumsListAdapter(Model.currentUser.albums);
        listOfAlbums.setAdapter(adapter);
        listOfAlbums.setLayoutManager(new LinearLayoutManager(context));
    }
}