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

public class PlatinumProfileList extends AppCompatActivity {
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
        setContentView(R.layout.activity_platinum_profile_list);
        hrId = new PrefManager(getApplicationContext()).getId();

        Api.getapiContext(getApplicationContext());
        (Api.getClient().platinumjobseekersList(hrId)).enqueue(new Callback<JobSeekerListResponse>() {
            @Override
            public void onResponse(Call<JobSeekerListResponse> call, Response<JobSeekerListResponse> response) {

                System.out.println("result of api---->"+response.body().toString());



                ArrayList<Users> seekerlist = (ArrayList<Users>) response.body().getData();
                //Log.d("response data ",response.body().getData());
                String platinumesize = Integer.toString(seekerlist.size());
                Log.d("plat coin count",platinumesize);
                new PrefManager(getApplicationContext()).saveTotalPlatinumProfiles(platinumesize);

                for(Users js :seekerlist)
                {
                    Log.d("username::",js.getUserName());
                    Log.d("username::",js.getDateOfBirth());
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
                    select.add(R.drawable.add_profile);
                    download.add(R.drawable.download_icon);
                    jseekerid.add(js.getId());
                    hridarray.add(hrId);
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
                RecyclerView recyclerView4 = findViewById(R.id.recy_platinumresumes);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView4.setLayoutManager(linearLayoutManager);
                PlatinumProfCustomAdapter customAdapter = new PlatinumProfCustomAdapter(PlatinumProfileList.this,jseekernames,experience,
                        qualification,phoneno,gender,skill1,skill2,age,select,jseekerid,hridarray,download);
                recyclerView4.setAdapter(customAdapter);

                new PrefManager(getApplicationContext()).saveTotalPlatinumProfiles(Integer.toString(customAdapter.getItemCount()));
            }




            @Override
            public void onFailure(Call<JobSeekerListResponse> call, Throwable t) {
                Validations.MyAlertBox(PlatinumProfileList.this,"No Platinum Profiles Yet");
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
