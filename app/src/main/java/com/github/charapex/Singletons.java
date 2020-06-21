package com.github.charapex;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.charapex.data.MozambiqueHere;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static MozambiqueHere MozambiqueHereInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if (gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static MozambiqueHere getMozambiqueHereInstance(){
        if(MozambiqueHereInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            MozambiqueHereInstance = retrofit.create(MozambiqueHere.class);
        }
        return MozambiqueHereInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if (sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("db_esiea", Context.MODE_PRIVATE);

        }
        return sharedPreferencesInstance;
    }
}
