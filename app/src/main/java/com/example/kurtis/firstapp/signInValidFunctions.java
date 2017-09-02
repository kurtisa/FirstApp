package com.example.kurtis.firstapp;

import android.util.Log;

import org.jsoup.helper.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sophr on 31/08/2017.
 */

class signInValidFunctions {

    static boolean isNicknameValid(String nickname) {
        return nickname.length() <= 24;
    }

    static boolean hasIllegalChars(String string) {

        Pattern regex = Pattern.compile("[\\p{Punct}]");

        return regex.matcher(string).find();
    }

    static boolean isUsernameValid(String username) {
        return (username.length() <= 24 && username.length() >= 6);
    }

    static boolean isPasswordsMatch(String password, String password2) {

        return password.equals(password2);
    }


    static boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return (m.matches() && email.length() < 255);
    }

    static boolean isPasswordValid(String password) {

        return (password.length() <= 32 && password.length() >= 6);
    }


    static boolean isAgeValid(String age) {
        if (!StringUtil.isNumeric(age)) {
            return false;
        }


        long age_int = Long.valueOf(age);
        Log.d("FUCK YOU", age);
        return (age_int > 0 && age_int < 101);

    }
}
