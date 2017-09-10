
package com.example.kurtis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacher_main_menu extends AppCompatActivity {

    static public String username_string;
    TextView userName;
    TextView task;
    private Intent intent;
    private Menu menu;
    private Button buttonAdd;
    private Button buttonTasks;
    private Button buttonReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_teacher_main_menu);
        Toolbar actionBar = (Toolbar) findViewById(R.id.teacher_toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        buttonAdd = (Button) findViewById(R.id.addStudentsCard);
        buttonTasks = (Button) findViewById(R.id.setTasksCard);
        buttonReview = (Button) findViewById(R.id.reviewProgressCard);
        final Intent login_intent = new Intent(this, LoginActivity.class);
        final Intent teacherAddIntent = new Intent(this, teacherAddStudentsActivity.class);
        final Intent setTasksIntent = new Intent(this, setTasksActivity.class);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                //add intent here
                // startActivity(login_intent);
                startActivity(teacherAddIntent);
                finish();
            }

        });

        buttonTasks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                //add intent here
                startActivity(setTasksIntent);

                finish();
            }

        });

        buttonReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                //add intent here
                // startActivity(login_intent);

                finish();
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
        DatabaseReference uidRef = mRootRef.child("teacher_users").child(uid).child("username");

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
            return true;
        }

        // User chose the "Favorite" action, mark the current item
        // as a favorite...

        return super.onOptionsItemSelected(item);
    }

}
