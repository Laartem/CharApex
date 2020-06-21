package com.github.charapex.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.charapex.R;
import com.github.charapex.Singletons;
import com.github.charapex.presentation.model.Legends;

import org.w3c.dom.Text;

public class LegendsDetailActivity extends AppCompatActivity {


    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legends_detail);

        txtDetail = findViewById(R.id.detail_txt);
        Intent intent = getIntent();
        String legendsJson = intent.getStringExtra("legendsKeyName");
        Legends legend = Singletons.getGson().fromJson(legendsJson, Legends.class);
        showDetail(legend);

    }

    private void showDetail(Legends legend) {
        txtDetail.setText("Name : " + legend.getName());


    }
}
