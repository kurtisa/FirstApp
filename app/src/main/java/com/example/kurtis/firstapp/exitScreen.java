package com.example.kurtis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class exitScreen extends AppCompatActivity {
    Button retry;
    Button main;
    TextView accuracyText;
    TextView message;
    ImageView nextLevel;
    DatabaseReference mRootRef;
    String username_string;
    String level;
    String accuracy;
    Boolean nextLevelUnlocked;
    int nextLevelNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_screen);

        level = getIntent().getStringExtra("Level");
        accuracy = getIntent().getStringExtra("Accuracy");
        nextLevel = (ImageView) findViewById(R.id.nextLevel);
        accuracyText = (TextView) findViewById(R.id.accuracy);
        message = (TextView) findViewById(R.id.message);
        retry = (Button) findViewById(R.id.retry);
        main = (Button) findViewById(R.id.mainmenu);
        accuracyText.setText(accuracy + "%");
        message.setText("");
        message.setVisibility(View.INVISIBLE);
        nextLevel.setVisibility(View.INVISIBLE);


        final Intent again = new Intent(this, rhythmQuestions.class);
        again.putExtra("LEVEL", level);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(again);
                finish();
            }
        });


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onStart() {
        super.onStart();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference uidRef = mRootRef.child("users").child(uid).child("username");
        boolean gotUsername;
        // Get username, age and nickname
        uidRef.addValueEventListener(new ValueEventListener() {

            // get username
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.w("MAIN MENU", (dataSnapshot.getValue(String.class)));
                username_string = dataSnapshot.getValue(String.class);
                getLevelInfo(username_string);
                Log.d("Username retrived:", username_string);
            }


            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void getLevelInfo(String username_string) {
        String thisLevel = level;
        nextLevelNum = Integer.parseInt(thisLevel);
        nextLevelNum++; // we want to know if the level above this one is unlocked!
        DatabaseReference levelRef = mRootRef.child("student_levels").child(username_string).child("level" + nextLevelNum);
        levelRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                nextLevelUnlocked = dataSnapshot.getValue(Boolean.class);
                displayMessage();
            }

            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });


    }

    private void displayMessage() {
        int accuracyInt = Integer.parseInt(accuracy);
        if ((accuracyInt < 90) && !nextLevelUnlocked) {
            message.setText("Try again! You need 90% accuracy to unlock the next level.");
            message.setVisibility(View.VISIBLE);
        } else if ((accuracyInt >= 90) && !nextLevelUnlocked) {
            message.setText("Well done! You have unlocked Level " + nextLevelNum);
            message.setVisibility(View.VISIBLE);
            unlockLevel(nextLevelNum);
        }

    }

    private void unlockLevel(int nextLevelNum) {
        DatabaseReference levelRef = mRootRef.child("student_levels").child(username_string).child("level" + nextLevelNum);

        levelRef.setValue(true, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                }
            }
        });


    }
}
