package com.balinasoft.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class AuthUtils {

    private static final String TOKEN = "TOKEN_CODE";
    private static final String USERNAME = "USERNAME";

    public static void saveToken(Context context, String token){
            SharedPreferences mSharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", 0);
            if(mSharedPreferences != null)
                mSharedPreferences.edit().putString(TOKEN, token).apply();
            Log.e("Token", token);
    }

    public static String getToken(Context context){
            SharedPreferences mSharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", 0);
            if(mSharedPreferences != null)
                return mSharedPreferences.getString(TOKEN, null);
        return null;
    }

    public static void clearToken(Context context){
        context.getSharedPreferences("SHARED_PREFERENCES", 0).edit().clear().apply();
    }


    public static void saveUsername(Context context, String name){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", 0);
        if(mSharedPreferences != null)
            mSharedPreferences.edit().putString(USERNAME, name).apply();
    }

    public static String getUsername(Context context){
        SharedPreferences mSharedPreferences = context.getSharedPreferences("SHARED_PREFERENCES", 0);
        if(mSharedPreferences != null)
            return mSharedPreferences.getString(USERNAME, null);
        return null;
    }

}
