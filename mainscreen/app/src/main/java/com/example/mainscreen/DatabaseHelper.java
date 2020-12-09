package com.example.mainscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static String currentUser = null;
    public static DatabaseHelper instance = new DatabaseHelper(null);

    public static DatabaseHelper getInstance(){
        return instance;
    }

    public static void setCurrentUser(String user){
        currentUser = user;
    }

    public static String getCurrentUser(){
        return currentUser;
    }

    private DatabaseHelper(@Nullable Context context) {
        super(context, "ggd.db", null, 1);
    }//End of the default constructor

    public void importSampleData(SQLiteDatabase ggdDatabase){
        ggdDatabase.execSQL("INSERT INTO HumanUsers (HumanID, FirstName, LastName, Email, Password) VALUES" +
                            "('0', 'Mario', 'Bajenting', 'mbajenting@gmail.com', 'mario123'), " +
                            "('1', 'admin', 'admin', 'amulkey21@yahoo.com', 'admin'), " +
                            "('2', 'Lauren', 'Dyson', 'ldyson@live.com', 'lauren456'), " +
                            "('3', 'Celeste', 'Tubon', 'ctubon@aol.com', 'celeste789');");
        ggdDatabase.execSQL("INSERT INTO HumanQuiz (HumanID, DogGroup, DogAge, DogColor, DogGender, DogSize) VALUES" +
                            "('0', 'No Preference', 'No Preference', 'No Preference', 'No Preference', 'No Preference')," +
                            "('1', 'No Preference', 'No Preference', 'No Preference', 'No Preference', 'No Preference')," +
                            "('2', 'No Preference', 'No Preference', 'No Preference', 'No Preference', 'No Preference')," +
                            "('3', 'No Preference', 'No Preference', 'No Preference', 'No Preference', 'No Preference');");
    }

    @Override
    public void onCreate(SQLiteDatabase ggdDatabase) {
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS HumanUsers(HumanID VARCHAR, FirstName VARCHAR, LastName VARCHAR, Email VARCHAR, Password VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogShelter(ShelterName VARCHAR, LocationPoint VARCHAR,  Email VARCHAR, Phone VARCHAR, Password VARCHAR, ID VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogProfile(DogName VARCHAR, Gender VARCHAR,  breed VARCHAR, Age VARCHAR, Color VARCHAR, Size VARCHAR, Bio VARCHAR, ID VARCHAR, ShelterID VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS HumanQuiz(HumanID VARCHAR, DogGroup VARCHAR, DogAge VARCHAR, DogColor VARCHAR, DogGender VARCHAR, DogSize VARCHAR);");
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

        cv2.put("ShelterName", "'"+sn+"'");
        cv2.put("LocationPoint", "'"+lp+"'");
        cv2.put("Email", "'"+em+"'");
        cv2.put("Phone", "'"+pn+"'");
        cv2.put("Password", "'"+pw+"'");

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