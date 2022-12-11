package adapter;

import model.Album;
import model.Model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android10.PhotosListActivity;
import com.example.android10.R;

import java.util.ArrayList;


// Create the basic adapter extending from RecyclerView.adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class AlbumsList extends RecyclerView.Adapter<AlbumsList.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView albumNameTextView;
        public Button openAlbumButton;
        public ImageButton renameOrDeleteAlbumButton;

        public ViewHolder(View itemView) {

            super(itemView);

            albumNameTextView = itemView.findViewById(R.id.albumNameTextView);
            openAlbumButton = itemView.findViewById(R.id.openAlbumButton);
            openAlbumButton.setOnClickListener(view -> open(view, albumNameTextView.getText().toString()));
            renameOrDeleteAlbumButton = itemView.findViewById(R.id.renameOrDeleteAlbumButton);
            renameOrDeleteAlbumButton.setOnClickListener(view -> showPopup(view, albumNameTextView.getText().toString()));

        }
    }

    public void open(View view, String album) {
        // NAVIGATE TO NEXT VIEW BY CALLING CHANGE VIEW FUNCTION IN MAIN
        Intent intent = new Intent(view.getContext(), PhotosListActivity.class);
        view.getContext().startActivity(intent);
    }

    public void showPopup(View view, String albumName) {
        // OPEN DA POPUP -- WORK IN PROGRESS
        PopupMenu popup = new PopupMenu(view.getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.delete) {
                try {
                    Model.currentUser.deleteAlbum(albumName);
                } catch (Exception e) {
                    throw new RuntimeException("Album cannot be deleted");
                }
                return true;
            }
            return false;
        });
        inflater.inflate(R.menu.deleteaction, popup.getMenu());
        popup.show();
        // gotta call function to re-fresh the albums list after deleting
        // gotta make the album_card part of a list, cause right now its just one thing
    }

    private ArrayList<Album> userAlbums;

    public AlbumsList(ArrayList<Album> albums) {
        userAlbums = albums;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public AlbumsList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.album_card, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(AlbumsList.ViewHolder holder, int position) {
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
