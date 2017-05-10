package com.example.kurtis.firstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

import java.util.Random;


public class MainActivity extends AppCompatActivity {



    private WebView wv1;


    protected void onCreate(Bundle savedInstanceState) {
        int noteRange=8;
        final Random randnum = new Random(noteRange);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv1 = (WebView) findViewById(R.id.webview_main);





        final String VexQuestions = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<canvas id=\"boo\" width=\"200\" height=\"100\"></canvas>" +

                "<script src=\"vexflow-master/releases/vexflow-min.js\"> </script>" +
                "<script type = text/javascript src = \"VexSource/vexQuestions.js\">" +
                "</script>" +
                "</body>" +
                "</html>";



        //enable js

        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        //wv1.loadUrl("http://beta.html5test.com/");

        wv1.loadDataWithBaseURL("file:///android_asset/", VexQuestions, "text/html", "UTF-8", null);

    }

//    public class JavaScriptInterface {
//        JavaScriptInterface(Context c) {
//            mContext = c;
//        }
//        @JavascriptInterface
//        public String getFromAndroid() {
//            return randnum;
//        }
//    }



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
