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
import java.util.Arrays;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedResumes extends AppCompatActivity {
    //ArrayList<String> jseekernames = new ArrayList<String>(Arrays.asList("ravi","dany","bhumi","revathi","madhukar","chaitanya","ashok"));
     ArrayList  jseekernames = new ArrayList();

//    ArrayList<String> coinid = new ArrayList<String>();
//    ArrayList<String> hrid = new ArrayList<String>();
    ArrayList<String> experience = new ArrayList<String>();
    ArrayList<String> qualification = new ArrayList<String>();
    ArrayList<String> phoneno = new ArrayList<String>();
    ArrayList<String> gender = new ArrayList<String>();
    ArrayList<String> age = new ArrayList<String>();
    ArrayList<String> skill1 = new ArrayList<String>();
    ArrayList<String> skill2 = new ArrayList<String>();

    String hrId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_resumes);

        hrId = new PrefManager(getApplicationContext()).getId();
        Log.d("insidejobseekersList::","testing"+hrId);


        Api.getapiContext(getApplicationContext());
        (Api.getClient().jobseekersList(hrId)).enqueue(new Callback<JobSeekerListResponse>() {
            @Override
            public void onResponse(Call<JobSeekerListResponse> call, Response<JobSeekerListResponse> response) {

               System.out.println("result of api---->"+response.body().toString());


               // Log.d("response data ",response.body().getData());
                ArrayList<Users> seekerlist = (ArrayList<Users>) response.body().getData();
                if(seekerlist != null) {
                    for (Users js : seekerlist) {
                        Log.d("membership_id::", js.getMembershipId());
                        //  if(js.getId()== new PrefManager(getApplicationContext()).getId()) {
                        jseekernames.add(js.getUserName());

                        Log.d("jseekernames::", jseekernames.toString());
                            experience.add(js.getTotalexperience());
                            qualification.add(js.getQualification());
                            phoneno.add(js.getPhone());
                            gender.add(js.getGender());
                            skill1.add(js.getSubject1());
                            skill2.add(js.getSubject2());
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
                        age.add(age1);
//                        // age.add(new PrefManager(getApplicationContext()).)

                        //  }

//                        if(js.getMembershipId().equals("1"))
//                        {
//
//                        }
                    }
                }else
                {
                    Validations.MyAlertBox(SelectedResumes.this,"No Selected Prfiles");
                }
                RecyclerView recyclerView = findViewById(R.id.recy_selectedresumes);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                SelectedProfCustomAdapter customAdapter = new SelectedProfCustomAdapter(SelectedResumes.this,jseekernames,experience,
                        qualification,phoneno,gender,skill1,skill2,age);
                recyclerView.setAdapter(customAdapter);

                    }




            @Override
            public void onFailure(Call<JobSeekerListResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

            }
        });






       // jobseekersList
    }

    private String getAge(int year, int month, int day) {
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
