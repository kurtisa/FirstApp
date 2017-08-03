package com.example.kurtis.firstapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.TextView;

import com.example.kurtis.firstapp.R;

import java.util.concurrent.ThreadLocalRandom;

public class android extends MainActivity {

    public String noteLetter;
    public String incorrectnoteLetter1;
    public String incorrectnoteLetter2;

    Context context;

    public android(Context context) {

        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        button1 = (Button) ((MainActivity) context).findViewById(R.id.button1);
//        button2 = (Button) ((MainActivity) context).findViewById(R.id.button2);
//        button3 = (Button) ((MainActivity) context).findViewById(R.id.button3);
    }



    public String getNoteLetter(int correctNoteNumber) {


        switch (correctNoteNumber) {
            case 0:
                noteLetter = "A";
                break;
            case 1:
                noteLetter = "Bb";
                break;
            case 2:
                noteLetter = "B";
                break;
            case 3:
                noteLetter = "C";
                break;
            case 4:
                noteLetter = "C#";
                break;
            case 5:
                noteLetter = "D";
                break;
            case 6:
                noteLetter = "Eb";
                break;
            case 7:
                noteLetter = "E";
                break;
            case 8:
                noteLetter = "F";
                break;
            case 9:
                noteLetter = "F";
                break;
            case 10:
                noteLetter = "G";
                break;
            case 11:
                noteLetter = "Ab";
                break;
        }
    return noteLetter;
    }

    public String getIncorrectNoteLetter1(int incorrectNoteNumber1) {
        switch (incorrectNoteNumber1) {
            case 0:
                incorrectnoteLetter1 = "A";
                break;
            case 1:
                incorrectnoteLetter1 = "Bb";
                break;
            case 2:
                incorrectnoteLetter1 = "B";
                break;
            case 3:
                incorrectnoteLetter1 = "C";
                break;
            case 4:
                incorrectnoteLetter1 = "C#";
                break;
            case 5:
                incorrectnoteLetter1 = "D";
                break;
            case 6:
                incorrectnoteLetter1 = "Eb";
                break;
            case 7:
                incorrectnoteLetter1 = "E";
                break;
            case 8:
                incorrectnoteLetter1 = "F";
                break;
            case 9:
                incorrectnoteLetter1 = "F";
                break;
            case 10:
                incorrectnoteLetter1 = "G";
                break;
            case 11:
                incorrectnoteLetter1 = "Ab";
                break;
        }
        return incorrectnoteLetter1;
    }


    public String getIncorrectNoteLetter2(int incorrectNoteNumber2) {
        switch (incorrectNoteNumber2) {
            case 0:
                incorrectnoteLetter2 = "A";
                break;
            case 1:
                incorrectnoteLetter2 = "Bb";
                break;
            case 2:
                incorrectnoteLetter2 = "B";
                break;
            case 3:
                incorrectnoteLetter2 = "C";
                break;
            case 4:
                incorrectnoteLetter2 = "C#";
                break;
            case 5:
                incorrectnoteLetter2 = "D";
                break;
            case 6:
                incorrectnoteLetter2 = "Eb";
                break;
            case 7:
                incorrectnoteLetter2 = "E";
                break;
            case 8:
                incorrectnoteLetter2 = "F";
                break;
            case 9:
                incorrectnoteLetter2 = "F";
                break;
            case 10:
                incorrectnoteLetter2 = "G";
                break;
            case 11:
                incorrectnoteLetter2 = "Ab";
                break;
        }
        return incorrectnoteLetter2;
    }


    @JavascriptInterface
    public void updateButtonText(int correctNoteNumber) {

        int buttonOrder = ThreadLocalRandom.current().nextInt(0, 5 + 1);

        int incorrectNoteNumber1 = correctNoteNumber + 2;
        int incorrectNoteNumber2 = correctNoteNumber - 2;


        if (incorrectNoteNumber2 < 0) {
            incorrectNoteNumber2 = incorrectNoteNumber2 + 11;
        }


        switch (buttonOrder)
        {
            case 0:
                button1.setText(getNoteLetter(correctNoteNumber));
                button2.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button3.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
            case 1:
                button1.setText(getNoteLetter(correctNoteNumber));
                button3.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button2.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
            case 2:
                button2.setText(getNoteLetter(correctNoteNumber));
                button1.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button3.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
            case 3:
                button2.setText(getNoteLetter(correctNoteNumber));
                button3.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button1.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
            case 4:
                button3.setText(getNoteLetter(correctNoteNumber));
                button2.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button1.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
            case 5:
                button3.setText(getNoteLetter(correctNoteNumber));
                button1.setText(getIncorrectNoteLetter1(incorrectNoteNumber1));
                button2.setText(getIncorrectNoteLetter2(incorrectNoteNumber2));
                break;
        }
    }


    // });

        }



