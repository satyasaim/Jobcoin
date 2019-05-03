package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPlatinumCoin extends AppCompatActivity {

    Button bt_platinum_plan,bt_pla_change_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_platinum_coin);

        bt_pla_change_plan = findViewById(R.id.bt_pla_change_plan);
        bt_platinum_plan = findViewById(R.id.bt_platinum_plan);
        bt_platinum_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPlatinumCoin.this,PlatinumDescription.class);
                startActivity(intent);
            }
        });
        bt_pla_change_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPlatinumCoin.this,BuyCoin.class);
                startActivity(intent);
            }
        });

    }
}
