package com.example.android10;

import model.Album;
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

    private List<Album> userAlbums;

    // Pass in the contact array into the constructor
    public HomeAdapter(List<Album> albums) {
        userAlbums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView albumNameTextView;
        public Button openAlbumButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            albumNameTextView = (TextView) itemView.findViewById(R.id.albumNameTextView);
            openAlbumButton = (Button) itemView.findViewById(R.id.openAlbumButton);
        }
    }
}
