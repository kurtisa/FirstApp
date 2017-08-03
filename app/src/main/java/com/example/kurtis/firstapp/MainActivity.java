package com.example.kurtis.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.Button;

import java.text.CollationElementIterator;
import java.util.concurrent.ThreadLocalRandom;


public class MainActivity extends AppCompatActivity {

    public  Button button1;
    public  Button button2;
    public  Button button3;


    private WebView wv1;
    Context context;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        setContentView(R.layout.activity_main);
        wv1 = (WebView) findViewById(R.id.webview_main);
        //wv1.addJavascriptInterface(new WebAppInterface(context),"MainActivityInterface");
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
                "<script src=\"vexflow-master/releases/vexflow-min.js\"> </script>" +
                "<script type = text/javascript src = \"VexSource/vexQuestions.js\">" +
                "</script>" +
                "</body>" +
                "</html>";


        //enable jse

        wv1.getSettings().setDomStorageEnabled(true);
        wv1.getSettings().setJavaScriptEnabled(true);


        //wv1.loadUrl("http://beta.html5test.com/");

        wv1.loadDataWithBaseURL("file:///android_asset/", VexQuestions, "text/html", "UTF-8", null);
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

        return super.onOptionsItemSelected(item);
    }





}

