package com.example.kurtis.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class android extends MainActivity {

    public static String noteLetter = "X";
    Context context;

    public android(Context context) {

        this.context = context;
    }

    public static int getNoteNumber(String noteLetter) {

        int noteNumber = 1;
        switch (noteLetter) {
            case "A":
                noteNumber = 1;
                break;
            case "B":
                noteNumber = 2;
                break;
            case "C":
                noteNumber = 3;
                break;
            case "D":
                noteNumber = 4;
                break;
            case "E":
                noteNumber = 5;
                break;
            case "F":
                noteNumber = 6;
                break;
            case "G":
                noteNumber = 7;
                break;
        }
        return noteNumber;
    }

    public static String getNoteLetter(int noteNumber) {

        String noteString = "X";
        switch (noteNumber) {
            case 1:
                noteString = "A";
                break;
            case 2:
                noteString = "B";
                break;
            case 3:
                noteString = "C";
                break;
            case 4:
                noteString = "D";
                break;
            case 5:
                noteString = "E";
                break;
            case 6:
                noteString = "F";
                break;
            case 7:
                noteString = "G";
                break;
        }
        return noteString;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // });
    @JavascriptInterface
    public void updateJavascriptNote(String correctNote) {
        noteLetter = correctNote;
    }


    @JavascriptInterface
    public boolean javaScriptGetClef() {
        if (MainActivity.trebleClef) {
            Log.d("android.java", "treble clef enabled");
        } else {
            Log.d("android.java", "bass clef enabled");
        }
        return MainActivity.trebleClef;
    }

    @JavascriptInterface
    public String javaScriptGetLevel() {
        Log.d("android.java", MainActivity.level);
        return MainActivity.level;
        //updateButtonText();
    }

}



