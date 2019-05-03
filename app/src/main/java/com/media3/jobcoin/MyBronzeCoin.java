package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyBronzeCoin extends AppCompatActivity {

    Button bt_br_change_plan,bt_bronze_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bronze_coin);

        bt_br_change_plan = findViewById(R.id.bt_br_change_plan);
        bt_bronze_plan = findViewById(R.id.bt_bronze_plan);
        bt_bronze_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBronzeCoin.this, BronzeDescription.class);
                startActivity(intent);

            }
        });
        bt_br_change_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyBronzeCoin.this, BuyCoin.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//        Intent intent = new Intent(MyBronzeCoin.this, BuyCoin.class);
//        startActivity(intent);
//    }
}
