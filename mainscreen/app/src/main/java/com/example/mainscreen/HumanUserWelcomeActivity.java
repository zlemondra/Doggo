package com.example.mainscreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HumanUserWelcomeActivity extends AppCompatActivity {
    //Variable Declarations
    Button btnMatchMe;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human_user_welcome);

        //Variable Initializations
        btnMatchMe = (Button) findViewById(R.id.btn_match_me);

        btnMatchMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(HumanUserWelcomeActivity.this, quiz.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnSignUp.setOnClickListener
    }//End of method onCreate
}//End of class HumanUserWelcomeActivity