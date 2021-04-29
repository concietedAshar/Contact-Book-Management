package com.mrash.ashbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavContactsAdapter extends RecyclerView.Adapter<FavContactsAdapter.ViewHolder> {


    private final ArrayList<Contacts> contacts;

    public FavContactsAdapter(Context context, ArrayList<Contacts> list) {
        contacts = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_items, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNameFav.setText(contacts.get(position).getName());
        int[] images = Images.getAllImages(); // Public Static Library
        holder.imgFav.setImageResource(images[contacts.get(position).getImageId()]);
        holder.imgDelFav.setVisibility(View.INVISIBLE);
        holder.imgDelFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFav, imgDelFav;
        TextView tvNameFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFav = itemView.findViewById(R.id.imgFav);
            tvNameFav = itemView.findViewById(R.id.tvNameFav);
            imgDelFav = itemView.findViewById(R.id.imgDelFav);

        }//Constructor Ends
    }//viewHolder Class end ->Remind
}
