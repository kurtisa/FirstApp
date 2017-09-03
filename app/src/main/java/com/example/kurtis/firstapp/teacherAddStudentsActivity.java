package com.example.kurtis.firstapp;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class teacherAddStudentsActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    // Array of strings...

    //TODO research cursorloader. Also implement the login activity methods for entering a new user.

    List<String> mobileArray = new ArrayList<String>();
    String username_string;
    SimpleCursorAdapter adapter;
    EditText mUsernameView;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_students);


        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.add_students_listview, mobileArray.toArray());
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        Button addNew = findViewById(R.id.add_student_button);
        mUsernameView = findViewById(R.id.add_student_field);
        String new_student_username = mUsernameView.getText().toString();
        Log.d("eror ", new_student_username);

        addNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {

                DatabaseReference userTypeRef = mRootRef.child("teacher-list"); //setting up an index of usernames mapped to uid
                DatabaseReference teacherStudentRef = userTypeRef.child("hi"); //TODO change to teacher username
                teacherStudentRef.child("HEARMEROAR").setValue("student", new DatabaseReference.CompletionListener() { //TODO change to student username
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("Data could not be saved ", databaseError.getMessage());
                        }
                    }

                });


            }

        });


    }

    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = mRootRef.child("teacher_users").child(uid).child("username");

        uidRef.addListenerForSingleValueEvent(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.w("MAIN MENU", (dataSnapshot.getValue(String.class)));

                username_string = dataSnapshot.getValue(String.class);

            }


            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });
        username_string = "teachertest3";
        DatabaseReference teacherListRef = mRootRef.child("teacher-list").child(username_string);

        teacherListRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.w("MAIN MENU", (dataSnapshot.getValue(String.class)));

                Object hi = dataSnapshot.getValue();
                String string = hi.toString();
                Log.d("hey faggot", string);
                mobileArray.add(string);

            }


            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}