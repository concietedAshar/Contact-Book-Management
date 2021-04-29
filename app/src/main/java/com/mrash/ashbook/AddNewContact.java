package com.mrash.ashbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class AddNewContact extends AppCompatActivity {

    //public static int index;
    DBHandler dbHandler;
    ImageView imgAddContact;
    EditText etAddName, etAddEmail, etAddPhoneNo, etAddAddress, etAddDOB;
    Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        init();


        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isValidate()) {

                    //Random Image Picker From Stating Image Library to New Contacts
                    int images[] = Images.getAllImages();
                    Random random = new Random();
                    int index = random.nextInt(images.length); // give Random index relative to Image Library
                    imgAddContact.setImageResource(images[index]); // set Image

                    Contacts contacts;
                    contacts = new Contacts(index, etAddName.getText().toString(), etAddPhoneNo.getText().toString()
                            , etAddEmail.getText().toString(), etAddAddress.getText().toString()
                            , etAddDOB.getText().toString());

                    //String name = etAddName.getText().toString();
                    //String email = etAddEmail.getText().toString();
                    //  String phoneNo = etAddPhoneNo.getText().toString();
                    // String address = etAddAddress.getText().toString();
                    // String dob = etAddDOB.getText().toString();

                    //Add Contact - in Database
                    dbHandler = new DBHandler(AddNewContact.this);
                    dbHandler.addContacts(contacts);

                    startActivity(new Intent(AddNewContact.this, com.mrash.ashbook.MainActivity.class));
                    finish();

                }

            }
        });
    }

    boolean isValidate() {
        boolean flag = true;

        String name = etAddName.getText().toString();
        String email = etAddEmail.getText().toString();
        String phoneNo = etAddPhoneNo.getText().toString();
        String address = etAddAddress.getText().toString();
        String dob = etAddDOB.getText().toString();

        if (name.isEmpty() || name.equals(" ")) {
            etAddName.setError("Name");
            flag = false;
        }
        if (email.isEmpty() || !email.contains("@") || email.equals(" ")) {
            etAddEmail.setError("Email");
            flag = false;
        }
        if (phoneNo.isEmpty() || phoneNo.equals(" ")) {
            etAddPhoneNo.setError("Phone No");
            flag = false;
        }
        if (address.isEmpty() || address.equals(" ")) {
            etAddAddress.setError("Address");
            flag = false;
        }
        if (dob.isEmpty() || dob.equals(" ")) {
            etAddDOB.setError("Date of Birth");
            flag = false;
        }

        return flag;
    }

    private void init() {
        etAddName = findViewById(R.id.etAddName);
        etAddEmail = findViewById(R.id.etAddEmail);
        etAddPhoneNo = findViewById(R.id.etAddPhoneNo);
        etAddAddress = findViewById(R.id.etAddAddress);
        etAddDOB = findViewById(R.id.etAddDOB);
        imgAddContact = findViewById(R.id.imgAddContact);
        btnAddContact = findViewById(R.id.btnAddContact);

    }
}