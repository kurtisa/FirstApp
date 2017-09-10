package com.example.kurtis.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class teacherAddStudentsActivity extends AppCompatActivity {
    // Array of strings...

    ArrayList<String> students = new ArrayList<>();
    ArrayList<Boolean> boolArray = new ArrayList<>();
    ArrayList<String> mobileArray = new ArrayList<>();

    AutoCompleteTextView mUsernameView;
    //TODO research cursorloader. Also implement the login activity methods for entering a new user.
    private Menu menu;
    private RecyclerView listView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_students);

        //  Toolbar actionBar = (Toolbar) findViewById(R.id.add_students_teacher_toolbar);
        // setSupportActionBar(actionBar);
        getSupportActionBar().setTitle("Add Students");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAdapter = new MyAdapter(mobileArray, boolArray);
        listView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(mAdapter);
        FloatingActionButton addNew = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        listView.setNestedScrollingEnabled(false);

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public boolean isLongPressDragEnabled() {


                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Collections.sort(mobileArray, String.CASE_INSENSITIVE_ORDER);
                if (direction == ItemTouchHelper.LEFT) {
                    int position = viewHolder.getAdapterPosition();
                    String key = mobileArray.get(position);
                    areYouSure(key, position);
                }
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(listView);


        addNew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {

                triggerAlert();

            }
        });
//

    }


    public void areYouSure(final String username, final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Are you sure you wish to delete " + username + "?");

        // Set an EditText view to get user input
        TextView areYouSure = new TextView(this);
        alert.setView(areYouSure);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                DatabaseReference mRef = mRootRef.child("teacher-list").child(teacher_main_menu.username_string);
                mRef.child(username).setValue(null);

                mobileArray.remove(position);
                boolArray.remove(position);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                changeData(mobileArray, boolArray);
            }
        });

        alert.show();

    }

    public void triggerAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Enter Student Username");

        // Set an EditText view to get user input
        mUsernameView = new AutoCompleteTextView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.simple_dropdown, students.toArray((new String[students.size()])));
        mUsernameView.setAdapter(adapter);
        mUsernameView.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        alert.setView(mUsernameView);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                attemptAdd(mUsernameView, alert);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();

    }

    private void attemptAdd(EditText mUsernameView, AlertDialog.Builder alert) {
        Boolean cancel = false;
        // Reset errors.
        mUsernameView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();

        // Check for a valid password, if the user entered one.
        if (mobileArray.contains(username)) {
            mUsernameView.setError(getString(R.string.student_double_entry));
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            cancel = true;
        } else if (!signInValidFunctions.isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            cancel = true;
        } else if (signInValidFunctions.hasIllegalChars(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_entry));
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            Toast.makeText(getApplicationContext(), mUsernameView.getError(), Toast.LENGTH_LONG).show();
            triggerAlert();

        } else {
            // Adding student to teacher's list of students
            final String new_student_username = mUsernameView.getText().toString();
            DatabaseReference userTypeRef = mRootRef.child("teacher-list"); //setting up an index of usernames mapped to uid
            DatabaseReference teacherStudentRef = userTypeRef.child(teacher_main_menu.username_string);
            boolArray.clear();
            teacherStudentRef.child(new_student_username).setValue(false, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Log.d("Data could not be saved ", databaseError.getMessage());
                    }
                }
            });
            // Adding teacher to student's list of students
            DatabaseReference studentList = mRootRef.child("student-list"); //setting up an index of usernames mapped to uid
            DatabaseReference studentTeacherRef = studentList.child(new_student_username);
            studentTeacherRef.child(teacher_main_menu.username_string).setValue(false, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Log.d("Data could not be saved ", databaseError.getMessage());
                    }
                }
            });




        }
    }

    public void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference teacherListRef = mRootRef.child("teacher-list").child(teacher_main_menu.username_string);


        teacherListRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                boolArray.clear();

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    if (mobileArray.contains(childDataSnapshot.getKey())) {
                    } else {
                        mobileArray.add(childDataSnapshot.getKey());
                    }
                    boolArray.add(childDataSnapshot.getValue(Boolean.class));
                }
                changeData(mobileArray, boolArray);
            }

            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

        //retrieve entire list of students
        DatabaseReference studentUsernameList = mRootRef.child("student_users");
        studentUsernameList.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    if (students.contains(childDataSnapshot.getKey())) {
                        // do nothing
                    } else {
                        students.add(childDataSnapshot.getKey());
                    }
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }


    public void changeData(ArrayList mobileArray, ArrayList boolArray) {
        mAdapter = new MyAdapter(mobileArray, boolArray);
        listView = (RecyclerView) findViewById(R.id.my_recycler_view);
        listView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teacher_menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("HOME BUTTION PRESSED", Integer.toString(id));
        //noinspection SimplifiableIfStatement

        if (id == 16908332) {  // home button id
            Intent intent = new Intent(this, teacher_main_menu.class);
            startActivity(intent);
            finish();

            return true;
        }
        if (R.id.action_log_out == id) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        if (R.id.action_settings == id) {
            return true;
        }
        // User chose the "Favorite" action, mark the current item
        // as a favorite...

        return super.onOptionsItemSelected(item);
    }
}