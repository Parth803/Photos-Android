package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android10.R;

import java.util.ArrayList;

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
        TextView caption = singlePhoto.findViewById(R.id.imageCaption);
        ImageView imageView = singlePhoto.findViewById(R.id.imageView);

        caption.setText(photo.caption);
        imageView.setImageResource(0);//path needs to be implemented

        return singlePhoto;
    }
}
