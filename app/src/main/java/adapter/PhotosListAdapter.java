package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android10.R;

import java.util.ArrayList;

import activity.DisplayActivity;
import activity.EditActivity;
import model.Album;
import model.Model;
import model.Photo;

public class PhotosListAdapter extends ArrayAdapter<Photo> {
    public Album album;
    public Boolean displayMode;

    public PhotosListAdapter(@NonNull Context context, ArrayList<Photo> photos) {
        super(context, 0, photos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View singlePhoto = convertView;

        if (singlePhoto == null) {
            singlePhoto = LayoutInflater.from(getContext()).inflate(R.layout.single_photo, parent, false);
        }

        Photo photo = getItem(position);
        ImageView imageView = singlePhoto.findViewById(R.id.imageView);

        imageView.setImageURI(Uri.parse(photo.path));

        singlePhoto.setOnClickListener(view -> displayEditPhoto(view.getContext(), photo));

        return singlePhoto;
    }

    public void displayEditPhoto(Context context, Photo selectedPhoto) {
        Model.initNextScene(true);
        Model.dataTransfer.add(album);
        Model.dataTransfer.add(selectedPhoto);
        Intent intent;
        if (displayMode) {
            intent = new Intent(context, DisplayActivity.class);
        } else {
            intent = new Intent(context, EditActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
