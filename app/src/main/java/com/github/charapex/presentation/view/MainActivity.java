package com.github.charapex.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.github.charapex.R;
import com.github.charapex.Singletons;
import com.github.charapex.presentation.controller.MainController;
import com.github.charapex.presentation.model.Abilities;
import com.github.charapex.presentation.model.Legends;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Singletons.getGson(),
                Singletons.getSharedPreferences(getApplicationContext())
        );
        controller.onStart();
    }



    public void showList(List<Legends> legendsList) {
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        // define an adapter
        mAdapter = new ListAdapter(legendsList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Legends item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
        public void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Legends legends) {
        Intent myIntent = new Intent(MainActivity.this, LegendsDetailActivity.class);
        myIntent.putExtra("legendsName", Singletons.getGson().toJson(legends));
        myIntent.putExtra("Ability1Type", Singletons.getGson().toJson(legends.getAbility_1().getType()));
        myIntent.putExtra("Ability1Name", Singletons.getGson().toJson(legends.getAbility_1().getName()));
        myIntent.putExtra("Ability1Desc", Singletons.getGson().toJson(legends.getAbility_1().getDescription()));
        myIntent.putExtra("Ability2Type", Singletons.getGson().toJson(legends.getAbility_2().getType()));
        myIntent.putExtra("Ability2Name", Singletons.getGson().toJson(legends.getAbility_2().getName()));
        myIntent.putExtra("Ability2Desc", Singletons.getGson().toJson(legends.getAbility_2().getDescription()));
        myIntent.putExtra("Ability3Type", Singletons.getGson().toJson(legends.getAbility_3().getType()));
        myIntent.putExtra("Ability3Name", Singletons.getGson().toJson(legends.getAbility_3().getName()));
        myIntent.putExtra("Ability3Desc", Singletons.getGson().toJson(legends.getAbility_3().getDescription()));


        MainActivity.this.startActivity(myIntent);
    }

}
