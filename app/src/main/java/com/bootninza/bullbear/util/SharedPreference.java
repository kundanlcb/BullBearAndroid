package com.bootninza.bullbear.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {
    SharedPreferences sharedPref;

    public SharedPreference(Application context) {
        sharedPref=  context.getApplicationContext().getSharedPreferences("bullbear", Context.MODE_PRIVATE);
    }

    public void addToken(String token) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public String getToken() {
       return sharedPref.getString("token", "empty");
    }
}
