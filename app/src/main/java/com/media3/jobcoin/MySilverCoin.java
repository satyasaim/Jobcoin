package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MySilverCoin extends AppCompatActivity {

    Button bt_silver_plan,bt_sl_change_plan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_silver_coin);
        bt_silver_plan = findViewById(R.id.bt_silver_plan);
        bt_sl_change_plan = findViewById(R.id.bt_sl_change_plan);

        bt_silver_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySilverCoin.this,SilverDescription.class);
                startActivity(intent);
            }
        });
        bt_sl_change_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySilverCoin.this,BuyCoin.class);
                startActivity(intent);
            }
        });
    }
}
