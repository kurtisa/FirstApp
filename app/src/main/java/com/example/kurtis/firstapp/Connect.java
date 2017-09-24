package com.example.kurtis.firstapp;

import java.util.ArrayList;
import java.util.List;

abstract class variableListener {
    public abstract void OnMyBooleanChanged();

    public static class Connect {
        public static boolean myBoolean = true;
        public static List<variableListener> listeners = new ArrayList<variableListener>();

        public static boolean getMyBoolean() {
            return myBoolean;
        }

        public static void setMyBoolean(boolean value) {
            myBoolean = value;
            for (variableListener l : listeners) {
                l.OnMyBooleanChanged();
            }
        }
        public static void addMyBooleanListener(variableListener l) {
            listeners.add(l);
        }
    }
}