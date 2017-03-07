package com.another1dd.testkingtestsudokuapp.gameEngine;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class TinyDB {

    private SharedPreferences preferences;


    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }


    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }


    public ArrayList<Integer> getListInt(String key) {
        String[] myList = TextUtils.split(preferences.getString(key, ""), "‚‗‚");
        ArrayList<String> arrayToList = new ArrayList<String>(Arrays.asList(myList));
        ArrayList<Integer> newList = new ArrayList<Integer>();

        for (String item : arrayToList)
            newList.add(Integer.parseInt(item));

        return newList;
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }



    private ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }


    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    public void putListInt(String key, ArrayList<Integer> intList) {
        checkForNullKey(key);
        Integer[] myIntList = intList.toArray(new Integer[intList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myIntList)).apply();
    }

    public void putLong(String key, long value) {
        checkForNullKey(key);
        preferences.edit().putLong(key, value).apply();
    }


    private void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }


    public void putBoolean(String key, boolean value) {
        checkForNullKey(key);
        preferences.edit().putBoolean(key, value).apply();
    }



    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    public void clear() {
        preferences.edit().clear().apply();
    }


    public Map<String, ?> getAll() {
        return preferences.getAll();
    }



    private void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    public void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}
