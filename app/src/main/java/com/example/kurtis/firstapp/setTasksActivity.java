package com.example.kurtis.firstapp;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class setTasksActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Array of strings...

    public static ArrayList<String> studentCheckedList = new ArrayList<>();
    private static Button setDueDateButton;
    private static int month;
    private static int day;
    private static int year;
    EditText mUsernameView;
    EditText repeats;
    private ArrayList<String> mobileArray = new ArrayList<>();
    private ArrayList<Boolean> boolArray = new ArrayList<>();
    //TODO research cursorloader. Also implement the login activity methods for entering a new user.
    private Menu menu;
    private RecyclerView listView;
    private TextView explaination;
    private ImageView preview;
    private Spinner options;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private boolean toggleFlag;
    private ToggleButton toggle;

    public static void setDueDate(int d, int m, int y) {
        day = d;
        month = m;
        year = y;
        setDueDateButton.setText(Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tasks);

        //keyboard should only pop up when edittext is clicked!
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAdapter = new SetTasksAdapter(mobileArray, boolArray);
        listView = (RecyclerView) findViewById(R.id.studentCheckboxList);
        options = (Spinner) findViewById(R.id.taskSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tasks_array, R.layout.simple_dropdown);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_dropdown);
        // Apply the adapter to the spinner
        options.setAdapter(adapter);
        options.setOnItemSelectedListener(this);

        preview = (ImageView) findViewById(R.id.taskPreview);
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listView.setAdapter(mAdapter);
        listView.setNestedScrollingEnabled(true);


        repeats = (EditText) findViewById(R.id.repeats);
        final TextView text1 = (TextView) findViewById(R.id.repeatText);
        final TextView text2 = (TextView) findViewById(R.id.timesText);
        toggle = (ToggleButton) findViewById(R.id.unlockLevel);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    repeats.setVisibility(View.INVISIBLE);
                    text1.setVisibility(View.INVISIBLE);
                    text2.setVisibility(View.INVISIBLE);
                    toggleFlag = true;
                } else {
                    repeats.setVisibility(View.VISIBLE);
                    text1.setVisibility(View.VISIBLE);
                    text2.setVisibility(View.VISIBLE);
                    toggleFlag = false;
                }
            }
        });

        setDueDateButton = (Button) findViewById(R.id.selectDueDate);

        setDueDateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                showTimePickerDialog(v2);
            }
        });

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
                    DatabaseReference mRef = mRootRef.child("teacher-list").child(teacher_main_menu.username_string);
                    mRef.child(key).setValue(null);

                    mobileArray.remove(position);
                    boolArray.remove(position);
                }
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(listView);

        Button setTasks = (Button) findViewById(R.id.setTaskButton);
        setTasks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                attemptTaskSet();
            }
        });
    }

    private void attemptTaskSet() {
        for (String student : studentCheckedList) {
            Log.d("Students checked", student);
        }
    }

    public void triggerAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Enter Student Username");
        // Set an EditText view to get user input
        mUsernameView = new EditText(this);
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
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            final String new_student_username = mUsernameView.getText().toString();
            DatabaseReference userTypeRef = mRootRef.child("teacher-list"); //setting up an index of usernames mapped to uid
            DatabaseReference teacherStudentRef = userTypeRef.child(teacher_main_menu.username_string); //TODO change to teacher username
            boolArray.clear();
            teacherStudentRef.child(new_student_username).setValue(false, new DatabaseReference.CompletionListener() { //TODO change to student username
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
                    if (!mobileArray.contains(childDataSnapshot.getKey()) && (Boolean) childDataSnapshot.getValue()) {
                        mobileArray.add(childDataSnapshot.getKey());
                    } else if (mobileArray.contains(childDataSnapshot.getKey()) && !(Boolean) childDataSnapshot.getValue()) {
                        mobileArray.remove(childDataSnapshot.getKey());
                    }
                    changeData(mobileArray, boolArray);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    public void changeData(ArrayList mobileArray, ArrayList boolArray) {
        mAdapter = new SetTasksAdapter(mobileArray, boolArray);
        listView = (RecyclerView) findViewById(R.id.studentCheckboxList);
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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        explaination = (TextView) findViewById(R.id.taskExplain);
        //  Log.d("Position value:", Integer.toString(pos));

        switch (pos) {

            case 0: {

                preview.setImageDrawable(null);
                explaination.setText(null);
                //preview.setVisibility(View.INVISIBLE);
                // explaination.setVisibility(View.INVISIBLE);
                toggle.setChecked(true);

            }

            case 1: {
                preview.setImageDrawable(getDrawable(R.drawable.rhythm_preview));
                explaination.setText("10 multi-choice questions where students either a Semibreve, Minim, Crotchet or Quaver will be shown. Students select the correct option out of three suggestions.");
                break;
            }
            case 2: {
                preview.setImageDrawable(getDrawable(R.drawable.rest_preview));
                explaination.setText("10 multi-choice questions of a Semibreve, Minim, Crotchet or Quaver rest. Students " +
                        "select the correct option out of three suggestions.");
                break;

            }
            case 3: {
                preview.setImageDrawable(getDrawable(R.drawable.notes_spaces_preview));
                explaination.setText("10 multi-choice questions of notes in the spaces.(Treble Clef) Students " +
                        "select the correct option out of three suggestions.");
                break;
            }
            case 4: {
                preview.setImageDrawable(getDrawable(R.drawable.notes_spaces_touch_preview));
                explaination.setText("10 touch questions of notes in the spaces (Treble Clef). Students will be prompted to find a F, A, C or E note on the stave.");
                break;
            }
            case 5: {
                preview.setImageDrawable(getDrawable(R.drawable.notes_preview));
                explaination.setText("10 multi-choice questions of notes on the lines.(Treble Clef) Students " +
                        "select the correct option out of three suggestions.");
                break;
            }
            case 6: {
                preview.setImageDrawable(getDrawable(R.drawable.touch_preview));
                explaination.setText("10 touch questions of notes on the lines (Treble Clef). Students will be prompted to find an E, G, B, D, or F note on the stave.");
                break;
            }

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setShowsDialog(true);
        newFragment.show(getFragmentManager(), "datePicker");
    }
}

