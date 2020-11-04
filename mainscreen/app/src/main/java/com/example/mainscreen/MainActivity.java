package com.example.mainscreen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Variable Declarations
    Intent intent;
    Bundle bundle;
    Cursor cursor;
    Button btnSignIn, btnSignUp;
    EditText etEmail, etPassword;
    Spinner spinnerUserType;
    int userChoice = 0;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable Initializations
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        spinnerUserType = (Spinner) findViewById(R.id.spinner_user_type);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        spinnerUserType.setOnItemSelectedListener(this);
        List<String> userTypes = new ArrayList<String>();
        userTypes.add("Please select a user type");
        userTypes.add("Human User");
        userTypes.add("Shelter");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userTypes);    //for the spinner
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //for the spinner
        spinnerUserType.setAdapter(dataAdapter);    //for the spinner


        //Create a database to hold the tables
        SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd_Database", MODE_PRIVATE, null);
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS HumanUsers(FirstName VARCHAR, LastName VARCHAR, Email VARCHAR, Password VARCHAR, ID VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS dogShelter(ID VARCHAR, ShelterName VARCHAR, LocationPoint VARCHAR,  Email VARCHAR, Phone VARCHAR, Password VARCHAR, ID VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS dogprofile(DogName VARCHAR, Gender VARCHAR,  breed VARCHAR, Age VARCHAR, Color VARCHAR, Size VARCHAR, Bio VARCHAR, ID VARCHAR, ShelterID VARCHAR);");
        cursor = ggdDatabase.rawQuery("SELECT * FROM HumanUsers;", null);
        if (cursor.getCount() == 0) {
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('admin', 'admin', 'amulkey21@yahoo.com', 'admin', '0');");
        }//End of if statement to initially populate the table HumanUsers


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete this switch statement on release
                switch (userChoice) {
                    case 1:
                        intent = new Intent(MainActivity.this, HumanUserWelcomeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, ShelterWelcomeActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select a User Type to continue ", Toast.LENGTH_LONG).show();
                        break;
                }//End of switch to decide with sign up activity to go to

                /* Commented out until release

                email = etEmail.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                if (!email.equals("") && !password.equals(""))  {
                    switch (userChoice) {
                        case 1:
                            //Add SQL query here
                            if (true)   {//Replace true with a check if the email / passwerd combination is in the table Users
                                intent = new Intent(MainActivity.this, HumanUserWelcomeActivity.class);
                                startActivity(intent);
                            } else  {
                                Toast.makeText(getApplicationContext(), "Email / Password not found", Toast.LENGTH_LONG).show();
                            }//End of if-else email and password are in the table
                            break;
                        case 2:
                            //Add SQL query here
                            if (true)   {//Replace true with a check if the email / passwerd combination is in the table Shelters
                                intent = new Intent(MainActivity.this, ShelterWelcomeActivity.class);
                                startActivity(intent);
                            } else  {
                                Toast.makeText(getApplicationContext(), "Email / Password not found", Toast.LENGTH_LONG).show();
                            }//End of if-else email and password are in the table
                            break;
                        default:
                            Toast.makeText(getApplicationContext(), "Please select a User Type to continue ", Toast.LENGTH_LONG).show();
                            break;
                    }//End of switch to decide with sign up activity to go to
                }else   {
                    Toast.makeText(getApplicationContext(), "All fields are required to continue ", Toast.LENGTH_LONG).show();
                }//End of if/else to check that all fields have values
                 */
            }//End of method onClick
        });//End of btnSignIn.setOnClickListener

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (userChoice) {
                    case 1:
                        intent = new Intent(MainActivity.this, HumanUserSignUpActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        //do something for shelter registration
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select a User Type to continue ", Toast.LENGTH_LONG).show();
                        break;
                }//End of switch to decide with sign up activity to go to

            }//End of method onClick
        });//End of btnSignUp.setOnClickListener
    }//End of method onCreate

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        switch (position)   {
            case 1:
                userChoice = 1;
                break;
            case 2:
                userChoice = 2;
                break;
            default:
                userChoice = 0;
                Toast.makeText(parent.getContext(), "Please select a User Type to continue ", Toast.LENGTH_LONG).show();
                break;
        }//End of switch statement that decides wich user type is selected
    }//End of onItemSelected for the spinner
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }//End of onNothingSelected for the spinner
}//End of MainActivity
