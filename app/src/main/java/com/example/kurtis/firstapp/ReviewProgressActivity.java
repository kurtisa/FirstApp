package com.example.kurtis.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewProgressActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_progress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.reviewProgressExList);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("student123");
        listDataHeader.add("olivia34");
        listDataHeader.add("octavo2");
        listDataHeader.add("interludo231");
        listDataHeader.add("harry345");

        // Adding child data
        List<String> student123 = new ArrayList<String>();
        student123.add("Complete Level 1: Reading Rhythm Notes (3 times)");
        student123.add("Complete Level 2: Reading Rhythm Rests (3 times)");

        List<String> olivia34 = new ArrayList<String>();
        olivia34.add("Unlock Level 6: Writing Treble Clef Notes on Lines");

        List<String> octavo2 = new ArrayList<String>();
        octavo2.add("Complete Level 5: Reading Rhythm Notes (5 times)");

        List<String> interludo231 = new ArrayList<String>();
        interludo231.add("No tasks to show");

        List<String> harry345 = new ArrayList<String>();
        harry345.add("No tasks to show");

        listDataChild.put(listDataHeader.get(0), student123); // Header, Child data
        listDataChild.put(listDataHeader.get(1), olivia34);
        listDataChild.put(listDataHeader.get(2), octavo2);
        listDataChild.put(listDataHeader.get(3), interludo231);
        listDataChild.put(listDataHeader.get(4), harry345);

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
