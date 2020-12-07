package com.example.mainscreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HumanUserSignUpActivity extends AppCompatActivity {
    //Variable Declarations
    Button btnSubmit;
    Cursor cursor;
    DatabaseHelper db;
    EditText etFirst, etLast, etPassword, etRPassword, etEmail;
    String first, last, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_user_sign_up);

        //Variable Initializations
        btnSubmit = findViewById(R.id.btn_submit);
        etFirst = (EditText) findViewById(R.id.et_first_name);
        etLast = (EditText) findViewById(R.id.et_last_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etRPassword = (EditText) findViewById(R.id.et_rPassword);
        etEmail = (EditText) findViewById(R.id.et_email);
        final SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd.db", MODE_PRIVATE, null);
        db = new DatabaseHelper(HumanUserSignUpActivity.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)    {
                cursor = ggdDatabase.rawQuery("SELECT * FROM HumanUsers WHERE FirstName = '" + etFirst.getText().toString() +
                        "' AND LastName = '" + etLast.getText().toString() + "' AND Email = '" + etEmail.getText().toString() + "';", null);
                if (cursor.getCount() > 0)  {
                    Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                }//End of if statement reached when the user is already in the database
                else if (!etPassword.getText().toString().trim().equals(etRPassword.getText().toString().trim()))    {
                    Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                }//End of else statements when passords do no match
                else  if(!etFirst.getText().toString().trim().isEmpty() && !etLast.getText().toString().trim().isEmpty() && !etPassword.getText().toString().trim().isEmpty() &&
                        !etRPassword.getText().toString().trim().isEmpty() && !etEmail.getText().toString().trim().isEmpty())                    {
                    first = etFirst.getText().toString().trim();
                    last = etLast.getText().toString().trim();
                    email = etEmail.getText().toString().trim();
                    pass = etPassword.getText().toString().trim();
                    db.addOneToHumanUser(first, last, email, pass);
                    //ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('" + first + "', '" + last + "', '" + email + "', '" + pass + "');");
                }//End of else clause to add a new restaurant to the list
                else    {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }//End of else when not all fields have values
            }//End of method onClick
        });//End of method btnSubmit.setOnClickListener
    }//End of onCreate
}//End of HumanUserSignUpActivity
