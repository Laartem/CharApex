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
        String legendJson = intent.getStringExtra("legendsName");
        String ab1tJson = intent.getStringExtra("legendsName");
        String ab1nJson = intent.getStringExtra("legendsName");
        String ab1dJson = intent.getStringExtra("legendsName");
        String ab2tJson = intent.getStringExtra("legendsName");
        String ab2nJson = intent.getStringExtra("legendsName");
        String ab2dJson = intent.getStringExtra("legendsName");
        String ab3tJson = intent.getStringExtra("legendsName");
        String ab3nJson = intent.getStringExtra("legendsName");
        String ab3dJson = intent.getStringExtra("legendsName");
        Legends legend = Singletons.getGson().fromJson(legendJson, Legends.class);
        showDetail(legend);

    }

    private void showDetail(Legends legend) {
        txtDetail.setText("Name : " + legend.getName());
        txtDetail.setText("First Ability : ");
        txtDetail.setText("Type :" + legend.getAbility_1().getType());
        txtDetail.setText("Name : " + legend.getAbility_1().getName());
        txtDetail.setText("Description : " + legend.getAbility_1().getDescription());
        txtDetail.setText("Second Ability : ");
        txtDetail.setText("Type :" + legend.getAbility_2().getType());
        txtDetail.setText("Name : " + legend.getAbility_2().getName());
        txtDetail.setText("Description : " + legend.getAbility_2().getDescription());
        txtDetail.setText("Third Ability : ");
        txtDetail.setText("Type :" + legend.getAbility_3().getType());
        txtDetail.setText("Name : " + legend.getAbility_3().getName());
        txtDetail.setText("Description : " + legend.getAbility_3().getDescription());

    }
}
