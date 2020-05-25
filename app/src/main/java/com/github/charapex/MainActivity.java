package com.github.charapex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.jsonbin.io/";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("db_esiea", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();
        List<Legends> legendsList = getDataFromCache();
        if(legendsList != null){
            showList(legendsList);
        }else {
            makeApiCall();
        }
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

    private void showList(List<Legends> legendsList) {
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(legendsList);
        recyclerView.setAdapter(mAdapter);
    }



    private void makeApiCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    showList(LegList);
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<List<Legends>> call, Throwable t) {
                showError();

            }
        });
    }

    private void saveList(List<Legends> legList) {
        String jsonString = gson.toJson(legList);
        sharedPreferences
                .edit()
                .putString("jsonLegendList", jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved !", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}
