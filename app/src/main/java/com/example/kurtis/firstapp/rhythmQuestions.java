package com.example.kurtis.firstapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;


public class rhythmQuestions extends AppCompatActivity {
    public static boolean trebleClef;
    public static String level;
    public int levelNum;
    public int question_num = 0;
    public int question_attempts = 0;
    public int rightAnswer;
    public int QuestionNum = 1;
    public long t1;
    Context context;
    Boolean logged_out;
    DatabaseReference mRootRef;
    FirebaseUser user;
    String uid;
    TextView percentageCorrect;
    int percent = 100;
    private ProgressBar progressBar;
    private WebView wv1;
    private Button button1;
    private Button button2;
    private Button button3;

    protected void onCreate(Bundle savedInstanceState) {
        t1 = System.currentTimeMillis();

        mRootRef = FirebaseDatabase.getInstance().getReference();

        user = FirebaseAuth.getInstance().getCurrentUser();
        try {
            uid = user.getUid();
            logged_out = false;
        } catch (NullPointerException e) {
            logged_out = true;
            uid = "xxxxxxxxx";
        }
        level = getIntent().getStringExtra("LEVEL");
        // for some unknown reason this isn't working. Get it back working and go from there.

        if ((Objects.equals(level, "1") | Objects.equals(level, "2"))) {
            trebleClef = true;
            Log.d("IF STATEMENT", "trebleClef set to true");
        } else {
            trebleClef = false;
            Log.d("IF STATEMENT", "trebleClef set to false");
        }

        question_num = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_questions);
        wv1 = (WebView) findViewById(R.id.rhythmWebview_main);
        wv1.addJavascriptInterface(new android(context), "android");

        final String rhythmQuestions = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"VexSource/vexQuestions.css\".css\"" +
                "</head>" + "<body leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\"> " +
                "<body>" +
                "<div id=question>" +
                "</div>" +
                "<canvas id=\"container\" width=\"200\" height=\"10\"></canvas>" +
                "<script src=\"jquery-3.2.1.slim.js\"> </script>" +
                "<script src=\"raphael.min.js\"> </script>" +
                "<script src=\"vector-master/releases/vexflow-min.js\"> </script>" +
                "<script type = text/javascript src = \"VexSource/rhythmQuestions.js\">" +
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
        percentageCorrect = (TextView) findViewById(R.id.percentageDisplay);
        percentageCorrect.setText("100%");
        percentageCorrect.setTextColor(Color.GREEN);
        //wv1.loadUrl("http://beta.html5test.com/");
        wv1.setScrollContainer(false);
        wv1.setVerticalScrollBarEnabled(false);
        wv1.setHorizontalScrollBarEnabled(false);
        wv1.loadDataWithBaseURL("file:///android_asset/", rhythmQuestions, "text/html", "utf-8", null);
        progressBar = (ProgressBar) findViewById(R.id.rhythmdeterminateBar);

