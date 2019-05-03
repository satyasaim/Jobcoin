package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JobSeekerCourses extends AppCompatActivity {

    Button bt_cert_course_next,bt_cert_course_prev;
    EditText et_certif,et_jobson,et_keyskill,et_keyskill2;
    String certifname,jobson,skill1,skill2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_courses);

        bt_cert_course_next = findViewById(R.id.bt_cert_course_next);
        bt_cert_course_prev = findViewById(R.id.bt_cert_course_prev);
        et_certif = findViewById(R.id.et_certif);
        et_jobson = findViewById(R.id.et_jobson);
        et_keyskill = findViewById(R.id.et_keyskill);
        et_keyskill2 =  findViewById(R.id.et_keyskill2);

        certifname = new PrefManager(getApplicationContext()).getjseekercertificate();
        et_certif.setText(certifname);
        jobson = new PrefManager(getApplicationContext()).getjseekerjobson();
        et_jobson.setText(jobson);
        skill1 = new PrefManager(getApplicationContext()).getjseekersub1();
        et_keyskill.setText(skill1);
        skill2 = new PrefManager(getApplicationContext()).getjseekersub2();
        et_keyskill2.setText(skill2);


        bt_cert_course_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerCourses.this,MeetHrExecutive.class);
                startActivity(intent);
            }
        });

        bt_cert_course_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(validate()) {
                 certifname = et_certif.getText().toString();
                 jobson = et_jobson.getText().toString();
                 skill1 = et_keyskill.getText().toString();
                 skill2 = et_keyskill2.getText().toString();
                 new PrefManager(getApplicationContext()).savejobseekercertificatecourse(certifname,jobson,skill1,skill2);
//                 String cert = new PrefManager(getApplicationContext()).getjseekercertificate();
//                 Toast.makeText(getApplicationContext(),cert,Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(JobSeekerCourses.this, ExperienceAndAddress.class);
                 startActivity(intent);
             }else
             {
                 return;
             }
            }
        });


    }



        public boolean validate(){
            boolean valid = true;
            String certfname = et_certif.getText().toString();
            String jobson = et_jobson.getText().toString();
            String keyskill = et_keyskill.getText().toString();
            String keyskill2 = et_keyskill2.getText().toString();

            if (certfname.isEmpty() ) {
                et_certif.setError("enter certificate name");
                valid = false;
            } else {
                et_certif.setError(null);
            }

            if (jobson.isEmpty() ) {
                et_jobson.setError("enter Jobs on");
                valid = false;
            } else {
                et_jobson.setError(null);
            }
            if (keyskill.isEmpty() ) {
                et_keyskill.setError("enter Key skill name");
                valid = false;
            } else {
                et_keyskill.setError(null);
            }

            if (keyskill2.isEmpty() ) {
                et_keyskill2.setError("enter key skill name");
                valid = false;
            } else {
                et_keyskill2.setError(null);
            }
            return valid;
        }
    }


