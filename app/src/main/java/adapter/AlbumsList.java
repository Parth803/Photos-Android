package adapter;

import model.Album;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

            albumNameTextView = (TextView) itemView.findViewById(R.id.albumNameTextView);
            openAlbumButton = (Button) itemView.findViewById(R.id.openAlbumButton);
            openAlbumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open(itemView);
                }
            });
            renameOrDeleteAlbumButton = (ImageButton) itemView.findViewById(R.id.renameOrDeleteAlbumButton);
            renameOrDeleteAlbumButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopup(itemView);
                }
            });

        }
    }

    public void open(View view) {
        // NAVIGATE TO NEXT VIEW BY CALLING CHANGE VIEW FUNCTION IN MAIN
    }

    public void showPopup(View view) {
        // OPEN DA POPUP -- WORK IN PROGRESS
    }

    private ArrayList<Album> userAlbums;

    public AlbumsList(ArrayList<Album> albums) {
        userAlbums = albums;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public AlbumsList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
