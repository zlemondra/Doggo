package com.example.mainscreen;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.DoubleSummaryStatistics;

public class quiz extends Activity {
    Button savebttn;
    Intent intent;
    EditText breed, gender,color, size, age;
    Cursor cursor;
    SQLiteDatabase ggdDatabase;
    Bundle extras = new Bundle();
    public Cursor returnMatchesIDs(){
        return this.cursor;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //save button initization
        savebttn = (Button) findViewById(R.id.savebttn);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //textbox entries
        breed = (EditText) findViewById(R.id.breedInput);
        gender = (EditText) findViewById(R.id.genderInput);
        color = (EditText) findViewById(R.id.colorInput);
        size = (EditText) findViewById(R.id.sizeInput);
        age = (EditText) findViewById(R.id.ageInput);
        savebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor = ggdDatabase.rawQuery("SELECT ID FROM dogProfile WHERE gender = " + gender.getText().toString() + " AND breed = " + breed.getText().toString() + " AND age = " + age.getText().toString() + ";", null);
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
