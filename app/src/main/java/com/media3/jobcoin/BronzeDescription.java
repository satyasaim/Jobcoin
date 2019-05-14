package com.media3.jobcoin;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class BronzeDescription extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bronze_description);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setLogo(R.drawable.inner_logo);
    }
}
