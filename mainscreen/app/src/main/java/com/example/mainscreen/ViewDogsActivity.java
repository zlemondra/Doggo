package com.example.mainscreen;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
    TextView tvViewDogName, tvViewDogAge, tvViewDogGender, tvViewDogBreed, tvViewDogColor, tvViewDogSize, tvViewDogBio;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dogs);

        //Variable Initializations
        SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd_Database", MODE_PRIVATE, null);
        ivDogImage = (ImageView) findViewById(R.id.iv_dog_image);
        tvViewDogName = (TextView) findViewById(R.id.tv_view_dog_name);
        tvViewDogAge = (TextView) findViewById(R.id.tv_view_dog_age);
        tvViewDogGender = (TextView) findViewById(R.id.tv_view_dog_gender);
        tvViewDogBreed = (TextView) findViewById(R.id.tv_view_dog_breed);
        tvViewDogColor = (TextView) findViewById(R.id.tv_view_dog_color);
        tvViewDogSize = (TextView) findViewById(R.id.tv_view_dog_size);
        tvViewDogBio = (TextView) findViewById(R.id.tv_view_dog_bio);
        orientation = getResources().getConfiguration().orientation;
        bundle = new Bundle();
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_list)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_infor)))
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
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_list_)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.fragment_dog_infor)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the list fragment

        tvViewDogName.setText(cursor.getString(0));
        tvViewDogGender.setText(cursor.getString(1));
        tvViewDogBreed.setText(cursor.getString(2));
        tvViewDogAge.setText(cursor.getString(3));
        tvViewDogColor.setText(cursor.getString(4));
        tvViewDogSize.setText(cursor.getString(5));
        tvViewDogBio.setText(cursor.getString(6));

    }//End of method onItemSelected

}//End of class ViewDogsActivity