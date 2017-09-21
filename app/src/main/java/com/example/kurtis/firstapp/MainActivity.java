package com.example.kurtis.firstapp;

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


public class MainActivity extends AppCompatActivity {
    public static boolean trebleClef;
    public static String level;
    public int levelNum;
    public int question_num = 0;
    public int question_attempts = 0;
    public int rightAnswer;
    public int QuestionNum = 1;
    public long t1;
    ProgressBar progressBar;
    Boolean logged_out;
    DatabaseReference mRootRef;
    FirebaseUser user;
    String uid;
    TextView percentageCorrect;
    int percent;
    private boolean flag = true;
    private WebView wv1;
    private Button button1;
    private Button button2;
    private Button button3;

    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

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

        if ((Objects.equals(level, "3") | Objects.equals(level, "2"))) {
            trebleClef = true;
            Log.d("IF STATEMENT", "trebleClef set to true");
        } else {
            trebleClef = false;
            Log.d("IF STATEMENT", "trebleClef set to false");
        }


        percentageCorrect = (TextView) findViewById(R.id.percentageDisplay);
        percentageCorrect.setText("100%");
        percentageCorrect.setTextColor(Color.GREEN);

        question_num = 1;
        super.onCreate(savedInstanceState);
        wv1 = (WebView) findViewById(R.id.webview_main);
        wv1.addJavascriptInterface(new android(getApplicationContext()), "android");

        final String VexQuestions = "<!DOCTYPE html>" +
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
                "<script type = text/javascript src = \"VexSource/vexQuestions.js\">" +
                "</script>" +
                "</body>" +
                "</html>";

        //enable jse

        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);

        //wv1.loadUrl("http://beta.html5test.com/");
        progressBar = (ProgressBar) findViewById(R.id.determinateBar);
        wv1.loadDataWithBaseURL("file:///android_asset/", VexQuestions, "text/html", "utf-8", null);
        createButtons();
    }

    public void createButtons() {

        button1 = (Button) findViewById(R.id.button1_id);
        button2 = (Button) findViewById(R.id.button2_id);
        button3 = (Button) findViewById(R.id.button3_id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            button1.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
            button2.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
            button3.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
        }


        delayButtons();

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                question_attempts += 1;
                if (rightAnswer == 1) {
                    question_num += 1;
                    updateProgress(button1);
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    if (question_num == 21) {
                        finish_activity();
                    }
                    delayButtons();
                } else {
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    wrongAnswer(button1);
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
                    delayButtons();

                } else {
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    wrongAnswer(button2);

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
                    delayButtons();
                } else {
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    wrongAnswer(button3);
                }
            }
        });
    }

    private void wrongAnswer(Button button) {

        YoYo.with(Techniques.Wave)
                .duration(300)
                .repeat(1)
                .playOn(button);
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


    public void finish_activity() {

        if (!logged_out) {
            DatabaseReference userNameRef = mRootRef.child("user-activity");
            DatabaseReference uidRef = userNameRef.child(uid);
            DatabaseReference instance = uidRef.push();
            instance.child("Level").setValue(level);
            instance.child("Accuracy").setValue(percent);

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
        wv1.reload();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                updateButtonText();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    button1.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
                    button2.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
                    button3.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
                }
            }
        }, 900);

    }


    public int updateButtonText() {
        //   Log.d("CurrentNote right after reload", android.noteLetter);
        //int rightAnswer = 0; // if 1, button 1 is correct, if 2 button 2, if 3 button 3
        int correctNote = android.getNoteNumber(android.noteLetter);
        int buttonOrder = ThreadLocalRandom.current().nextInt(0, 5 + 1);
        int incorrectNoteNumber1 = correctNote + 2;
        int incorrectNoteNumber2 = correctNote - 2;


        if (incorrectNoteNumber1 == correctNote) {
            incorrectNoteNumber1 = correctNote - 1;
        }
        if (incorrectNoteNumber2 == correctNote) {
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

        if (id == 16908332) {  // home button id
            //Intent intent = new Intent(this, MainMenu.class);
            //startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

