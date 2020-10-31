package com.example.mainscreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShelterSignUpActivity extends AppCompatActivity {
    //Variable Declarations
    Button createAccountButton;
    Cursor cursor;
    SQLiteDatabase ggdDatabase;
    EditText shelterName, shelterAddress, shelterPhone, shelterEmail, shelterPassword, retypeShelterPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelter_sign_up);

        createAccountButton = findViewById(R.id.createAccountButton);
        shelterName = (EditText) findViewById(R.id.shelterNameInput);
        shelterAddress = (EditText) findViewById(R.id.shelterAddressInput);
        shelterPhone = (EditText) findViewById(R.id.shelterPhoneNumberInput);
        shelterEmail = (EditText) findViewById(R.id.shelterEmailInput);
        shelterPassword = (EditText) findViewById(R.id.shelterPasswordInput);
        retypeShelterPassword = (EditText) findViewById(R.id.reenterShelterPasswordInput);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)    {
                cursor = ggdDatabase.rawQuery("SELECT * FROM dogShelters WHERE shelterName = " + shelterName.getText().toString() +
                        " AND locationPoint = " + shelterAddress.getText().toString() + " AND phone = " + shelterPhone.getText().toString() + " AND Email = " + shelterEmail.getText().toString() + ";", null);
                if (cursor.getCount() > 0)  {
                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                }
                else if (!shelterPassword.getText().toString().trim().equals(retypeShelterPassword.getText().toString().trim()))    {
                    Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                }
                else  if(!shelterName.getText().toString().trim().isEmpty()  && !shelterAddress.getText().toString().trim().isEmpty() && !shelterPhone.getText().toString().trim().isEmpty() &&
                        !shelterEmail.getText().toString().trim().isEmpty() && !shelterPassword.getText().toString().trim().isEmpty() && !retypeShelterPassword.getText().toString().trim().isEmpty())                    {
                    ggdDatabase.execSQL("INSERT INTO dogShelters VALUES('" + shelterName.getText().toString().trim() + "', '" + shelterAddress.getText().toString().trim() + "', '" + shelterPhone.getText().toString().trim() + "', '" + shelterEmail.getText().toString().trim() +
                            "', '" + shelterPassword.getText().toString().trim() + ");");
                }
                else    {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }//End of else when not all fields have values
            }//End of method onClick
        });//End of method btnSubmit.setOnClickListener
    }
}
