package com.example.kurtis.firstapp;

import android.util.Log;

import org.jsoup.helper.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sophr on 31/08/2017.
 */

public class signInValidFunctions {

    public static boolean isNicknameValid(String nickname){
        return nickname.length() <= 24;
    }
    public static boolean hasIllegalChars(String string) {

        Pattern regex = Pattern.compile("[\\p{Punct}]");

        if (regex.matcher(string).find()) {
            return true;
        }else{
            return  false;
        }
    }

    public static boolean isUsernameValid(String username) {
        return (username.length() <= 24 && username.length() >= 6);
    }

    public static boolean isPasswordsMatch(String password, String password2) {

        return password.equals(password2);
    }


    public static boolean isEmailValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return (m.matches() && email.length() < 255);
    }

    static public boolean isPasswordValid(String password) {

        return (password.length() <= 32 && password.length() >= 6);
    }


    static public boolean isAgeValid(String age) {
        if (!StringUtil.isNumeric(age)){
            return false;
        }
        int age_int = Integer.valueOf(age);
        if (age_int > 0 & age_int <101){
            return true;
        } else {
            return false;
        }

    }
}
