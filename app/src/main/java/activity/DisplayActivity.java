package activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android10.PhotosLibrary;
import com.example.android10.R;

import java.util.Objects;

import adapter.DisplayAdapter;
import model.Album;
import model.Model;
import model.Photo;

public class DisplayActivity extends AppCompatActivity {
    public static DisplayAdapter adapter;
    public Album currentAlbum;
    public static Photo currentPhoto;
    public int currentIndex;
    public Menu optionsMenu;
    public ImageView imageView;
    public static RecyclerView listOfTags;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public Switch tagSwitch;
    public static Boolean isPersonTag;
    public SearchView tagValueField;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        currentAlbum = (Album) Model.dataTransfer.get(0);
        currentPhoto = (Photo) Model.dataTransfer.get(1);
        currentIndex = currentAlbum.photos.indexOf(currentPhoto);
        listOfTags = findViewById(R.id.listOfTags);
        listOfTags.setAdapter(adapter);
        listOfTags.setLayoutManager(new LinearLayoutManager(this));
        updateDisplay();
        updateTagsList(this);
        context = this;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.displaymenu, menu);
        optionsMenu = menu;
        this.setTitle((currentIndex + 1) + " of " + currentAlbum.photos.size());
        updatePrevNext();

        tagSwitch = (Switch) menu.findItem(R.id.tagSwitch).getActionView();
        tagValueField = (SearchView) menu.findItem(R.id.action_create).getActionView();
        menu.findItem(R.id.tagSwitch).setVisible(false);
        isPersonTag = tagSwitch.isChecked();
        tagValueField.setQueryHint("Tag Value...");
        tagValueField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    currentPhoto.addTag(isPersonTag ? "person" : "location", query);
                    Model.persist();
                    updateTagsList(context);
                } catch (Exception e) {
                    PhotosLibrary.errorAlert(e, context);
                }
                menu.findItem(R.id.action_create).collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.action_create), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                menu.findItem(R.id.tagSwitch).setVisible(true);
                menu.findItem(R.id.previous).setVisible(false);
                menu.findItem(R.id.next).setVisible(false);
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                menu.findItem(R.id.previous).setVisible(true);
                menu.findItem(R.id.next).setVisible(true);
                menu.findItem(R.id.tagSwitch).setVisible(false);
                return true;
            }
        });
        tagSwitch.setText("Location");
        tagSwitch.setOnCheckedChangeListener((view, checked) -> {
            isPersonTag = checked;
            tagSwitch.setText(checked ? "Person" : "Location");
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Model.initPreviousScene();
            finish();
        } else if (id == R.id.previous) {
            currentIndex--;
            currentPhoto = currentAlbum.photos.get(currentIndex);
            updatePrevNext();
            updateDisplay();
            updateTagsList(this);
        } else if (id == R.id.next) {
            currentIndex++;
            currentPhoto = currentAlbum.photos.get(currentIndex);
            updatePrevNext();
            updateDisplay();
            updateTagsList(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void updatePrevNext() {
        optionsMenu.findItem(R.id.previous).setEnabled(currentAlbum.photos.indexOf(currentPhoto) != 0);
        optionsMenu.findItem(R.id.previous).getIcon().setAlpha((currentAlbum.photos.indexOf(currentPhoto) != 0) ? 255 : 130);
        optionsMenu.findItem(R.id.next).setEnabled(currentAlbum.photos.indexOf(currentPhoto) != (currentAlbum.photos.size() - 1));
        optionsMenu.findItem(R.id.next).getIcon().setAlpha((currentAlbum.photos.indexOf(currentPhoto) != (currentAlbum.photos.size() - 1)) ? 255 : 130);
    }

    public void updateDisplay() {
        this.setTitle((currentIndex + 1) + " of " + currentAlbum.photos.size());
        imageView = this.findViewById(R.id.imageView);
        imageView.setImageURI(Uri.parse(currentPhoto.path));
    }

    public static void updateTagsList(Context context) {
        adapter = new DisplayAdapter(currentPhoto.tags);
        listOfTags.setAdapter(adapter);
        listOfTags.setLayoutManager(new LinearLayoutManager(context));
    }
}

