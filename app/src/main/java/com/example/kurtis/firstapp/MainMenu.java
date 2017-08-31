package com.example.kurtis.firstapp;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
        private Intent intent;
        TextView userName;
        TextView task;
        MenuItem username_item;
        private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar actionBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(actionBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        userName = (TextView) findViewById(R.id.userName);

        task = (TextView)findViewById(R.id.taskToDo);


      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, actionBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
       toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // log out button code here

        final Button log_out = (Button) findViewById(R.id.log_out);

        intent = new Intent(this, LoginActivity.class);

        log_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
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
        DatabaseReference uidRef = mRootRef.child("users").child(uid).child("username");

        uidRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.w("MAIN MENU", (dataSnapshot.getValue(String.class)));

                String username_string = dataSnapshot.getValue(String.class);
                if (!username_string.isEmpty()) {
                    MenuItem hey = menu.findItem(R.id.action_username);
                    hey.setTitle(username_string);
                }
            }

            public void onCancelled(DatabaseError databaseError) {
              //  Log.w("MAIN MENU", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if( R.id.action_user_icon == id){
            return true;
        }

        if( R.id.action_username == id){
            return true;
        }

        if( R.id.action_trophy_icon == id){
            return true;
        }
        // User chose the "Favorite" action, mark the current item
        // as a favorite...

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.lvl1) {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("LEVEL", "1");
            startActivity(intent);
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
