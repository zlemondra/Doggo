
package com.example.mainscreen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;


public class DogProfileSubmitActivity extends AppCompatActivity {

    Button btn_submit_dog_profile;
    Cursor cursor;
    SQLiteDatabase ggdDatabase;
    EditText etdogname, etbreed, etcolor, etgender, etage, etsize, etbio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//the values for the submit form
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_profile_sign_up);

        //dog profile variable init

        btn_submit_dog_profile = findViewById(R.id.btn_submit_dog_profile);
        etdogname = findViewById(R.id.et_dogname);
        etbreed = findViewById(R.id.et_dogbreed);
        etcolor = findViewById(R.id.et_dog_color);
        etgender = findViewById(R.id.et_dog_gender);
        etage = findViewById(R.id.et_dog_age);
        etsize = findViewById(R.id.et_dog_size);
        etbio = findViewById(R.id.et_dog_bio);
        final UUID uniquedogID = UUID.randomUUID();
        /*finding a way to create dog ID:
        iterator
        id generator UUID
        */
        btn_submit_dog_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)    {
                cursor = ggdDatabase.rawQuery("SELECT * FROM dogProfile WHERE dogName = " + etdogname.getText().toString() +
                        " AND gender = " + etgender.getText().toString() + " AND breed = " + etbreed.getText().toString() + " AND age = " + etage.getText().toString() + ";", null);
                if (cursor.getCount() > 0)  {
                    Toast.makeText(getApplicationContext(), "Dog already exists", Toast.LENGTH_SHORT).show();
                }//End of if statement reached when the dog is already in the database
                else  if(!etdogname.getText().toString().trim().isEmpty()
                        && !etbreed.getText().toString().trim().isEmpty()
                        && !etcolor.getText().toString().trim().isEmpty()
                        && !etgender.getText().toString().trim().isEmpty()
                        && !etage.getText().toString().trim().isEmpty()
                        && !etsize.getText().toString().trim().isEmpty()
                        && !etbio.getText().toString().trim().isEmpty())                    {
                    ggdDatabase.execSQL("INSERT INTO dogProfile VALUES('" +
                            etdogname.getText().toString().trim()   + "', '" +
                            etbreed.getText().toString().trim()     + "', '" +
                            etcolor.getText().toString().trim()     + "', '" +
                            etgender.getText().toString().trim()     + "', '" +
                            etage.getText().toString().trim()     + "', '" +
                            etsize.getText().toString().trim()     + "', '" +
                            etbio.getText().toString().trim()     + "', '" +
                            uniquedogID.toString().trim()    +
                            ");");
                }//End of else clause to add a new dog to list
                else    {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }//End of else when not all fields have values
            }//End of method onClick
        });//End of method btnSubmit.setOnClickListener
    }//end of onCreate
}//end of dogprofilesubmitactivity



 