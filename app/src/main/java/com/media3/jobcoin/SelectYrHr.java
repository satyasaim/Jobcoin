package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.Datum;
import com.media3.jobcoin.Pojo.HrListData;
import com.media3.jobcoin.Pojo.HrListResponse;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectYrHr extends AppCompatActivity {

    String userId;
    ArrayList hrnames = new ArrayList();
    ArrayList phoneno = new ArrayList();
    ArrayList emails = new ArrayList();
    ArrayList experience = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_yr_hr);

        userId = new PrefManager(getApplicationContext()).getId();
        Log.d(userId,"jseekerid");
        Api.getapiContext(getApplicationContext());
        (Api.getClient().hrListForJobSeeker(userId)).enqueue(new Callback<HrListResponse>() {

            @Override
            public void onResponse(Call<HrListResponse> call, Response<HrListResponse> response) {
               // Validations.MyAlertBox(SelectYrHr.this,"success");

               System.out.println("result of api---->"+response.body().toString());

               ArrayList<Datum> hrlist = (ArrayList<Datum>)response.body().getData();
               if(hrlist!=null) {
                   for (Datum hr : hrlist) {
                       hrnames.add(hr.getUserName());
                       phoneno.add(hr.getPhone());
                       emails.add(hr.getEmail());
                       experience.add(hr.getTotalexperience());
                   }
               }else {
                   Validations.MyAlertBox(SelectYrHr.this,"YOUR PROFILE IS YET TO BE SEEN");

//                   Intent intent = new Intent(SelectYrHr.this,JobSeekerMainScreen.class);
//                   startActivity(intent);
               }

                RecyclerView recyclerView2 = findViewById(R.id.recy_hrslist);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView2.setLayoutManager(linearLayoutManager);
                HrListAdapter hrcustomAdapter = new HrListAdapter(SelectYrHr.this,hrnames,phoneno,emails,experience);
                recyclerView2.setAdapter(hrcustomAdapter);

            }

            @Override
            public void onFailure(Call<HrListResponse> call, Throwable t) {
                Log.d("","hr list api is onfailure");
           Validations.MyAlertBox(SelectYrHr.this,"fail");
            }
        });



}
}