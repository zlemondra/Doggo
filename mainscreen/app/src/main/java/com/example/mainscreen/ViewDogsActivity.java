package com.example.mainscreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Objects;

public class ViewDogsActivity extends AppCompatActivity implements DogListFragment.ItemSelected {
    //Variable Declarations
    Intent intent;
    ArrayList<String> dogNames;
    FragmentManager manager;
    int orientation;
    String dName;
    SQLiteDatabase ggdDatabase;
    Cursor cursor;
    Bundle bundle;
    Fragment listFragment;  //dog list fragment
    //dog_infor Fragment Objects
    ImageView ivDogImage;
    TextView tvViewDogName, tvViewDogAge, tvViewDogGender, tvViewDogBreed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dogs);

        //Variable Initializations
        SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd_Database", MODE_PRIVATE, null);
        tvViewDogName = (TextView) findViewById(R.id.dogProfileName);
        tvViewDogAge = (TextView) findViewById(R.id.dogProfileAge);
        tvViewDogGender = (TextView) findViewById(R.id.dogProfileGender);
        tvViewDogBreed = (TextView) findViewById(R.id.dogProfileBreed);
        orientation = getResources().getConfiguration().orientation;
        bundle = new Bundle();
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_list)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_profile_item)))
                    .commit();
        }//End of if statement to hide the details fragment

        //Populate the list of dog names
        cursor = ggdDatabase.rawQuery("SELECT * FROM DogProfile ORDER BY DogName;", null);
        if (cursor.moveToFirst()) {
            do {
                dogNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }//End of if statement to get populate a list of restarants

        //Send the ArrayList to the fragment
        bundle.putStringArrayList("names", dogNames);
        listFragment.setArguments(bundle);
    }//End of method onCreate

    @Override
    public void onItemSelected(int index) {
        dName = dogNames.get(index);
        cursor = ggdDatabase.rawQuery("SELECT * FROM Restaurants WHERE Name = " + dName + ";", null);

        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {   //If in portrait mode
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_list)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_profile_item)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the list fragment

        tvViewDogName.setText(cursor.getString(0));
        tvViewDogGender.setText(cursor.getString(1));
        tvViewDogBreed.setText(cursor.getString(2));
        tvViewDogAge.setText(cursor.getString(3));


    }//End of method onItemSelected

}//End of class ViewDogsActivity