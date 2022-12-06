package com.if5b.myapplication.Services;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utilities {
    public static final String TULIS_AJA_APIKEY = "";
    private static final String PREFERENCES_FILE_KEY = "";
    private static final String BASE_URL = "https://restapi.mdp.ac.id/tulisaja/";
    public static final String API_KEY = "dirumahja";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static void setValue(Context context, String xPref, String xValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xPref, xValue);
        editor.commit();
    }
    public static String getValue(Context context, String xPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue != null;
    }
}
