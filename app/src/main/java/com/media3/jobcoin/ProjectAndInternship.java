package com.media3.jobcoin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ProjectAndInternship extends AppCompatActivity {

    Button bt_project_prev,bt_project_next;
    CheckBox ch_tel_read,ch_tel_right, ch_tel_speak,ch_eng_read,ch_eng_right,ch_eng_speak,ch_hindi_read,ch_hindi_right,ch_hindi_speak,ch_oria_read,ch_oria_right,ch_oria_speak;

    EditText et_project_title,et_project_desc;
    //ArrayList<String> tel_checkbox = new ArrayList<String>();
    StringBuilder tel_checkbox = new StringBuilder();
    StringBuilder eng_checkbox = new StringBuilder();
    StringBuilder hindi_checkbox = new StringBuilder();
    StringBuilder oria_checkbox = new StringBuilder();
    String protitle,prodesc;
   // String[] telugu,english,hindi,oria;
    String telread,telwrite,telspeak,engread,engwrite,engspeak,hindiread,hindiwrite,hindispeak,oriaread,oriawrite,oriaspeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_and_internship);
        projectandinternshipData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);//ch_tel_read.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        if(ch_tel_read.isChecked())
//        {
//            tel_checkbox.append("read"+",");
//
//        }
//    }
//});
//        ch_tel_right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ch_tel_right.isChecked())
//                {
//                    tel_checkbox.append("write"+",");
//                }
//            }
//        });
//        ch_tel_speak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ch_tel_speak.isChecked())
//                {
//                    tel_checkbox.append("speak");
//                }
//            }
//        });




        //Log.d(tel_checkbox.get(0),"checkbox ");

        protitle = new PrefManager(getApplicationContext()).getjseekerprojtitle();
        et_project_title.setText(protitle);
        prodesc = new PrefManager(getApplicationContext()).getjseekerprojdesc();
        et_project_desc.setText(prodesc);
        telread = new PrefManager(getApplicationContext()).getjseekerteluguvalues();
        telread.split(" ");
//        telread.setText();
//        telwrite.setText()

       // tel_checkbox = new PrefManager(getApplicationContext().getjseekerteluguvalues());


        bt_project_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectAndInternship.this,ExperienceAndAddress.class);
                startActivity(intent);
            }
        });
        bt_project_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {

                    if(ch_tel_read.isChecked())
                    {
                        tel_checkbox.append("read"+" ");

                    }
                    if(ch_tel_right.isChecked())
                    {
                        tel_checkbox.append("write"+" ");
                    }
                    if(ch_tel_speak.isChecked())
                    {
                        tel_checkbox.append("speak");
                    }
                    Log.d(String.valueOf(tel_checkbox),"checkbox");
                   // Toast.makeText(getApplicationContext(),tel_checkbox.toString(),Toast.LENGTH_SHORT).show();
                    if(ch_eng_read.isChecked())
                    {
                        eng_checkbox.append("read"+" ");
                    }
                    if(ch_eng_right.isChecked())
                    {
                        eng_checkbox.append("write"+" ");
                    } if(ch_eng_speak.isChecked())
                    {
                        eng_checkbox.append("speak");
                    }
                   // Toast.makeText(getApplicationContext(),eng_checkbox.toString(),Toast.LENGTH_SHORT).show();
                    if(ch_hindi_read.isChecked())
                    {
                       hindi_checkbox.append("read"+" ");
                    }
                    if(ch_hindi_right.isChecked())
                    {
                        hindi_checkbox.append("write"+" ");
                    } if(ch_hindi_speak.isChecked())
                    {
                        hindi_checkbox.append("speak");
                    }
                   // Toast.makeText(getApplicationContext(),hindi_checkbox.toString(),Toast.LENGTH_SHORT).show();
                    if(ch_oria_read.isChecked())
                    {
                        oria_checkbox.append("read"+" ");
                    }
                    if(ch_oria_right.isChecked())
                    {
                        oria_checkbox.append("write"+" ");
                    } if(ch_oria_speak.isChecked())
                    {
                        oria_checkbox.append("speak");
                    }

                  //  Toast.makeText(getApplicationContext(),oria_checkbox.toString(),Toast.LENGTH_SHORT).show();

                    protitle = et_project_title.getText().toString();
                    prodesc = et_project_desc.getText().toString();
                    //telread = ch_tel_read.isChecked();
                    new PrefManager(getApplicationContext()).savejobseekerprojectintern(protitle,prodesc,tel_checkbox,eng_checkbox,hindi_checkbox,oria_checkbox);
                   // String protitle = new PrefManager(getApplicationContext()).getjseekerprojtitle();
                    //Toast.makeText(getApplicationContext(),protitle,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProjectAndInternship.this, ContactAndResumeupload.class);
                    startActivity(intent);
                }else
                {
                    return;
                }
            }

            public boolean validate() {
                boolean valid = true;

                String protitle = et_project_title.getText().toString();
                String projdesc = et_project_desc.getText().toString();

                if (protitle.isEmpty()) {
                    et_project_title.setError("enter project title");
                    valid = false;
                } else {
                    et_project_title.setError(null);
                }

                if (projdesc.isEmpty() ) {
                    et_project_desc.setError("enter project description");
                    valid = false;
                } else {
                    et_project_desc.setError(null);
                }

                return valid;
            }
        });

    }
    public void projectandinternshipData()
    {
        bt_project_prev = findViewById(R.id.bt_project_prev);
        bt_project_next = findViewById(R.id.bt_project_next);
        et_project_title = findViewById(R.id.et_project_title);
        et_project_desc = findViewById(R.id.et_project_desc);
        ch_tel_read = findViewById(R.id.ch_tel_read);
        ch_tel_right = findViewById(R.id.ch_tel_right);
        ch_tel_speak = findViewById(R.id.ch_tel_speak);
        ch_eng_read = findViewById(R.id.ch_eng_read);
        ch_eng_right = findViewById(R.id.ch_eng_right);
        ch_eng_speak = findViewById(R.id.ch_eng_speak);
        ch_hindi_read = findViewById(R.id.ch_hindi_read);
        ch_hindi_right = findViewById(R.id.ch_hindi_right);
        ch_hindi_speak = findViewById(R.id.ch_hindi_speak);
        ch_oria_read = findViewById(R.id.ch_oria_read);
        ch_oria_right = findViewById(R.id.ch_oria_right);
        ch_oria_speak = findViewById(R.id.ch_oria_speak);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