        wv1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
        createButtons();
    }

    public void createButtons() {


        button1 = (Button) findViewById(R.id.rhythmbutton1);
        button2 = (Button) findViewById(R.id.rhythmbutton2);
        button3 = (Button) findViewById(R.id.rhythmbutton3);
        delayButtons();


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                question_attempts += 1;
                if (rightAnswer == 1) {
                    question_num += 1;
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    updateProgress(button1);
                    if (question_num == 21) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();
                } else {
                    wrongAnswer(button1);
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                }


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                question_attempts += 1;
                if (rightAnswer == 2) {
                    question_num += 1;
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    updateProgress(button2);
                    if (question_num == 21) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();
                } else {
                    wrongAnswer(button2);
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                }


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v3) {
                question_attempts += 1;
                if (rightAnswer == 3) {
                    question_num += 1;
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    updateProgress(button3);
                    if (question_num == 21) {
                        finish_activity();
                    }
                    wv1.reload();
                    delayButtons();
                } else {
                    wrongAnswer(button3);
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                }

            }
        });
    }

    private void updatePercentage(int question_attempts, int question_num, TextView percentageCorrect) {

        Log.d("num", Integer.toString(question_num));
        Log.d("attempts", Integer.toString(question_attempts));

        float percentage = (float) (question_num - 1) / question_attempts;
        percent = (int) (percentage * 100);
        Log.d("percent", Integer.toString(percent));
        percentageCorrect.setText(Integer.toString(percent) + "%");

        if (percent >= 90) {
            percentageCorrect.setTextColor(Color.GREEN);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                percentageCorrect.setTextColor(getResources().getColor(R.color.colorOrangePrimary, getTheme()));
            }
        }
    }
    public void finish_activity() {

        if (!logged_out) {
            DatabaseReference userNameRef = mRootRef.child("user-activity");
            DatabaseReference uidRef = userNameRef.child(uid);
            DatabaseReference instance = uidRef.push();
            instance.child("Level").setValue(level);
            instance.child("Attempts").setValue(question_attempts);

            long t2 = System.currentTimeMillis();
            long time_taken = t2 - t1;
            instance.child("time-taken").setValue(time_taken);
            instance.child("timestamp").setValue(System.currentTimeMillis());
        }
        Intent intent = new Intent(this, exitScreen.class);
        intent.putExtra("Accuracy", Integer.toString(percent));
        intent.putExtra("Level", level);
        startActivity(intent);
        finish();


    }

    public void delayButtons() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                updateRhythmButtonText();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    button1.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
                    button2.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
                    button3.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);

                }
            }
        }, 900);

    }

    private void updateProgress(Button button) {
        int progress = question_num * 5;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress, true);
        }

        YoYo.with(Techniques.Pulse)
                .duration(600)
                .repeat(1)
                .playOn(button);
        button.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
    }


    public int updateRhythmButtonText() {
        //   Log.d("CurrentNote right after reload", android.noteLetter);
        final Button button1 = (Button) findViewById(R.id.rhythmbutton1);
        final Button button2 = (Button) findViewById(R.id.rhythmbutton2);
        final Button button3 = (Button) findViewById(R.id.rhythmbutton3);
        //int rightAnswer = 0; // if 1, button 1 is correct, if 2 button 2, if 3 button 3
        int buttonOrder = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        int incorrectRhythmNumber1 = 0;
        int incorrectRhythmNumber2 = 0;

        if (android.correctRhythmNumber <= 3) {
            do {
                incorrectRhythmNumber1 = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            }
            while (incorrectRhythmNumber1 == android.correctRhythmNumber);


            do {
                incorrectRhythmNumber2 = ThreadLocalRandom.current().nextInt(0, 3 + 1);
            }
            while (incorrectRhythmNumber2 == android.correctRhythmNumber ||
                    incorrectRhythmNumber2 == incorrectRhythmNumber1);
        } else if (android.correctRhythmNumber >= 4) {
            do {
                incorrectRhythmNumber1 = ThreadLocalRandom.current().nextInt(5, 7 + 1);
            }
            while (incorrectRhythmNumber1 == android.correctRhythmNumber);


            do {
                incorrectRhythmNumber2 = ThreadLocalRandom.current().nextInt(5, 7 + 1);
            }
            while (incorrectRhythmNumber2 == android.correctRhythmNumber ||
                    incorrectRhythmNumber2 == incorrectRhythmNumber1);
        }
        switch (buttonOrder) {
            case 0:
                button1.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 1;
                button2.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button3.setText(android.getRhythmLetter(incorrectRhythmNumber2));
                break;
            case 1:
                button1.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 1;
                button3.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button2.setText(android.getRhythmLetter(incorrectRhythmNumber2));
                break;
            case 2:
                button2.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 2;
                button1.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button3.setText(android.getRhythmLetter(incorrectRhythmNumber2));
                break;
            case 3:
                button2.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 2;
                button3.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button1.setText(android.getRhythmLetter(incorrectRhythmNumber2));
                break;
            case 4:
                button3.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 3;
                button2.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button1.setText(android.getRhythmLetter(incorrectRhythmNumber2));
                break;
            case 5:
                button3.setText(android.getRhythmLetter(android.correctRhythmNumber));
                rightAnswer = 3;
                button1.setText(android.getRhythmLetter(incorrectRhythmNumber1));
                button2.setText(android.getRhythmLetter(incorrectRhythmNumber2));
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
        if (id == R.id.action_settings) {
            return true;
        }


        if (id == 16908332) {  // home button id
            //Intent intent = new Intent(this, MainMenu.class);
            //startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void wrongAnswer(Button button) {

        YoYo.with(Techniques.Wave)
                .duration(300)
                .repeat(1)
                .playOn(button);
    }

}