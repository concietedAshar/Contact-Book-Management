package com.mrash.ashbook;

import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    static int me;
    ItemClicked myActivity;
    Context context;
    private ArrayList<Contacts> contacts;

    public ContactsAdapter(Context context, ArrayList<Contacts> list) {

        myActivity = (ItemClicked) context;
        this.context = context;
        contacts = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        me = position;

        setHolderData(holder, position);

        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:" + contacts.get(position).getMobileNo()));
                context.startActivity(intent1);
            }
        });


        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHandler dbHandler = new DBHandler(context);
                Contacts c = new Contacts();
                c = contacts.get(me);

                dbHandler.deleteContact(c);
                notifyDataSetChanged();
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myActivity.onItemClicked(contacts.indexOf((Contacts) v.getTag()));
            }
        });//setOnClickListener Ends


    }

    private void setHolderData(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(contacts.get(position));
        holder.tvName.setText(contacts.get(position).getName());
        holder.tvEmail.setText(contacts.get(position).getEmail());
        int images[] = Images.getAllImages(); // Public Static Library
        holder.imgContact.setImageResource(images[contacts.get(position).getImageId()]);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public interface ItemClicked {
        void onItemClicked(int index);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgContact, imgDel, imgCall;
        TextView tvName, tvEmail;
        View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            init();
        }//Constructor Ends

        private void init() {
            imgContact = itemView.findViewById(R.id.imgContact);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            imgDel = itemView.findViewById(R.id.imgDel);
            imgCall = itemView.findViewById(R.id.imgCall);
            //Invisible by Default For Main Screen Edit Button
            imgDel.setVisibility(View.INVISIBLE);
            mView = itemView;
        }
    }//viewHolder Class end ->Remind
}
