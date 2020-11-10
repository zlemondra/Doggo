package com.example.mainscreen;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShelterProfilePage extends AppCompatActivity {
    SQLiteDatabase ggdDatabase;
    TextView shelterName, shelterEmail, shelterPhone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_profile_page);

        shelterName = findViewById(R.id.shelterName);
        shelterEmail = findViewById(R.id.shelterEmailAddress);
        shelterPhone = findViewById(R.id.shelterPhoneNumber);


    }
}