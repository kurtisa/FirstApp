package com.example.kurtis.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class android extends touchQuestions {

    public static String rhythmString = "X";
    public static int lastNumber = 0;
    public static String noteLetter = "X";
    public static int correctRhythmNumber;

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

    public static String getRhythmLetter(int correctRhythmNumber) {


        switch (correctRhythmNumber) {
            case 0:
                rhythmString = ("Semibreve");
                break;
            case 1:
                rhythmString = ("Minim");
                break;
            case 2:
                rhythmString = ("Crotchet");
                break;
            case 3:
                rhythmString = ("Quaver");
                break;
            case 4:
                rhythmString = ("Semibreve Rest");
                break;
            case 5:
                rhythmString = ("Minim Rest");
                break;
            case 6:
                rhythmString = ("Crotchet Rest");
                break;
            case 7:
                rhythmString = ("Quaver Rest");
                break;
        }
        return rhythmString;
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

    @JavascriptInterface
    public void updateJavascriptRhythm(int jsRhythmNumber) {
        correctRhythmNumber = jsRhythmNumber;
    }
//    public static int getRhythmNumber(String rhythmLetter) {
//
//        int rhythmNumber = 1;
//        switch (noteLetter) {
//            case "w":
//                rhythmNumber = 1;
//                break;
//            case "h":
//                rhythmNumber = 2;
//                break;
//            case "q":
//                rhythmNumber = 3;
//                break;
//            case "s":
//                rhythmNumber = 4;
//                break;
//            case "wr":
//                rhythmNumber = 5;
//                break;
//            case "hr":
//                rhythmNumber = 6;
//                break;
//            case "qr":
//                rhythmNumber = 7;
//                break;
//            case "sr":
//                rhythmNumber = 8;
//                break;
//        }
//        return rhythmNumber;
//    }

    @JavascriptInterface
    public int rememberRandom(int currentNumber) {
        if (currentNumber == lastNumber) {
            lastNumber = currentNumber;
            return 1;
        } else {
            lastNumber = currentNumber;
            return 0;
        }
        //updateButtonText();
    }

    @JavascriptInterface
    public String javaScriptGetRhythmLevel() {
        Log.d("android.java", rhythmQuestions.level);
        return rhythmQuestions.level;
        //updateButtonText();
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
    public boolean javaScriptGetTouchClef() {
        if (touchQuestions.trebleClef) {
            Log.d("android.java", "treble clef enabled");
        } else {
            Log.d("android.java", "bass clef enabled");
        }
        return touchQuestions.trebleClef;
    }

    @JavascriptInterface
    public String javaScriptGetLevel() {
        Log.d("android.java", MainActivity.level);
        return MainActivity.level;
    }

    @JavascriptInterface
    public String javaScriptGetTouchLevel() {
        Log.d("android.java", "touchLevel " + touchQuestions.level);
        return touchQuestions.level;
    }

    @JavascriptInterface
    public void javaScriptGetTouch(String touchLocationX, String touchLocationY, String notesY, String notesX) {
        Log.d("android.java", "touches at " + touchLocationX + " " + touchLocationY + " " + notesY + " " + notesX);
    }

    @JavascriptInterface
    public void javaScriptCorrectAnswer(String correct) {
        if (correct.equals("true")) {
            variableListener.Connect.setMyBoolean(true);
        } else {
            Log.d("HEY", "MY BOOLEAN CHANGED YO");
            variableListener.Connect.setMyBoolean(false);
        }
    }
}



