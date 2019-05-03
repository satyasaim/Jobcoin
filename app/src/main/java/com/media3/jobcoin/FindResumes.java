package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FindResumes extends AppCompatActivity {
    Button bt_bronze_all,bt_silver_all,bt_gold_all,bt_pla_view_all,bt_view_all_profiles;
    TextView tv_bcoin_count,tv_silver_count,tv_gold_count,tv_count;

    //ArrayList coin_count = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_resumes);
        findViewByIds();
        //Total bronze coins count
        String bronzecount = new PrefManager(getApplicationContext()).getBronzeProfilecount();
        Log.d(bronzecount,"bronzecount from sharedpref");
        tv_bcoin_count.setText(bronzecount);

        //Total silver coins count
        String silvercount = new PrefManager(getApplicationContext()).getSilverProfilecount();
        tv_silver_count.setText(silvercount);
        //Total gold coins count
        String goldcount = new PrefManager(getApplicationContext()).getGoldProfilecount();
        tv_gold_count.setText(goldcount);
        //Total platinum coins count
        String platcount = new PrefManager(getApplicationContext()).getPlatinumProfilecount();
        tv_count.setText(platcount);


        bt_bronze_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FindResumes.this, ProfileList.class);
                  startActivity(intent);
                            }
        });

        bt_silver_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindResumes.this, SilverProfileList.class);
                startActivity(intent);
            }
        });

        bt_gold_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindResumes.this, GoldProfileList.class);
                startActivity(intent);
            }
        });

        bt_pla_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindResumes.this, PlatinumProfileList.class);
                startActivity(intent);
            }
        });

//        bt_view_all_profiles.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(FindResumes.this, ProfileList.class);
//                startActivity(intent);
//            }
//        });

    }

    public void findViewByIds()
    {
        bt_bronze_all = findViewById(R.id.bt_bronze_all);
        bt_silver_all = findViewById(R.id.bt_silver_all);
        bt_gold_all = findViewById(R.id.bt_gold_all);
        bt_pla_view_all = findViewById(R.id.bt_pla_view_all);
       // bt_view_all_profiles = findViewById(R.id.bt_view_all_profiles);
        tv_bcoin_count = findViewById(R.id.tv_bcoin_count);
        tv_silver_count = findViewById(R.id.tv_silver_count);
        tv_gold_count = findViewById(R.id.tv_gold_count);
        tv_count = findViewById(R.id.tv_count);

    }

}
