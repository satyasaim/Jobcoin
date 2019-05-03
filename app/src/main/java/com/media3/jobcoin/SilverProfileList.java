package com.media3.jobcoin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.Users;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SilverProfileList extends AppCompatActivity {
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
    ImageView tv_memebershipicon;

    String hrId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_silverprofile_list);

        hrId = new PrefManager(getApplicationContext()).getId();
       // tv_memebershipicon = findViewById(R.id.tv_memebershipicon);

        Api.getapiContext(getApplicationContext());
        (Api.getClient().silverjobseekersList(hrId)).enqueue(new Callback<JobSeekerListResponse>() {
            @Override
            public void onResponse(Call<JobSeekerListResponse> call, Response<JobSeekerListResponse> response) {

                System.out.println("result of api---->"+response.body().toString());



                ArrayList<Users> seekerlist = (ArrayList<Users>) response.body().getData();
                //Log.d("response data ",response.body().getData());
                String silverprolist = Integer.toString(seekerlist.size());
               Log.d("silver coin count",silverprolist);
               System.out.println("silvercount::"+silverprolist);
                new PrefManager(getApplicationContext()).saveTotalSilverProfiles(silverprolist);

                for(Users js :seekerlist)
                {
                    Log.d("username::",js.getUserName());
                    Log.d("membership id",js.getMembershipId());
                    select.add(R.drawable.add_profile);
                    download.add(R.drawable.download_icon);
                    jseekerid.add(js.getId());
                    Log.d("js id",js.getId());
                    hridarray.add(hrId);
                    //  if(js.getId()== new PrefManager(getApplicationContext()).getId()) {
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
                            jseekernames.add(js.getUserName());
//                            if(js.getMembershipId().equals("2"))
//                            {
//                                tv_memebershipicon.setImageResource(R.drawable.silver_coin);
//                                //tv_memebershipicon.setImageIcon(R.drawable.silver_coin);
//                            }



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
                RecyclerView recyclerView2 = findViewById(R.id.recy_silverresumes);
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView2.setLayoutManager(linearLayoutManager);
                SilverProfCustomAdapter customAdapter = new SilverProfCustomAdapter(SilverProfileList.this,jseekernames,experience,
                        qualification,phoneno,gender,skill1,skill2,age,select,jseekerid,hridarray,download);
                recyclerView2.setAdapter(customAdapter);

                new PrefManager(getApplicationContext()).saveTotalSilverProfiles(Integer.toString(customAdapter.getItemCount()));
            }




            @Override
            public void onFailure(Call<JobSeekerListResponse> call, Throwable t) {
                Validations.MyAlertBox(SilverProfileList.this,"No Silver Profiles Yet");
               // Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

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
