package com.example.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class quiz extends Activity {
    Button savebttn;
    Intent intent;
    EditText breed, gender,color, size, age;

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
                intent = new Intent(quiz.this, MainActivity.class); // will change when laurn updates his part
                startActivity(intent);
            }//End of method onClick
        });
    }

}
