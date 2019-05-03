package com.media3.jobcoin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.Users;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoldProfileList extends AppCompatActivity {
    ArrayList jseekernames = new ArrayList();
    ArrayList experience = new ArrayList();
    ArrayList qualification = new ArrayList();
    ArrayList phoneno = new ArrayList();
    ArrayList gender = new ArrayList();
    ArrayList skill1 = new ArrayList();
    ArrayList skill2 = new ArrayList();
    ArrayList age = new ArrayList();
    ArrayList select = new ArrayList();
    ArrayList jseekerid = new ArrayList();
    ArrayList hridarray = new ArrayList();
    ArrayList download = new ArrayList();


    String hrId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_profile_list);
        hrId = new PrefManager(getApplicationContext()).getId();

        Api.getapiContext(getApplicationContext());
        (Api.getClient().goldjobseekersList(hrId)).enqueue(new Callback<JobSeekerListResponse>() {
            @Override
            public void onResponse(Call<JobSeekerListResponse> call, Response<JobSeekerListResponse> response) {

                System.out.println("result of api---->"+response.body().toString());



                ArrayList<Users> seekerlist = (ArrayList<Users>) response.body().getData();
                //Log.d("response data ",response.body().getData());
                String goldesize = Integer.toString(seekerlist.size());
                Log.d("bronze coin count",goldesize);
                new PrefManager(getApplicationContext()).saveTotalGoldProfiles(goldesize);

                for(Users js :seekerlist)
                {
                    Log.d("username::",js.getUserName());
                    select.add(R.drawable.add_profile);
                    jseekerid.add(js.getId());
                    download.add(R.drawable.download_icon);
                    Log.d("js id",js.getId());
                    hridarray.add(hrId);
                    String dob = js.getDateOfBirth();
                    Log.d("username::",js.getDateOfBirth());
                    String[] seperate= dob.split("-");
                    int year = Integer.parseInt(seperate[0]);
                    int mon = Integer.parseInt(seperate[1]);
                    int day = Integer.parseInt(seperate[2]);
                    Log.d("year",String.valueOf(year));
                    Log.d("month",String.valueOf(mon));
                    Log.d("day",String.valueOf(day));
                    String age1 = getAge(year,mon,day);
                    Log.d(age1,"static silver age testing");
                    //  if(js.getId()== new PrefManager(getApplicationContext()).getId()) {
                    jseekernames.add(js.getUserName());


                    // Log.d("jseekernames::",jseekernames.get(0));
                    experience.add(js.getTotalexperience());
                    qualification.add(js.getQualification());
                    phoneno.add(js.getPhone());
                    gender.add(js.getGender());
                    skill1.add(js.getSubject1());
                    skill2.add(js.getSubject2());
                    age.add(age1);
                    // age.add(new PrefManager(getApplicationContext()).)

                    //  }
                }
                RecyclerView recyclerView3 = findViewById(R.id.recy_goldresumes);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView3.setLayoutManager(linearLayoutManager);
                GoldProfCustomAdapter customAdapter = new GoldProfCustomAdapter(GoldProfileList.this,jseekernames,experience,
                        qualification,phoneno,gender,skill1,skill2,age,select,jseekerid,hridarray,download);
                recyclerView3.setAdapter(customAdapter);

                new PrefManager(getApplicationContext()).saveTotalGoldProfiles(Integer.toString(customAdapter.getItemCount()));


            }




            @Override
            public void onFailure(Call<JobSeekerListResponse> call, Throwable t) {
                Validations.MyAlertBox(GoldProfileList.this,"No Gold Profiles Yet");
                //Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

            }
        });



    }
    public String getAge(int year, int month, int day){
        Calendar dob1 = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob1.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob1.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob1.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();
        Log.d("age in method",ageS);
        return ageS;
    }

}
