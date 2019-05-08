package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class JseekerResume extends AppCompatActivity {
 ImageView img_resume_button;
 EditText et_resume_fullname,et_resume_dob,et_resume_father_name,et_resume_mother_name;
 Spinner et_resume_gender,et_resume_merriage,et_resume_disablity;
 String fullname,dob,fathername,mothername,genders,merriage,disablity,jsid;
 String[] gender ={"Gender","Male","Female","Others"};
 String[] Merital = {"Marital Status","Single","Married"};
 String[] handicap = {"Handicaped Status","No","Yes"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jseeker_resume);
        jseekerData();

        ArrayAdapter genderadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_resume_gender.setAdapter(genderadapter);


        ArrayAdapter merriageadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Merital);
        merriageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_resume_merriage.setAdapter(merriageadapter);

        ArrayAdapter disabilityadapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,handicap);
        disabilityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_resume_disablity.setAdapter(disabilityadapter);


        fullname = new PrefManager(getApplicationContext()).gethrname();
        et_resume_fullname.setText(fullname);
        dob = new PrefManager(getApplicationContext()).getjseekerresumejdob();
        et_resume_dob.setText(dob);
        genders = new PrefManager(getApplicationContext()).getjseekerresumejgender();
        if (genders != null) {
            int spinnerPosition = genderadapter.getPosition(genders);
            et_resume_gender.setSelection(spinnerPosition);
        }
        et_resume_gender.getSelectedItem();
        merriage = new PrefManager(getApplicationContext()).getjseekerresumejmerital();
        if (merriage != null) {
            int spinnerPosition = merriageadapter.getPosition(merriage);
            et_resume_merriage.setSelection(spinnerPosition);
        }
        et_resume_merriage.getSelectedItem();
        disablity = new PrefManager(getApplicationContext()).getjseekerresumejHandicapped();
        if (disablity != null) {
            int spinnerPosition = disabilityadapter.getPosition(disablity);
            et_resume_disablity.setSelection(spinnerPosition);
        }
        et_resume_disablity.getSelectedItem();
        fathername = new PrefManager(getApplicationContext()).getjseekerresumejfatherName();
        et_resume_father_name.setText(fathername);
        mothername = new PrefManager(getApplicationContext()).getjseekerresumejmotherName();
        et_resume_mother_name.setText(mothername);
        img_resume_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                   // jsid = new PrefManager(getApplicationContext()).getId();

                    fullname = et_resume_fullname.getText().toString().trim();
                    dob = et_resume_dob.getText().toString().trim();
                    fathername = et_resume_father_name.getText().toString().trim();
                    mothername = et_resume_mother_name.getText().toString().trim();
                    genders = et_resume_gender.getSelectedItem().toString();
                    merriage = et_resume_merriage.getSelectedItem().toString();
                    disablity = et_resume_disablity.getSelectedItem().toString();
                    new PrefManager(getApplicationContext()).savejobseekerResume(fullname,dob,genders,merriage,disablity,fathername,mothername);

                    //merriage = new PrefManager(getApplicationContext()).getjseekerresumejmerital();
                   // Toast.makeText(getApplicationContext(), merriage, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), mothername, Toast.LENGTH_SHORT).show();
                   // new PrefManager(getApplicationContext()).saveJobSeekerData(jsid,fullname,dob,fathername,mothername,genders,merriage,disablity);
                    Intent intent = new Intent(JseekerResume.this, MeetHrExecutive.class);
                    startActivity(intent);
                    finish();
                } else
                {
                    return;
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



    }

    public boolean validate() {

            boolean valid = true;

            String name = et_resume_fullname.getText().toString();
            String dob = et_resume_dob.getText().toString();
            String faname = et_resume_father_name.getText().toString();
            String maname = et_resume_mother_name.getText().toString();

            if (name.isEmpty() ) {
                et_resume_fullname.setError("enter a name");
                valid = false;
            } else {
                et_resume_fullname.setError(null);
            }

            if (dob.isEmpty() ) {
                et_resume_dob.setError("enter date of birth in YYYY MM DD format");
                valid = false;
            } else {
                et_resume_dob.setError(null);
            }
        if (faname.isEmpty() ) {
            et_resume_father_name.setError("enter father name");
            valid = false;
        } else {
            et_resume_father_name.setError(null);
        }

        if (maname.isEmpty() ) {
            et_resume_mother_name.setError("enter mother name");
            valid = false;
        } else {
            et_resume_mother_name.setError(null);
        }
            return valid;
        }

        public void jseekerData()
        {
            img_resume_button = findViewById(R.id.img_resume_button);
            et_resume_gender = findViewById(R.id.et_resume_gender);
            et_resume_merriage = findViewById(R.id.et_resume_merriage);
            et_resume_disablity = findViewById(R.id.et_resume_disablity);
            et_resume_fullname = findViewById(R.id.et_resume_fullname);
            et_resume_dob = findViewById(R.id.et_resume_dob);
            et_resume_father_name = findViewById(R.id.et_resume_father_name);
            et_resume_mother_name = findViewById(R.id.et_resume_mother_name);
        }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

