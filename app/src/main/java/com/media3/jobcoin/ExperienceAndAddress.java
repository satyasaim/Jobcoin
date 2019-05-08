package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExperienceAndAddress extends AppCompatActivity {
    Button bt_exp_prev,bt_exp_next;
    EditText et_experience,et_perm_addres,et_pre_addres,et_location;
String experience,permadd,presadd,location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience_and_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        bt_exp_prev = findViewById(R.id.bt_exp_prev);
        bt_exp_next = findViewById(R.id.bt_exp_next);
        et_experience = findViewById(R.id.et_experience);
        et_perm_addres = findViewById(R.id.et_perm_addres);
        et_pre_addres = findViewById(R.id.et_pre_addres);
        et_location = findViewById(R.id.et_location);


        experience = new PrefManager(getApplicationContext()).getjseekerexperience();
        et_experience.setText(experience);
        permadd = new PrefManager(getApplicationContext()).getjseekerpermadd();
        et_perm_addres.setText(permadd);
        presadd = new PrefManager(getApplicationContext()).getjseekerpreadd();
        et_pre_addres.setText(presadd);
        location = new PrefManager(getApplicationContext()).getjseekerlocation();
        et_location.setText(location);


        bt_exp_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExperienceAndAddress.this, JobSeekerCourses.class);
                startActivity(intent);
            }
        });

        bt_exp_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    experience = et_experience.getText().toString();
                    permadd = et_perm_addres.getText().toString();
                    presadd = et_pre_addres.getText().toString();
                    location = et_location.getText().toString();
                    new PrefManager(getApplicationContext()).savejobseekerexpandaddr(experience,permadd,presadd,location);

//                    String exp =  new PrefManager(getApplicationContext()).getjseekerexperience();
//                    Toast.makeText(getApplicationContext(),exp,Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ExperienceAndAddress.this, ProjectAndInternship.class);
                    startActivity(intent);
                }else
                {
                    return;
                }
            }


                public boolean validate(){

                    boolean valid = true;

                    String experience = et_experience.getText().toString();
                    String permadd = et_perm_addres.getText().toString();
                    String presadd = et_pre_addres.getText().toString();
                    String location = et_location.getText().toString();

                    if (experience.isEmpty() || experience.length()>3 ) {
                        et_experience.setError("Enter proper Experience");
                        valid = false;
                    } else {
                        et_experience.setError(null);
                    }

                    if (permadd.isEmpty() ) {
                        et_perm_addres.setError("Enter Permenant Address");
                        valid = false;
                    } else {
                        et_perm_addres.setError(null);
                    }
                    if (presadd.isEmpty() ) {
                        et_pre_addres.setError("Enter Present Address");
                        valid = false;
                    } else {
                        et_pre_addres.setError(null);
                    }

                    if (location.isEmpty() ) {
                        et_location.setError("Enter Location");
                        valid = false;
                    } else {
                        et_location.setError(null);
                    }
                    return valid;
                }


        });

    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
