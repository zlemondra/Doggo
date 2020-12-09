package com.example.mainscreen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShelterProfilePage extends AppCompatActivity {
    SQLiteDatabase ggdDatabase;
    TextView shelterName, shelterEmail, shelterPhone;
    Button newDog, viewDog;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_profile_page);

        final SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd.db", MODE_PRIVATE, null);

        shelterName = findViewById(R.id.shelterName);
        shelterEmail = findViewById(R.id.shelterEmailAddress);
        shelterPhone = findViewById(R.id.shelterPhoneNumber);
        newDog = (Button) findViewById(R.id.createProfileButton);
        viewDog = (Button) findViewById(R.id.viewProfileButton);
        newDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ShelterProfilePage.this, DogProfileSubmitActivity.class);
                ggdDatabase.close();
                startActivity(intent);
            }
        });
        viewDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ShelterProfilePage.this, ViewShelterDogs.class);
                ggdDatabase.close();
                startActivity(intent);
            }
        });
    }
}