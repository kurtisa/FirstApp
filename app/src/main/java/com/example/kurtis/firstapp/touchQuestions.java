package com.example.kurtis.firstapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class touchQuestions extends AppCompatActivity {
    public static boolean trebleClef;
    public static int question_num = 0;
    public static String level;

    final String touchquestions = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<meta charset=\"UTF-8\">" +
            "<link rel=\"stylesheet\" type=\"text/css\" href=\"VexSource/vexQuestionsTouch.css\".css\"" +
            "</head>" +
            "<body>" + "<body leftmargin=\"0\" topmargin=\"0\" rightmargin=\"0\" bottommargin=\"0\"> " +
            "<div id=container>" +
            "<div id=question>" +
            "</div>" +
            "</div>" +
            "<script src=\"jquery-3.2.1.slim.js\"> </script>" +
            "<script src=\"raphael.min.js\"> </script>" +
            "<script src=\"vector-master/releases/vexflow-min.js\"> </script>" +
            "<script type = text/javascript src = \"VexSource/vexQuestionsTouch.js\">" +
            "</script>" +
            "</body>" +
            "</html>";
    public int levelNum;
    public int question_attempts = 0;
    public int rightAnswer;
    public int QuestionNum = 1;
    public long t1;
    int percent;
    TextView percentageCorrect;
    ProgressBar progressBar;
    Button button1;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    boolean hi = true;
    String uid;
    private WebView webview_touch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        t1 = System.currentTimeMillis();
        uid = user.getUid();
        level = getIntent().getStringExtra("LEVEL");

        if ((Objects.equals(level, "4") || Objects.equals(level, "6"))) {
            trebleClef = true;
            Log.d("IF STATEMENT", "trebleClef set to true");
        } else {
            trebleClef = false;
            Log.d("IF STATEMENT", "trebleClef set to false");
        }

        question_num = 1;
        setContentView(R.layout.activity_touch_questions);
        webview_touch = (WebView) findViewById(R.id.webview_touch);
        webview_touch.addJavascriptInterface(new android(getApplicationContext()), "android");

        WebSettings webSettings = webview_touch.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        percentageCorrect = (TextView) findViewById(R.id.percentageDisplay);
        percentageCorrect.setText("100%");
        percentageCorrect.setTextColor(Color.GREEN);
        webview_touch.loadDataWithBaseURL("file:///android_asset/", touchquestions, "text/html", "utf-8", null);
        updateQuestionText();
        progressBar = (ProgressBar) findViewById(R.id.touchDeterminateBar);
        button1 = (Button) findViewById(R.id.touchQuestions);
        final boolean[] fromHere = {false};
        variableListener.Connect.addMyBooleanListener(new variableListener() {
            @Override
            public void OnMyBooleanChanged() {
                if (Connect.getMyBoolean()) {

                    question_num++;
                    question_attempts++;
                    int progress = question_num * 10;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        progressBar.setProgress(progress, true);
                    }
                    Log.d("IF STATEMENT", Integer.toString(question_num));

                    YoYo.with(Techniques.Pulse)
                            .duration(600)
                            .repeat(1)
                            .playOn(button1);

                    button1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
                    button1.setText("Press here to continue!");
                    updatePercentage(question_attempts, question_num, percentageCorrect);
                    fromHere[0] = true;
                    Connect.setMyBoolean(false);

                    //finish();
                } else {
                    if (!fromHere[0]) {
                        question_attempts = question_attempts + 1;
                        updatePercentage(question_attempts, question_num, percentageCorrect);
                    } else {
                        // do nothing
                        fromHere[0] = false;
                    }
                }

            }


        });


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                if (button1.getText() == "Press here to continue!") {
                    if (question_num == 11) {
                        finish_activity();
                    }
                    webview_touch.reload();
                    YoYo.with(Techniques.Tada)
                            .duration(1300)
                            .playOn(button1);
                    updateQuestionText();
                }
            }
        });

    }

    private void finish_activity() {

        DatabaseReference userNameRef = mRootRef.child("user-activity");
        DatabaseReference uidRef = userNameRef.child(uid);
        DatabaseReference instance = uidRef.push();
        instance.child("Level").setValue(level);
        instance.child("Accuracy").setValue(percent);

        long t2 = System.currentTimeMillis();
        long time_taken = t2 - t1;
        instance.child("time-taken").setValue(time_taken);
        instance.child("timestamp").setValue(System.currentTimeMillis());

        Intent intent = new Intent(this, exitScreen.class);
        intent.putExtra("Accuracy", Integer.toString(percent));
        intent.putExtra("Level", level);
        startActivity(intent);
        finish();
    }


    public void updateQuestionText() {
        final TextView button1 = (TextView) findViewById(R.id.touchQuestions);
        // button1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            button1.getBackground().setColorFilter(getResources().getColor(R.color.colorBlueLight, getTheme()), PorterDuff.Mode.MULTIPLY);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                button1.setText("Find the note: " + android.noteLetter);
            }
        }, 1200);
    }

    public void checkCorrect() {

        webview_touch.reload();
        Log.d("IF STATEMENT", "trebleClef set to false");

        question_num++;
        if (question_num == 10) {
        }
        webview_touch.reload();
        int progress = question_num * 10;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(progress, true);
        }
        YoYo.with(Techniques.Pulse)
                .duration(600)
                .repeat(1)
                .playOn(button1);
        button1.getBackground().setColorFilter(0xFF00FF00, PorterDuff.Mode.MULTIPLY);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("HOME BUTTION PRESSED", Integer.toString(id));
        //noinspection SimplifiableIfStatement

        if (id == 16908332) {  // home button id
            //Intent intent = new Intent(this, MainMenu.class);
            //startActivity(intent);
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