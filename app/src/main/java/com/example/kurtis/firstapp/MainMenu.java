package com.example.kurtis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView userName;
    TextView task;
    String username_string;
    private Intent intent;
    private Menu menu;
    private Intent login_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main_menu);
        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        login_intent = new Intent(this, LoginActivity.class);
      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        ImageButton noteRhythm = (ImageButton) findViewById(R.id.noteRythm);
        ImageButton restRhythm = (ImageButton) findViewById(R.id.restRhythm);
        ImageButton faceLearn = (ImageButton) findViewById(R.id.faceLearn);
        ImageButton faceTouch = (ImageButton) findViewById(R.id.faceTouch);
        ImageButton egbdfLearn = (ImageButton) findViewById(R.id.egbdfLearn);
        ImageButton egbdfTouch = (ImageButton) findViewById(R.id.egbdfTouch);


        final Intent login_intent = new Intent(this, LoginActivity.class);
        final Intent teacherAddIntent = new Intent(this, teacherAddStudentsActivity.class);

        final Intent noteRhythmIntent = new Intent(this, rhythmQuestions.class);

        noteRhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                noteRhythmIntent.putExtra("LEVEL", "8");
                startActivity(noteRhythmIntent);
            }

        });
        final Intent restIntent = new Intent(this, rhythmQuestions.class);

        restRhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                restIntent.putExtra("LEVEL", "9");
                startActivity(restIntent);
            }
        });

        final Intent faceIntent = new Intent(this, MainActivity.class);

        faceLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                faceIntent.putExtra("LEVEL", "1");
                startActivity(faceIntent);
            }

        });

        final Intent faceTouchIntent = new Intent(this, touchQuestions.class);


        faceTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                faceTouchIntent.putExtra("LEVEL", "6");
                startActivity(faceTouchIntent);
            }

        });

        final Intent egbdfLearnIntent = new Intent(this, MainActivity.class);

        egbdfLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                egbdfLearnIntent.putExtra("LEVEL", "2");
                startActivity(egbdfLearnIntent);
            }

        });
        final Intent egbdfTouchIntent = new Intent(this, touchQuestions.class);

        egbdfTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                egbdfTouchIntent.putExtra("LEVEL", "5");
                startActivity(egbdfTouchIntent);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference taskRef = mRootRef.child("tasks");
        //TODO retrieve teacher tasks
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference uidRef = mRootRef.child("users").child(uid).child("username");

        uidRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.w("MAIN MENU", (dataSnapshot.getValue(String.class)));

                username_string = dataSnapshot.getValue(String.class);

            }


            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_log_student_out) {
            FirebaseAuth.getInstance().signOut();
            startActivity(login_intent);
            finish();
        }

        if (R.id.action_settings == id) {

            startActivity(new Intent(this, StudentSettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.lvl1) {

        } else if (id == R.id.lvl2) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LEVEL", "2");
            startActivity(intent);
        } else if (id == R.id.lvl3) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LEVEL", "3");
            startActivity(intent);
        } else if (id == R.id.lvl4) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LEVEL", "4");
            startActivity(intent);
        } else if (id == R.id.lvl5) {
            intent = new Intent(this, touchQuestions.class);
            intent.putExtra("LEVEL", "5");
            startActivity(intent);
        } else if (id == R.id.lvl6) {
            intent = new Intent(this, touchQuestions.class);
            intent.putExtra("LEVEL", "6");
            startActivity(intent);
        } else if (id == R.id.lvl7) {
            intent = new Intent(this, touchQuestions.class);
            intent.putExtra("LEVEL", "7");
            startActivity(intent);
        }
        return true;
    }


}
