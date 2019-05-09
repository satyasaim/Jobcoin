package com.media3.jobcoin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);new  Thread(){
            public void run(){


                try {
                    sleep(3000);

                    Intent splashIntent = new Intent(Splash.this, Login.class);
                    startActivity(splashIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }


}
