package com.mrash.ashbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.security.ConfirmationNotAvailableException;
import android.widget.Toast;

import java.util.ArrayList;


public class DBHandler extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "ContactDB";
    private static final String CONTACTS_TABLE = "contacts";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String NUMBER = "mobileNo";
    private static final String EMAIL = "email";
    private static final String ADDRESS = "Address";
    private static final String DATE = "dateOfBirth";
    private static final String IMAGE_ID = "imageId";

    public DBHandler(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLES =
                "CREATE TABLE " + CONTACTS_TABLE
                        + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + IMAGE_ID + " INTEGER, "
                        + NAME + " TEXT, "
                        + NUMBER + " TEXT, "
                        + EMAIL + " TEXT, "
                        + ADDRESS + " TEXT, "
                        + DATE + " TEXT )";

        db.execSQL(CREATE_CONTACTS_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + CONTACTS_TABLE;
        db.execSQL(sql);
        onCreate(db);

    }

    public void addContacts(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE_ID, contacts.getImageId());
        contentValues.put(NAME, contacts.getName());
        contentValues.put(NUMBER, contacts.getMobileNo());
        contentValues.put(EMAIL, contacts.getEmail());
        contentValues.put(ADDRESS, contacts.getAddress());
        contentValues.put(DATE, contacts.getDateOfBirth());


        sqLiteDatabase.insert(CONTACTS_TABLE, null, contentValues);
        sqLiteDatabase.close();
    }

    public Contacts getContact(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                 CONTACTS_TABLE,
                new String[]{ID, IMAGE_ID, NAME, NUMBER, EMAIL, ADDRESS, DATE},
                ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null
        );

        Contacts contacts;
        if (cursor != null) {
            cursor.moveToFirst();
            contacts = new Contacts((cursor.getInt(0))
                    , cursor.getInt(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6));
            return contacts;
        } else {
            return null;
        }
    }

    public ArrayList<Contacts> getAllContacts() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Contacts> contacts = new ArrayList<>();
        String query = "SELECT * FROM " + CONTACTS_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Contacts contact = new Contacts();
                contact.setId(cursor.getInt(0));
                contact.setImageId(cursor.getInt(1));
                contact.setName(cursor.getString(2));
                contact.setMobileNo(cursor.getString(3));
                contact.setEmail(cursor.getString(4));
                contact.setAddress(cursor.getString(5));
                contact.setDateOfBirth(cursor.getString(6));
                contacts.add(contact);
            }
            while (cursor.moveToNext());
        }
        return contacts;
    }

    public int updateContact(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(IMAGE_ID, contacts.getImageId());
        contentValues.put(NAME, contacts.getName());
        contentValues.put(NUMBER, contacts.getMobileNo());
        contentValues.put(EMAIL, contacts.getEmail());
        contentValues.put(ADDRESS, contacts.getAddress());
        contentValues.put(DATE, contacts.getDateOfBirth());

//when edit please change the ID+"=?" to "Iid=?" This was error maybe
        return sqLiteDatabase.update
                (CONTACTS_TABLE,
                        contentValues,
                        ID + "=?",
                        new String[]{String.valueOf(contacts.getId())}
                );
    }

//when edit please change the ID+"=?" to "id=?" This was error maybe and this.getWritableDatabase()
    public void deleteContact(Contacts contacts) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        sqLiteDatabase.delete(CONTACTS_TABLE, ID + "=?", new String[]{String.valueOf(contacts.getId())});
        sqLiteDatabase.close();
    }

    public int getContactCount() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + CONTACTS_TABLE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor.getCount();
    }


}
