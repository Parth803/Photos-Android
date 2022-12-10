package com.example.android10;

import model.Album;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class HomeAdapter extends
        RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView albumNameTextView;
        public Button openAlbumButton;


        public ViewHolder(View itemView) {

            super(itemView);

            albumNameTextView = (TextView) itemView.findViewById(R.id.albumNameTextView);
            openAlbumButton = (Button) itemView.findViewById(R.id.openAlbumButton);
        }
    }

    private List<Album> userAlbums;

    public HomeAdapter(List<Album> albums) {
        userAlbums = albums;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.album_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Album album = userAlbums.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.albumNameTextView;
        textView.setText(album.name);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return userAlbums.size();
    }
}
