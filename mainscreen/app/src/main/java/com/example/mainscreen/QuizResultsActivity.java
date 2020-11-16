package com.example.mainscreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class QuizResultsActivity extends AppCompatActivity {
    Cursor dog;
    SQLiteDatabase ggdDatabase;
    String dogID, dogName, dogBreed, dogGender, dogAge;
    List<String> listOfIDs = new ArrayList<>();
    List<DogContent> listOfDogs = new ArrayList<DogContent>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        Intent i = getIntent();
        Bundle ids = i.getExtras();

        for(String key : ids.keySet()){
            listOfIDs.add(ids.getString(key));
        }

        for(int j = 0; j < listOfIDs.size(); j++) {
            dogID = listOfIDs.get(j);

            dog = ggdDatabase.rawQuery("SELECT dogName FROM dogProfile WHERE id = " + dogID, null);
            dogName = dog.toString();

            dog = ggdDatabase.rawQuery("SELECT dogBreed FROM dogProfile WHERE id = " + dogID, null);
            String breed = dog.toString();
            dogBreed = "Breed: " + breed;

            dog = ggdDatabase.rawQuery("SELECT dogGender FROM dogProfile WHERE id = " + dogID, null);
            String gender = dog.toString();
            dogGender = "Gender: " + gender;

            dog = ggdDatabase.rawQuery("SELECT dogAge FROM dogProfile WHERE id = " + dogID, null);
            String age = dog.toString();
            dogAge = "Age: " + age;

            DogContent dog = new DogContent(dogID, dogName, dogBreed, dogGender, dogAge);
            listOfDogs.add(dog);
        }

        ListView newListView = findViewById(R.id.view_results);

        QuizResultRecyclerViewAdapter adapter = new QuizResultRecyclerViewAdapter(this, listOfDogs);
        newListView.setAdapter((ListAdapter) adapter);
    }

}

