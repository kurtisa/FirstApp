package com.example.kurtis.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_choice);

        final Intent teacher_sign_up = new Intent (this, Teacher_sign_up.class);
        final Intent student_sign_up = new Intent (this, SignUpActivity.class);



        Button studentButton = (Button) findViewById(R.id.student_button);
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(student_sign_up);
                finish();
            }
        });


        Button teacherButton = (Button) findViewById(R.id.teacher_button);
        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(teacher_sign_up);
                finish();
            }
        });





    }
}
