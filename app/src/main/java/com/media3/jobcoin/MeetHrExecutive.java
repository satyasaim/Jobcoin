package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MeetHrExecutive extends AppCompatActivity {

String[] qualifiction={"Qualification","SSC","INTER","DIPLOMA","DEGREE","B TECH","M TECH","MBA","MCA"};
Spinner spin_quali,spin_year;
EditText et_grade,et_percentage;
Button bt_qual_Pre,bt_qual_next;
RadioGroup radioGroup;
RadioButton radiotelugu,radioButton3,radioButton2;
int tel,hindi,eng;
String qualification, passyear, grade,percentage,mediumofstudy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jseeker_qualification);
        qualificationData();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,qualifiction);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_quali.setAdapter(aa);


        ArrayList<String> years = new ArrayList<String>();
        years.add("Passed year");
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);


        spin_year.setAdapter(adapter);
//present
        qualification = new PrefManager(getApplicationContext()).getjseekerqual();
        if (qualification != null) {
            int spinnerPosition = aa.getPosition(qualification);
            spin_quali.setSelection(spinnerPosition);
        }
        spin_quali.getSelectedItem();
        passyear = new PrefManager(getApplicationContext()).getjseekerpassyear();
        if (passyear != null) {
            int spinnerPosition = adapter.getPosition(passyear);
            spin_year.setSelection(spinnerPosition);
        }
        spin_year.getSelectedItem();
        grade = new PrefManager(getApplicationContext()).getjseekergrade();
        et_grade.setText(grade);
        percentage = new PrefManager(getApplicationContext()).getjseekerpercentage();
        et_percentage.setText(percentage);
        mediumofstudy = new PrefManager(getApplicationContext()).getjseekermediumofstudy();
        if(mediumofstudy.equalsIgnoreCase("telugu"))
                   {
                       radiotelugu.setChecked(true);
                   }else
                       if(mediumofstudy.equalsIgnoreCase("hindi"))
                       {
                           radioButton3.setChecked(true);
                       }else
                       {
                           radioButton2.setChecked(true);
                       }







        bt_qual_Pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetHrExecutive.this, JseekerResume.class);
                startActivity(intent);

            }
        });
        bt_qual_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    qualification = spin_quali.getSelectedItem().toString();
                    passyear = spin_year.getSelectedItem().toString();
                    grade = et_grade.getText().toString();
                    percentage = et_percentage.getText().toString();
                    mediumofstudy =((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();


                    new PrefManager(getApplicationContext()).savejobseekerQualification(qualification,passyear,grade,percentage,mediumofstudy);

//                    String language = new PrefManager(getApplicationContext()).getjseekermediumofstudy();
//                    Toast.makeText(getApplicationContext(), language, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MeetHrExecutive.this, JobSeekerCourses.class);
                    startActivity(intent);
                }else
                {
                    return;
                }
            }
        });
    }

    public boolean validate() {

        boolean valid = true;

        String grade = et_grade.getText().toString();
        //Toast.makeText(MeetHrExecutive.this,grade);
       // Toast.makeText(getApplicationContext(), grade, Toast.LENGTH_LONG).show();
        String percen = et_percentage.getText().toString();
       //String maname = et_resume_mother_name.getText().toString();

        if (grade.isEmpty() ) {
            et_grade.setError("enter grade");
            valid = false;
        } else {
            et_grade.setError(null);
        }

        if (percen.isEmpty() ) {
            et_percentage.setError("enter percentage");
            valid = false;
        } else {
            et_percentage.setError(null);
        }
        int i = radioGroup.getCheckedRadioButtonId();
        if( i<= 0){
            radioButton2.setError("Select Item");//Set error to last Radio button
            valid = false;
        }else
        {
            radioButton2.setError(null);
        }
//        if(mediumofstudy.isEmpty()|| mediumofstudy.equals(null))
//        {
//            radioGroup.setche("select one option");
//            radioButton2.setChecked(true);
//            mediumofstudy = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
//
//        }
//       if(mediumofstudy.isEmpty())
//       {
//           radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//               @Override
//               public void onCheckedChanged(RadioGroup group, int checkedId) {
//                   // find which radio button is selected
//                   if(checkedId == R.id.radiotelugu) {
//                       radioGroup.setId(radiotelugu.getId());
//                   } else if(checkedId == R.id.radioButton3) {
//                       radioGroup.setId(radioButton3.getId());
//                   } else {
//                       radioGroup.setId(radioButton2.getId());
//                   }
//               }
//
//           });
//       }
      //  mediumofstudy =((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
//        if (maname.isEmpty() ) {
//            et_resume_mother_name.setError("enter mother name");
//            valid = false;
//        } else {
//            et_resume_mother_name.setError(null);
//        }
        return valid;
    }

    public void qualificationData(){
        spin_year = findViewById(R.id.spin_year);
        spin_quali = findViewById(R.id.spin_quali);
        bt_qual_next = findViewById(R.id.bt_qual_next);
        bt_qual_Pre = findViewById(R.id.bt_qual_Pre);
        et_grade = findViewById(R.id.et_grade);
        et_percentage = findViewById(R.id.et_percentage);
        radioGroup = findViewById(R.id.radioGroup);
        radiotelugu = findViewById(R.id.radiotelugu);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton2 = findViewById(R.id.radioButton2);
        //RadioGroup radioGroup =  findViewById(R.id.radioGroup);

    }
}
