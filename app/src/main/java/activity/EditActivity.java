package activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android10.R;

import java.util.Objects;

import model.Album;
import model.Model;
import model.Photo;

public class EditActivity extends AppCompatActivity {
    public Album currentAlbum;
    public Photo selectedPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        this.setTitle("Edit");
        this.currentAlbum = (Album) Model.dataTransfer.get(0);
        this.selectedPhoto = (Photo) Model.dataTransfer.get(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Model.initPreviousScene();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

