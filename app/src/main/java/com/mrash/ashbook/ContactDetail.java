package com.mrash.ashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactDetail extends AppCompatActivity {

    Contacts editContacts;
    ImageView imgCContact;
    TextView tvCContactName;
    TextView etCEmail, etCPhoneNo, etCAddress, etCDOB;
    FloatingActionButton fabBack, fabEditContact, fabCall;
    int index;
    int contactIdToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        contactIdToEdit = getIntent().getIntExtra("contact", -1); //give id of editing contact
        //position of item on which item clicked
        index = getIntent().getIntExtra("position", -1);

        init();
        editContacts = new Contacts();
        DBHandler dbHandler = new DBHandler(this);
        editContacts = dbHandler.getContact(contactIdToEdit); //get Contact to edit on which clicked
        setData(); //set Data on Detail Activity XML
        buttonClick();

    }

    private void buttonClick() {

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ContactDetail.this, com.mrash.ashbook.MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        //functionality  of Call Intent
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:" + editContacts.getMobileNo()));
                startActivity(intent1);
            }
        });

        //Functionality of EditContact
        fabEditContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ContactDetail.this, com.mrash.ashbook.EditExistingContacts.class);
                intent2.putExtra("contactID", editContacts.getId());
                intent2.putExtra("positionEdit", index);
                startActivity(intent2);
                finish();
            }
        });


    }

    private void setData() {
        int[] images = Images.getAllImages();
        imgCContact.setImageResource(images[editContacts.getImageId()]);
        tvCContactName.setText(editContacts.getName());
        etCEmail.setText(editContacts.getEmail());
        etCPhoneNo.setText(editContacts.getMobileNo());
        etCAddress.setText(editContacts.getAddress());
        etCDOB.setText(editContacts.getDateOfBirth());
    }

    private void init() {
        tvCContactName = findViewById(R.id.tvCContactName);
        imgCContact = findViewById(R.id.imgCContact);
        etCEmail = findViewById(R.id.etCEmail);
        etCPhoneNo = findViewById(R.id.etCPhoneNo);
        etCAddress = findViewById(R.id.etCAddress);
        etCDOB = findViewById(R.id.etCDOB);
        fabBack = findViewById(R.id.fabBack);
        fabEditContact = findViewById(R.id.fabEditContact);
        fabCall = findViewById(R.id.fabCall);
    }
}