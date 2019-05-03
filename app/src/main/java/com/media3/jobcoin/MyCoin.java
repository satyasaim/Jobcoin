package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyCoin extends AppCompatActivity {
    Button bt_change_plan,bt_view_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coin);
        bt_change_plan = findViewById(R.id.bt_change_plan);
        bt_view_plan = findViewById(R.id.bt_view_plan);
        bt_change_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCoin.this,BuyCoin.class);
                startActivity(intent);
            }
        });

        bt_view_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCoin.this,GoldDescription.class);
                startActivity(intent);
            }
        });
    }
}
