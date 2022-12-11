package activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android10.R;

import java.util.Objects;

import model.Album;
import model.Model;

public class PhotosListActivity extends AppCompatActivity {
    Button back;
    Album currentAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoslist);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        currentAlbum = (Album) Model.dataTransfer.get(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upload, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.upload) {
            upload(this);
        }
        return super.onOptionsItemSelected(item);
    }

    public void upload(Context context) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            System.out.println(currentAlbum.photos);
            System.out.println(selectedImage.getPath());
            try {
                currentAlbum.addPhoto(selectedImage.getPath());
                System.out.println(currentAlbum.photos);
            } catch (Exception e) {
                throw new RuntimeException("Error adding uploaded photo to album");
            }
        }
    }
}
