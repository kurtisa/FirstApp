package com.example.kurtis.firstapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class StudentAnalytics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_analytics);
        BarChart chart = (BarChart) findViewById(R.id.chart);
        //   String[] dataObjects = {"M","1"};
        Spinner options;

        options = (Spinner) findViewById(R.id.taskSpinneranalytics);
        // options_title = (TextView) findViewById(R.id.option_title);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.analytics_array, R.layout.simple_dropdown);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.simple_dropdown);
        // Apply the adapter to the spinner
        options.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        List<BarEntry> monday = new ArrayList<BarEntry>();
        List<BarEntry> tuesday = new ArrayList<BarEntry>();
        List<BarEntry> wed = new ArrayList<BarEntry>();
        List<BarEntry> thursday = new ArrayList<BarEntry>();
        List<BarEntry> friday = new ArrayList<BarEntry>();

        chart.getDescription().setEnabled(false);
        // turn your data into Entry objects
        monday.add(new BarEntry(0, 1));
        tuesday.add(new BarEntry(1, 2));
        wed.add(new BarEntry(2, 1));
        thursday.add(new BarEntry(3, 4));
        friday.add(new BarEntry(4, 1));

        //Legend legend = chart.getLegend();
        //legend.setEnabled(false);


        BarDataSet dataSet = new BarDataSet(monday, "9/09"); // add entries to dataset
        BarDataSet dataSet2 = new BarDataSet(tuesday, "10/09"); // add entries to dataset

        BarDataSet dataSet3 = new BarDataSet(wed, "11/09"); // add entries to dataset
        BarDataSet dataSet4 = new BarDataSet(thursday, "12/09"); // add entries to dataset
        BarDataSet dataSet5 = new BarDataSet(friday, "13/09"); // add entries to dataset

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dataSet.setColor(getResources().getColor(R.color.colorOrangePrimary, getTheme()));
            dataSet2.setColor(getResources().getColor(R.color.colorAccentLight, getTheme()));
            dataSet3.setColor(getResources().getColor(R.color.colorGreyLight, getTheme()));
            dataSet4.setColor(getResources().getColor(R.color.colorYellowLight, getTheme()));
            dataSet5.setColor(getResources().getColor(R.color.colorPurpleLight, getTheme()));
        }

        // dataSet.setColor(...);
        //  dataSet.setValueTextColor(...); // styling, ...

        BarData lineData = new BarData(dataSet, dataSet2, dataSet3, dataSet4, dataSet5);
        chart.setData(lineData);
        chart.invalidate(); // refresh

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
