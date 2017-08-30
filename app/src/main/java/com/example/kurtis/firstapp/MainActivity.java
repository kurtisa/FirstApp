package com.example.kurtis.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.CollationElementIterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {

    public int levelNum;
    public static boolean trebleClef;
    public int question_num = 0;
    public int question_attempts = 0;
    public int rightAnswer;
    public static String level;
    public int QuestionNum = 1;
    private WebView wv1;
    Context context;
    public long t1;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String uid;

    protected void onCreate(Bundle savedInstanceState) {
        t1 = System.currentTimeMillis();
        uid = user.getUid();
        level = getIntent().getStringExtra("LEVEL");
        // for some unknown reason this isn't working. Get it back working and go from there.

        if ((Objects.equals(level, "1") | Objects.equals(level, "2"))) {
            trebleClef = true;
            Log.d("IF STATEMENT", "trebleClef set to true");
        } else{
            trebleClef = false;
            Log.d("IF STATEMENT", "trebleClef set to false");
        }

        question_num = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv1 = (WebView) findViewById(R.id.webview_main);
        wv1.addJavascriptInterface(new android(context), "android");

        final String VexQuestions = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"VexSource/vexQuestions.css\".css\"" +
                "</head>" +
                "<body>" +
                "<div id=question>" +
                "</div>" +
                "<canvas id=\"container\" width=\"200\" height=\"100\"></canvas>" +
                "<script src=\"jquery-3.2.1.slim.js\"> </script>" +
                "<script src=\"raphael.min.js\"> </script>" +
                "<script src=\"vector-master/releases/vexflow-min.js\"> </script>" +
                "<script type = text/javascript src = \"VexSource/vexQuestions.js\">" +
                "</script>" +
                "</body>" +
                "</html>";

        //enable jse

        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        //webSettings.setUseWideViewPort(true);
       // webSettings.setBuiltInZoomControls(true);
        //webSettings.setDisplayZoomControls(false);
        //webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);

        //wv1.loadUrl("http://beta.html5test.com/");

        wv1.loadDataWithBaseURL("file:///android_asset/",  VexQuestions, "text/html", "utf-8", null);
        createButtons();
    }

    public void createButtons(){


        final Button button1 = (Button) findViewById(R.id.button1_id);
        final Button button2 = (Button) findViewById(R.id.button2_id);
        final Button button3 = (Button) findViewById(R.id.button3_id);

        final TextView helloTextView = (TextView) findViewById(R.id.textView2);
        helloTextView.setText("1/10");
        delayButtons();



        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                question_attempts += 1;
                if (rightAnswer == 1) {
                    question_num += 1;
                    String display = Integer.toString(question_num) + "/10";
                    helloTextView.setText(display);

                    if (question_num == 10) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();
                }


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                question_attempts += 1;
                if (rightAnswer == 2) {
                    question_num += 1;
                    String display = Integer.toString(question_num) + "/10";
                    helloTextView.setText(display);
                    if (question_num == 10) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();

                }


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v3) {
                question_attempts += 1;
                if (rightAnswer == 3) {
                    question_num += 1;
                    String display = Integer.toString(question_num) + "/10";
                    helloTextView.setText(display);
                    if (question_num == 10) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();
                }
                Log.d("hi", "button3 clicked");

            }
        });


    }


    public void finish_activity(){

        DatabaseReference userNameRef = mRootRef.child("user-activity");
        DatabaseReference uidRef = userNameRef.child(uid);
        DatabaseReference instance = uidRef.push();
        instance.child("Level").setValue(level);
        instance.child("Attempts").setValue(question_attempts);

        long t2 = System.currentTimeMillis();
        long time_taken = t2 - t1;
        instance.child("time-taken").setValue(time_taken);
        instance.child("timestamp").setValue(System.currentTimeMillis());

        finish();


    }
    public void delayButtons(){

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                updateButtonText();
            }
        }, 900);

    }


    public int updateButtonText() {
     //   Log.d("CurrentNote right after reload", android.noteLetter);

        final Button button1 = (Button) findViewById(R.id.button1_id);
        final Button button2 = (Button) findViewById(R.id.button2_id);
        final Button button3 = (Button) findViewById(R.id.button3_id);
        //int rightAnswer = 0; // if 1, button 1 is correct, if 2 button 2, if 3 button 3
        int correctNote = android.getNoteNumber(android.noteLetter);
        int buttonOrder = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        int incorrectNoteNumber1 = correctNote + 2;
        int incorrectNoteNumber2 = correctNote - 2;


        if (incorrectNoteNumber1 == correctNote){
            incorrectNoteNumber1 = correctNote - 1;
        }
        if (incorrectNoteNumber2 == correctNote){
            incorrectNoteNumber2 = correctNote - 1;
        }

        if (incorrectNoteNumber2 < 1) {
            incorrectNoteNumber2 = 5;
        }
        if (incorrectNoteNumber1 > 7) {
            incorrectNoteNumber1 = 2;
        }


        switch (buttonOrder) {
            case 0:
                button1.setText(android.noteLetter);
                rightAnswer = 1;
                button2.setText(android.getNoteLetter(incorrectNoteNumber1));
                button3.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
            case 1:
                button1.setText(android.noteLetter);
                rightAnswer = 1;
                button3.setText(android.getNoteLetter(incorrectNoteNumber1));
                button2.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
            case 2:
                button2.setText(android.noteLetter);
                rightAnswer = 2;
                button1.setText(android.getNoteLetter(incorrectNoteNumber1));
                button3.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
            case 3:
                button2.setText(android.noteLetter);
                rightAnswer = 2;
                button3.setText(android.getNoteLetter(incorrectNoteNumber1));
                button1.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
            case 4:
                button3.setText(android.noteLetter);
                rightAnswer = 3;
                button2.setText(android.getNoteLetter(incorrectNoteNumber1));
                button1.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
            case 5:
                button3.setText(android.noteLetter);
                rightAnswer = 3;
                button1.setText(android.getNoteLetter(incorrectNoteNumber1));
                button2.setText(android.getNoteLetter(incorrectNoteNumber2));
                break;
        }
        Log.d("rightAnswer at update", Integer.toString(rightAnswer));
        return rightAnswer;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {;
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

}

