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
import model.Photo;

public class SearchAdapter extends ArrayAdapter<Photo> {
    public SearchAdapter(@NonNull Context context, ArrayList<Photo> listOfFilteredPhotos) {
        super(context, 0, listOfFilteredPhotos);
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

        singlePhoto.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DisplayActivity.class);
            view.getContext().startActivity(intent);
        });

        return singlePhoto;
    }
}
