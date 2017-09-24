package com.example.kurtis.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    TextView userName;
    TextView task;
    String username_string;
    String username;
    String nickname;
    String age;
    DatabaseReference mRootRef;
    ArrayList<String> teacherlist = new ArrayList();
    ArrayList<String> studentTasks = new ArrayList();

    Boolean alertBool = true;
    ImageButton noteRhythm;
    ImageButton restRhythm;
    ImageButton faceLearn;
    ImageButton faceTouch;
    ImageButton egbdfLearn;
    ImageButton egbdfTouch;
    ImageButton acegLearn;
    ImageButton gbdfaLearn;
    ImageButton acegTouch;
    ImageButton gbdfaTouch;
    Boolean restRhythmLock = true;
    Boolean faceLearnLock = true;
    Boolean faceTouchLock = true;
    Boolean egbdfLearnLock = true;
    Boolean egbdfTouchLock = true;
    Boolean acegLearnLock = true;
    Boolean acegTouchLock = true;
    Boolean gbdfaLearnLock = true;
    Boolean gbdfaTouchLock = true;
    private Intent intent;
    private Menu menu;
    private Intent login_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main_menu);
        final Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
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
        noteRhythm = (ImageButton) findViewById(R.id.noteRythm);
        restRhythm = (ImageButton) findViewById(R.id.restRhythm);
        faceLearn = (ImageButton) findViewById(R.id.faceLearn);
        faceTouch = (ImageButton) findViewById(R.id.faceTouch);
        egbdfLearn = (ImageButton) findViewById(R.id.egbdfLearn);
        egbdfTouch = (ImageButton) findViewById(R.id.egbdfTouch);
        acegLearn = (ImageButton) findViewById(R.id.acegLearn);
        gbdfaLearn = (ImageButton) findViewById(R.id.gbdfaLearn);
        acegTouch = (ImageButton) findViewById(R.id.acegTouch);
        gbdfaTouch = (ImageButton) findViewById(R.id.gbdfaTouch);

        final Intent login_intent = new Intent(this, LoginActivity.class);
        final Intent teacherAddIntent = new Intent(this, teacherAddStudentsActivity.class);

        final Intent noteRhythmIntent = new Intent(this, rhythmQuestions.class);

        noteRhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                noteRhythmIntent.putExtra("LEVEL", "1");
                startActivity(noteRhythmIntent);
            }
        });
        final Intent restIntent = new Intent(this, rhythmQuestions.class);

        restRhythm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (restRhythmLock) {
                    restIntent.putExtra("LEVEL", "2");
                    startActivity(restIntent);
                }
            }
        });

        final Intent faceIntent = new Intent(this, MainActivity.class);

        faceLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (faceLearnLock) {
                    faceIntent.putExtra("LEVEL", "3");
                    startActivity(faceIntent);
                }
            }

        });

        final Intent faceTouchIntent = new Intent(this, touchQuestions.class);


        faceTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (faceTouchLock) {
                    faceTouchIntent.putExtra("LEVEL", "4");
                    startActivity(faceTouchIntent);
                    variableListener.Connect.setMyBoolean(false);
                }
            }

        });

        final Intent egbdfLearnIntent = new Intent(this, MainActivity.class);

        egbdfLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (egbdfLearnLock) {
                    egbdfLearnIntent.putExtra("LEVEL", "5");
                    startActivity(egbdfLearnIntent);
                }
            }

        });
        final Intent egbdfTouchIntent = new Intent(this, touchQuestions.class);

        egbdfTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (egbdfTouchLock) {
                    variableListener.Connect.setMyBoolean(false);
                    egbdfTouchIntent.putExtra("LEVEL", "6");
                    startActivity(egbdfTouchIntent);
                }
            }
        });
        final Intent gbdfaLearnIntent = new Intent(this, MainActivity.class);

        gbdfaLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (gbdfaLearnLock) {
                    gbdfaLearnIntent.putExtra("LEVEL", "9");
                    startActivity(gbdfaLearnIntent);
                }
            }
        });
        final Intent gbdfaTouchIntent = new Intent(this, touchQuestions.class);

        gbdfaTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (gbdfaTouchLock) {
                    variableListener.Connect.setMyBoolean(false);
                    gbdfaTouchIntent.putExtra("LEVEL", "10");
                    startActivity(gbdfaTouchIntent);
                }
            }
        });
        final Intent acegLearnIntent = new Intent(this, MainActivity.class);

        acegLearn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (acegLearnLock) {
                    acegLearnIntent.putExtra("LEVEL", "7");
                    startActivity(acegLearnIntent);
                }
            }

        });
        final Intent acegTouchIntent = new Intent(this, touchQuestions.class);

        acegTouch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (acegTouchLock) {
                    acegTouchIntent.putExtra("LEVEL", "8");
                    startActivity(acegTouchIntent);
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference taskRef = mRootRef.child("tasks");
        //TODO retrieve teacher tasks
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
                addTeacherListener();
                Log.d("Username retrived:", username_string);
            }


            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void addTeacherListener() {

        DatabaseReference levelRef = mRootRef.child("student_levels").child(username_string);
        final DatabaseReference studentList = mRootRef.child("student-list").child(username_string);
        final DatabaseReference taskList = mRootRef.child("student-tasks").child(username_string).child("exhibitionteacher");

        // Check if student's teacher list has changed
        studentList.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    // if the student's list of teachers does not contain the teacher, or the teacher username value is false (student hasn't accepted request
                    if ((Boolean) childDataSnapshot.getValue() && teacherlist.contains(childDataSnapshot.getKey())) {
                        // if there are teacher's who have been accepted by student; do nothing.
                    } else if (!(Boolean) childDataSnapshot.getValue()) {
                        // else ask student for permission
                        String new_teacher = childDataSnapshot.getKey();
                        trigger_add_teacher(new_teacher);
                    } else if ((Boolean) childDataSnapshot.getValue()) {
                        teacherlist.add(childDataSnapshot.getKey());
                    }
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

        taskList.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    // String leveltodo = childDataSnapshot.getValue(String.class);
                    //String completeInt = childDataSnapshot.child("Complete level").getKey();
                    //String unlockLev = childDataSnapshot.child("Unlock level").getValue(String.class);
                    //String due = childDataSnapshot.child("Due Date").getKey();
                    //trigger_task_list(leveltodo, completeInt, unlockLev, due);

                }
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        levelRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    unlockLevels(childDataSnapshot.getKey(), childDataSnapshot.getValue(Boolean.class));
                }
            }
            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    private void unlockLevels(String key, Boolean value) {
        switch (key) {
            case "level2":
                if (value) {
                    restRhythm.setBackground(getDrawable(R.drawable.restingman));
                    restRhythmLock = true;
                } else {
                    restRhythm.setBackground(getDrawable(R.drawable.grey_restingman));
                    restRhythmLock = false;
                }
                break;
            case "level3":
                if (value) {
                    faceLearn.setBackground(getDrawable(R.drawable.spaces_bird));
                    faceLearnLock = true;
                } else {
                    faceLearn.setBackground(getDrawable(R.drawable.grey_spaces_bird));
                    faceLearnLock = false;
                }
                break;
            case "level4":
                if (value) {
                    faceTouch.setBackground(getDrawable(R.drawable.baby_bird));
                    faceTouchLock = true;
                } else {
                    faceTouch.setBackground(getDrawable(R.drawable.grey_baby_bird));
                    faceTouchLock = false;
                }
                break;
            case "level5":
                if (value) {
                    egbdfLearn.setBackground(getDrawable(R.drawable.lines_bird));
                    egbdfLearnLock = true;
                } else {
                    egbdfLearn.setBackground(getDrawable(R.drawable.grey_lines_bird));
                    egbdfLearnLock = false;
                }
                break;
            case "level6":
                if (value) {
                    egbdfTouch.setBackground(getDrawable(R.drawable.baby_bird));
                    egbdfTouchLock = true;
                } else {
                    egbdfTouch.setBackground(getDrawable(R.drawable.grey_baby_bird));
                    egbdfTouchLock = false;
                }
                break;
            case "level7":
                if (value) {
                    acegLearn.setBackground(getDrawable(R.drawable.spaces_bird));
                    acegLearnLock = true;
                } else {
                    acegLearn.setBackground(getDrawable(R.drawable.grey_spaces_bird));
                    acegLearnLock = false;
                }
                break;
            case "level8":
                if (value) {
                    acegTouch.setBackground(getDrawable(R.drawable.baby_bird));
                    acegTouchLock = true;
                } else {
                    acegTouch.setBackground(getDrawable(R.drawable.grey_baby_bird));
                    acegTouchLock = false;
                }
                break;
            case "level9":
                if (value) {
                    gbdfaLearn.setBackground(getDrawable(R.drawable.lines_bird));
                    gbdfaLearnLock = true;
                } else {
                    gbdfaLearn.setBackground(getDrawable(R.drawable.grey_lines_bird));
                    gbdfaLearnLock = false;
                }
                break;
            case "level10":
                if (value) {
                    gbdfaTouch.setBackground(getDrawable(R.drawable.baby_bird));
                    gbdfaTouchLock = true;
                } else {
                    gbdfaTouch.setBackground(getDrawable(R.drawable.grey_baby_bird));
                    gbdfaTouchLock = false;
                }
                break;
        }
    }

    private void trigger_task_list(String level, String complete, String unlock, String due) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Exhibitionteacher set you new tasks!");

        TextView task1 = new TextView(this);
        alert.setView(task1);
        task1.setPaddingRelative(2, 2, 10, 2);
        task1.setText("Complete " + level + " by " + complete);
        task1.setTextSize(14);

        alert.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.show();
        alertBool = false;
    }
    private void trigger_add_teacher(final String teacher_username) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("A teacher, " + teacher_username + " added you on Thero!");

        alert.setPositiveButton("Accept!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                // turn teacher username value to true in student's teacher list.
                DatabaseReference studentList = mRootRef.child("student-list"); //setting up an index of usernames mapped to uid
                DatabaseReference studentTeacherRef = studentList.child(username_string).child(teacher_username);
                studentTeacherRef.setValue(true, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("Data could not be saved ", databaseError.getMessage());
                        }
                    }
                });

                // add student username value to true in teacher's list
                DatabaseReference teacherListRef = mRootRef.child("teacher-list"); //setting up an index of usernames mapped to uid
                DatabaseReference teacherStudentRef = teacherListRef.child(teacher_username);
                teacherStudentRef.child(username_string).setValue(true, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("Data could not be saved ", databaseError.getMessage());
                        }
                    }
                });
            }

        });

        alert.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // remove teacher from the student's list.
                DatabaseReference studentList = mRootRef.child("student-list"); //setting up an index of usernames mapped to uid
                DatabaseReference studentTeacherRef = studentList.child(username_string).child(teacher_username);
                studentTeacherRef.setValue(null, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null) {
                            Log.d("Data could not be saved ", databaseError.getMessage());
                        }
                    }
                });

            }
        });
        teacherlist.add(teacher_username);
        alert.show();

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

        if (R.id.assignments == id) {

            startActivity(new Intent(this, StudentTasksActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
