package activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android10.R;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.stream.Collectors;

import model.Album;
import model.Model;
import model.Photo;

public class DisplayActivity extends AppCompatActivity {
    public Album currentAlbum;
    public Photo currentPhoto;
    public int currentIndex;
    public Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        currentAlbum = (Album) Model.dataTransfer.get(0);
        currentPhoto = (Photo) Model.dataTransfer.get(1);
        currentIndex = currentAlbum.photos.indexOf(currentPhoto);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.displaymenu, menu);
        optionsMenu = menu;
        this.setTitle((currentIndex + 1) + " of " + currentAlbum.photos.size());
        updatePrevNext();
        updateDisplay();
        updateTagsList();
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
            updateTagsList();
        } else if (id == R.id.next) {
            currentIndex++;
            currentPhoto = currentAlbum.photos.get(currentIndex);
            updatePrevNext();
            updateDisplay();
            updateTagsList();
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
//        if (!currentPhoto.path.isEmpty()) displayImage.setImage(new Image("file:" + currentPhoto.path));
    }

    public void updateTagsList() {
//        this.tagsList.setItems(FXCollections.observableList(currentPhoto.tags.stream().map(t -> t.type+"="+t.value).collect(Collectors.toList())));
    }
}

