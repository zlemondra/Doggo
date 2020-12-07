/*
package com.example.mainscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "ggd_Database.db", null, 1);
    }//End of the default constructor

    @Override
    public void onCreate(SQLiteDatabase ggdDatabase) {
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS HumanUsers(FirstName TEXT, LastName TEXT, Email TEXT, Password TEXT);");//Tables come with a rowid by default
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogShelter(ShelterName TEXT, LocationPoint TEXT,  Email TEXT, Phone TEXT, Password TEXT);");//A rowid is made by default
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogProfile(DogName TEXT, Gender TEXT,  Breed TEXT, Age Integer, Color TEXT, Size TEXT, Bio TEXT, ShelterID INTEGER);");//Use the rowid instead of an id

    }//End of method onCreate to initially create the database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//End of method onUpgrade used when the database is updated



    public boolean addOneToHumanUser(String fn, String ln, String em, String pw)    {
        SQLiteDatabase ggdDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("FirstName", fn);
        cv.put("LastName", ln);
        cv.put("Email", em);
        cv.put("Password", pw);

        ggdDatabase.insert("HumanUsers", null, cv);
        ggdDatabase.close();
        return true;
    }//End of the method addOneToHumanUser

    public boolean addOneToDogShelter(String sn, String lp, String em, String pn, String pw)    {
        SQLiteDatabase ggdDatabase = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();

        cv2.put("ShelterName", sn);
        cv2.put("LocationPoint", lp);
        cv2.put("Email", em);
        cv2.put("Phone", pn);
        cv2.put("Password", pw);

        ggdDatabase.insert("DogShelter", null, cv2);
        ggdDatabase.close();
        return true;
    }//End of the method addOneToDogShelter

    public boolean addOneToDogProfile(String dn, String gender, String breed, String age, String color, String size, String bio, int si)    {
        SQLiteDatabase ggdDatabase = this.getWritableDatabase();
        ContentValues cv3 = new ContentValues();

        cv3.put("DogName", dn);
        cv3.put("Gender", dn);
        cv3.put("Breed", dn);
        cv3.put("Age", dn);
        cv3.put("Color", dn);
        cv3.put("Size", dn);
        cv3.put("Bio", dn);
        cv3.put("ShelterID", dn);

        ggdDatabase.insert("DogProfile", null, cv3);
        ggdDatabase.close();
        return true;
    }//End of the method addOneToDogProfile
}//End of class DatabaseHelper

 */