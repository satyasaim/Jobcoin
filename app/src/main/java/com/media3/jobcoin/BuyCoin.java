package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BuyCoin extends AppCompatActivity {
Button bt_bron_getcoin, bt_get_coin,bt_getsilvercoin,bt_plat_getcoin,bt_bron_knowmore,bt_silver_knowmore,bt_know_more,bt_plat_knowmore;
String membershipid,membershipname,membershipprice,currentbalance,totalamount,productinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_coin);

        findByIds();




        bt_bron_getcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipid = "1";
                membershipname ="Bronze";
                membershipprice = "5000";
                currentbalance = "0";
                totalamount = "5000";
                productinfo = "1";


                new PrefManager(getApplicationContext()).saveMembershipdetails(membershipid,membershipname,membershipprice,currentbalance,totalamount,productinfo);

                Intent intent = new Intent(BuyCoin.this,PaymentDetails.class);
                startActivity(intent);
            }
        });


        bt_get_coin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipid = "3";
                membershipname ="Gold";
                membershipprice = "20000";
                currentbalance = "0";
                totalamount = "20000";
                productinfo = "3";


                new PrefManager(getApplicationContext()).saveMembershipdetails(membershipid,membershipname,membershipprice,currentbalance,totalamount,productinfo);

                Intent intent = new Intent(BuyCoin.this,PaymentDetails.class);
                startActivity(intent);
            }
        });
        bt_getsilvercoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipid = "2";
                membershipname ="Silver";
                membershipprice = "10000";
                currentbalance = "0";
                totalamount = "10000";
                productinfo = "2";


                new PrefManager(getApplicationContext()).saveMembershipdetails(membershipid,membershipname,membershipprice,currentbalance,totalamount,productinfo);

                Intent intent = new Intent(BuyCoin.this,PaymentDetails.class);
                startActivity(intent);
            }
        });
        bt_plat_getcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                membershipid = "4";
                membershipname ="Platinum";
                membershipprice = "50000";
                currentbalance = "0";
                totalamount = "50000";
                productinfo = "4";


                new PrefManager(getApplicationContext()).saveMembershipdetails(membershipid,membershipname,membershipprice,currentbalance,totalamount,productinfo);

                Intent intent = new Intent(BuyCoin.this,PaymentDetails.class);
                startActivity(intent);
            }
        });
        bt_bron_knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyCoin.this,BronzeDescription.class);
                startActivity(intent);
            }
        });

        bt_silver_knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyCoin.this,SilverDescription.class);
                startActivity(intent);
            }
        });
        bt_know_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyCoin.this,GoldDescription.class);
                startActivity(intent);
            }
        });
        bt_plat_knowmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyCoin.this,PlatinumDescription.class);
                startActivity(intent);
            }
        });
    }

    public  void findByIds()
    {
        bt_get_coin = findViewById(R.id.bt_get_coin);
        bt_bron_getcoin = findViewById(R.id.bt_bron_getcoin);
        bt_getsilvercoin = findViewById(R.id.bt_getsilvercoin);
        bt_plat_getcoin = findViewById(R.id.bt_plat_getcoin);
        bt_bron_knowmore = findViewById(R.id.bt_bron_knowmore);
        bt_silver_knowmore = findViewById(R.id.bt_silver_knowmore);
        bt_know_more = findViewById(R.id.bt_know_more);
        bt_plat_knowmore = findViewById(R.id.bt_plat_knowmore);

    }
}
