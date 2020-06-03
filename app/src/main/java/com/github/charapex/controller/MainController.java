package com.github.charapex.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.github.charapex.Constants;
import com.github.charapex.data.MozambiqueHere;
import com.github.charapex.model.Legends;
import com.github.charapex.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;

    }

    public void onStart(){

        List<Legends> legendsList = getDataFromCache();
        if(legendsList != null){
            view.showList(legendsList);
        }else {
            makeApiCall();
        }

    }

    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MozambiqueHere moz = retrofit.create(MozambiqueHere.class);

        Call<List<Legends>> call = moz.getLegendsList();
        call.enqueue(new Callback<List<Legends>>() {
            @Override
            public void onResponse(Call<List<Legends>> call, Response<List<Legends>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<Legends> LegList = response.body();
                    saveList(LegList);
                    view.showList(LegList);
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<List<Legends>> call, Throwable t) {
                view.showError();

            }
        });
    }

    private void saveList(List<Legends> legList) {
        String jsonString = gson.toJson(legList);
        sharedPreferences
                .edit()
                .putString("jsonLegendList", jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List saved !", Toast.LENGTH_SHORT).show();
    }

    private List<Legends> getDataFromCache() {
        String jsonLegend = sharedPreferences.getString("jsonLegendList", null);

        if (jsonLegend == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Legends>>(){}.getType();
            return gson.fromJson(jsonLegend, listType);
        }


    }
    public void onItemClick(Legends legends){

    }

    public void onButtonAClick(){

    }

    public void onButtonBClick(){

    }
}
