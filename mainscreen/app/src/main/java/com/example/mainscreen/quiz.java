package com.example.mainscreen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.DoubleSummaryStatistics;

public class quiz extends Activity {
    Button savebttn;
    Intent intent;
    String breed, gender,color, size, age;
    Cursor cursor;
    SQLiteDatabase ggdDatabase;
    Bundle extras = new Bundle();
    public Cursor returnMatchesIDs(){
        return this.cursor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //save button initization
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        savebttn = (Button) findViewById(R.id.savebttn);

        //spinner entries
        Spinner groupSpinner = (Spinner) findViewById(R.id.groupSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> groupAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_group_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        groupSpinner.setAdapter(groupAdapter);

        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        genderSpinner.setAdapter(genderAdapter);

        Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_color_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorSpinner.setAdapter(colorAdapter);

        Spinner ageSpinner = (Spinner) findViewById(R.id.ageSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> ageAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_age_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ageSpinner.setAdapter(ageAdapter);

        Spinner sizeSpinner = (Spinner) findViewById(R.id.sizeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.dog_size_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sizeSpinner.setAdapter(sizeAdapter);



        breed = (String) groupSpinner.getSelectedItem();
        gender = (String) genderSpinner.getSelectedItem();
        color = (String) colorSpinner.getSelectedItem();
        size = (String) sizeSpinner.getSelectedItem();
        age = (String) ageSpinner.getSelectedItem();


        savebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = DatabaseHelper.getInstance();
                String humanID = dbHelper.getCurrentUser();
                
                ggdDatabase.execSQL("UPDATE HumanQuiz SET " +
                                    "DogGroup = '" + breed +"'," +
                                    "DogAge = '" + age +"'," +
                                    "DogColor = '" + color +"'," +
                                    "DogGender = '" + gender +"'," +
                                    "DogSize = '" + breed + "'" +
                                    "WHERE HumanID = '" + humanID + "'");



                cursor = ggdDatabase.rawQuery("SELECT ID FROM dogProfile WHERE gender = " + gender + " AND breed = " + breed + " AND age = " + age + ";", null);
                String[] dogIDs = new String[cursor.getCount()];
                for(int i = 0; i< cursor.getCount(); i++){
                    dogIDs[i] = cursor.getString(0);
                    cursor.moveToNext();
                }

                ggdDatabase.close();

                extras.putStringArray("Dog IDs", dogIDs);

                intent = new Intent(quiz.this, QuizResultsActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }//End of method onClick
        });
    }

}
