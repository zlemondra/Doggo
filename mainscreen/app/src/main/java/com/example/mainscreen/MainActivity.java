package com.example.mainscreen;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.mainscreen.DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

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
        final SQLiteDatabase ggdDatabase = openOrCreateDatabase("ggd.db", MODE_PRIVATE, null);

        ggdDatabase.execSQL("DROP TABLE HumanUsers;");
        ggdDatabase.execSQL("DROP TABLE DogShelter;");
        ggdDatabase.execSQL("DROP TABLE DogProfile;");

        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS HumanUsers(FirstName VARCHAR, LastName VARCHAR, Email VARCHAR, Password VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogShelter(ShelterName VARCHAR, LocationPoint VARCHAR,  Email VARCHAR, Phone VARCHAR, Password VARCHAR, ID VARCHAR);");
        ggdDatabase.execSQL("CREATE TABLE IF NOT EXISTS DogProfile(DogName VARCHAR, Gender VARCHAR,  breed VARCHAR, Age VARCHAR, Color VARCHAR, Size VARCHAR, Bio VARCHAR, ID VARCHAR, ShelterID VARCHAR);");
        cursor = ggdDatabase.rawQuery("SELECT * FROM HumanUsers;", null);
        if (cursor.getCount() == 0) {
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('admin', 'admin', 'amulkey21@yahoo.com', 'admin');");
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('David', 'Wilson', 'dawil22@yahoo.com', 'righteousArk24');");
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('Rachel', 'Levi', 'rach@yahoo.com', 'fThE27og');");
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('Clark', 'Lewis', 'clle99@yahoo.com', 'LongBay28');");
            ggdDatabase.execSQL("INSERT INTO HumanUsers VALUES('Donna', 'Bevins', 'dbevins@gmail.com', 'Donna1234');");
        }//End of if statement to initially populate the table HumanUsers
        cursor = ggdDatabase.rawQuery("SELECT * FROM DogShelter;", null);
        if (cursor.getCount() != 1) {
            ggdDatabase.execSQL("INSERT INTO DogShelter VALUES('Seal Beach Animal Care', '1700 Adolfo Lopez dr, Seal Beach, CA 90740', 'contact@sbacc.org', '5624304993', 'sbaccAdmin', '1');");
            ggdDatabase.execSQL("INSERT INTO DogShelter VALUES('Long Beach Animal Control', '7700 E. Spring St, Long Beach, CA 90815', 'animalcare@longbeach.gov', '5625703053', 'lbcAc', '2');");
            ggdDatabase.execSQL("INSERT INTO DogShelter VALUES('WAGS Pet Adoption', '6621 Westminster ave, Westminster, CA 92683', 'michellerusill@gmail.com', '7148876156', 'WAGSmr01', '3');");
            ggdDatabase.execSQL("INSERT INTO DogShelter VALUES('Animal Assistance League', '15102 Jackson st, Midway City, CA 92655', 'aaloc@aaloc.org', '7148934393', 'AaLoC1234', '4');");
            ggdDatabase.execSQL("INSERT INTO DogShelter VALUES('OC Small Paws', '17870 Newhope st ste 104-138, Fountain Valley, CA 92708', 'ocsp.adoptions@gmail.com', '7148154300', 'ocSPlewis22', '5');");
        }//End of if statement to initially populate the table HumanUsers
        cursor = ggdDatabase.rawQuery("SELECT * FROM DogProfile;", null);
        if (cursor.getCount() != 1) {
            ggdDatabase.execSQL("INSERT INTO DogProfile VALUES('Panda', 'Male', 'Chihuahua', '8', 'Black, White', 'Small', '', '1', '3');");
            ggdDatabase.execSQL("INSERT INTO DogProfile VALUES('Atticus', 'Male', 'German Shepherd', '9', 'Brown, Black', 'Extra Large', '', '2', '3');");
            ggdDatabase.execSQL("INSERT INTO DogProfile VALUES('Bean', 'Male', 'Pit Bull Terrier / Greyhound', '3', 'Black, White/Cream', 'Medium', '', '3', '1');");
            ggdDatabase.execSQL("INSERT INTO DogProfile VALUES('Brandon', 'Male', 'Chihuahua', '1', 'White', 'Small', '', '4', '1');");
            ggdDatabase.execSQL("INSERT INTO DogProfile VALUES('Brandy', 'Female', 'Mixed Breed', '4', 'Black, White', 'Medium', '', '5', '1');");
        }//End of if statement to initially populate the table HumanUsers

        // Remove for Mario
        /*
        final DatabaseHelper dbHelper = DatabaseHelper.getInstance();
        dbHelper.onCreate(ggdDatabase);
        dbHelper.importSampleData(ggdDatabase);
        */


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Pull string entries
                    email = etEmail.getText().toString().trim();
                    String passwordIn = etPassword.getText().toString().trim();

                    // Query database
                    String query = "SELECT Email, Password FROM HumanUsers WHERE Email = '" + email +"';";
                    cursor = ggdDatabase.rawQuery(query, null);
                    cursor.moveToFirst();

                    //password = cursor.getString(2); //from when i had humanID
                    password = cursor.getString(1);

                    if (passwordIn.equals(password)) {
                        //dbHelper.setCurrentUser(cursor.getString(0));
                        switch (userChoice) {
                            case 1:
                                //Add SQL query here
                                if (true) {//Replace true with a check if the email / passwerd combination is in the table Users
                                    intent = new Intent(MainActivity.this, HumanUserWelcomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Email / Password not found", Toast.LENGTH_LONG).show();
                                }//End of if-else email and password are in the table
                                break;
                            case 2:
                                //Add SQL query here
                                if (true) {//Replace true with a check if the email / passwerd combination is in the table Shelters
                                    intent = new Intent(MainActivity.this, ShelterProfilePage.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Email / Password not found", Toast.LENGTH_LONG).show();
                                }//End of if-else email and password are in the table
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Please select a User Type to continue ", Toast.LENGTH_LONG).show();
                                break;
                        }//End of switch to decide with sign up activity to go to
                    } else {
                        Toast.makeText(getApplicationContext(), "All fields are required to continue ", Toast.LENGTH_LONG).show();
                    }//End of if/else to check that all fields have values
                } catch (NullPointerException npe) {
                    Toast.makeText(getApplicationContext(), "Incorrect Login", Toast.LENGTH_LONG).show();
                } catch (CursorIndexOutOfBoundsException cioobe){
                    Toast.makeText(getApplicationContext(), "Incorrect Login", Toast.LENGTH_LONG).show();
                }
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
                        intent = new Intent(MainActivity.this, ShelterSignUpActivity.class);
                        startActivity(intent);
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
