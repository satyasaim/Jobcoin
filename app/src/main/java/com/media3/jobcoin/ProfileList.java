package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.Data;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.Users;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileList extends AppCompatActivity {
    ArrayList jseekernames = new ArrayList();
    ArrayList experience = new ArrayList();
    ArrayList qualification = new ArrayList();
    ArrayList phoneno = new ArrayList();
    ArrayList gender = new ArrayList();
    ArrayList skill1 = new ArrayList();
    ArrayList skill2 = new ArrayList();
    ArrayList age = new ArrayList();
    ArrayList select = new ArrayList();
    ArrayList download = new ArrayList();
    ArrayList jseekerid = new ArrayList();
    ArrayList hridarray = new ArrayList();

    String hrId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_list);

        hrId = new PrefManager(getApplicationContext()).getId();
            Log.d(hrId,"hrid");
        Api.getapiContext(getApplicationContext());
        (Api.getClient().bronzejobseekersList(hrId)).enqueue(new Callback<JobSeekerListResponse>() {
            @Override
            public void onResponse(Call<JobSeekerListResponse> call, Response<JobSeekerListResponse> response) {

                System.out.println("result of api---->"+response.body().toString());


               // getAge(1981,05,21);
                ArrayList<Users> seekerlist = (ArrayList<Users>) response.body().getData();
                //Log.d("response data ",response.body().getData());
                String bronzesize = Integer.toString(seekerlist.size());
               Log.d("bronze coin count",bronzesize);
                new PrefManager(getApplicationContext()).saveTotalBronzeProfiles(bronzesize);

                for(Users js :seekerlist)
                {
                    Log.d("username::",js.getUserName());
                    Log.d("js dob::",js.getDateOfBirth());
                    String dob = js.getDateOfBirth();
                    select.add(R.drawable.add_profile);
                    download.add(R.drawable.download_icon);
                    jseekerid.add(js.getId());
                    Log.d("js id",js.getId());
                    hridarray.add(hrId);

//                    String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//                   int result = Integer.parseInt(date) - Integer.parseInt(dob);
//                    String.valueOf(result);
//                    Log.d("current date::",date);
//                    Log.d("age::",String.valueOf(result));
                    //  if(js.getId()== new PrefManager(getApplicationContext()).getId()) {
                    //String dob = "1986-02-13";
                    String[] seperate= dob.split("-");
                   // int len = seperate.length;
                    int year = Integer.parseInt(seperate[0]);
                    int mon = Integer.parseInt(seperate[1]);
                    int day = Integer.parseInt(seperate[2]);
                    String age1 = getAge(year,mon,day);
                    Log.d(age1,"static age testing");
                  // Toast.makeText(getApplicationContext(),age1,Toast.LENGTH_SHORT).show();

                    jseekernames.add(js.getUserName());


                    // Log.d("jseekernames::",jseekernames.get(0));
                            experience.add(js.getTotalexperience());
                            qualification.add(js.getQualification());
                            phoneno.add(js.getPhone());
                            gender.add(js.getGender());
                            skill1.add(js.getSubject1());
                            skill2.add(js.getSubject2());
                            age.add(age1);
                            //age.add(js.getAge());
                    // age.add(new PrefManager(getApplicationContext()).)

                    //  }
                }
                RecyclerView recyclerView = findViewById(R.id.recy_bronzeresumes);
               final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                BronzeProfCustomAdapter customAdapter = new BronzeProfCustomAdapter(ProfileList.this,jseekernames,experience,
                        qualification,phoneno,gender,skill1,skill2,age,select,jseekerid,hridarray,download);
                recyclerView.setAdapter(customAdapter);
                new PrefManager(getApplicationContext()).saveTotalBronzeProfiles(Integer.toString(customAdapter.getItemCount()));
               //customAdapter.notifyDataSetChanged();


            }




            @Override
            public void onFailure(Call<JobSeekerListResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

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

        return ageS;
    }

}
