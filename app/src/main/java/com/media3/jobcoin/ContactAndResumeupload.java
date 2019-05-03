package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.HrData;
import com.media3.jobcoin.Pojo.JseekerAccountUpdateResponse;
import com.media3.jobcoin.Pojo.ResumeUploadResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactAndResumeupload extends AppCompatActivity {

    Button bt_cont_previous,bt_cont_update,bt_cho_resume,bt_upload_jseeker_resume;
    EditText et_contact_email,et_contact_phone,et_resumename;
    String id,email,phone,altph,resume;
    String fullname,dob,fathername,mothername,genders,merriage,disablity,jsid,qualification, passyear,
            grade,percentage,mediumofstudy,certifname,jobson,skill1,skill2,experience,permadd,presadd,location,protitle,prodesc,mediaPath = null;
   StringBuilder tel_checkbox,eng_checkbox,hindi_checkbox,oria_checkbox;
 ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_and_resumeupload);
        bt_cont_previous = findViewById(R.id.bt_cont_previous);
        bt_cont_update = findViewById(R.id.bt_cont_update);
        et_contact_email = findViewById(R.id.et_contact_email);
        et_contact_phone = findViewById(R.id.et_contact_phone);
        et_resumename = findViewById(R.id.et_resumename);
        bt_cho_resume = findViewById(R.id.bt_cho_resume);
        bt_upload_jseeker_resume = findViewById(R.id.bt_upload_jseeker_resume);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        
        resume = new PrefManager(getApplicationContext()).getjseekerresume();
        et_resumename.setText(resume);

        id = new PrefManager(getApplicationContext()).getId();
        email = new PrefManager(getApplicationContext()).gethremil();
        phone = new PrefManager(getApplicationContext()).gethrmobile();
        altph = new PrefManager(getApplicationContext()).gethraltph();
        fullname = new PrefManager(getApplicationContext()).getjseekerresumename();
        dob = new PrefManager(getApplicationContext()).getjseekerresumejdob();
        fathername = new PrefManager(getApplicationContext()).getjseekerresumejfatherName();
        mothername = new PrefManager(getApplicationContext()).getjseekerresumejmotherName();
        genders = new PrefManager(getApplicationContext()).getjseekerresumejgender();
        merriage = new PrefManager(getApplicationContext()).getjseekerresumejmerital();
        disablity = new PrefManager(getApplicationContext()).getjseekerresumejHandicapped();
        qualification = new PrefManager(getApplicationContext()).getjseekerqual();
        passyear = new PrefManager(getApplicationContext()).getjseekerpassyear();
        grade = new PrefManager(getApplicationContext()).getjseekergrade();
        percentage = new PrefManager(getApplicationContext()).getjseekerpercentage();
        mediumofstudy = new PrefManager(getApplicationContext()).getjseekermediumofstudy();
        certifname = new PrefManager(getApplicationContext()).getjseekercertificate();
        jobson = new PrefManager(getApplicationContext()).getjseekerjobson();
        skill1 = new PrefManager(getApplicationContext()).getjseekersub1();
        skill2 = new PrefManager(getApplicationContext()).getjseekersub2();
        experience = new PrefManager(getApplicationContext()).getjseekerexperience();
        permadd = new PrefManager(getApplicationContext()).getjseekerpermadd();
        presadd = new PrefManager(getApplicationContext()).getjseekerpreadd();
        location = new PrefManager(getApplicationContext()).getjseekerlocation();
        protitle = new PrefManager(getApplicationContext()).getjseekerprojtitle();
        prodesc = new PrefManager(getApplicationContext()).getjseekerprojdesc();















        et_contact_email.setText(email);
        et_contact_phone.setText(phone);
        et_resumename.setText(resume);

        et_contact_email.setEnabled(false);
        et_contact_phone.setEnabled(false);

        //Toast.makeText(getApplicationContext(),et_contact_email.getText().toString(),Toast.LENGTH_SHORT).show();

        bt_cont_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactAndResumeupload.this,ProjectAndInternship.class);
                startActivity(intent);
            }
        });
        bt_cont_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {



                    resume = et_resumename.getText().toString();
                 new PrefManager(getApplicationContext()).savejobseekerContactResume(email,phone,resume);
                // String jemail = new PrefManager(getApplicationContext()).getjseekeremail();
                 //Toast.makeText(getApplicationContext(),jemail,Toast.LENGTH_SHORT).show();
                    jseekerAccountdataUpdate();
                    //Validations.MyAlertBox(ContactAndResumeupload.this,"updated successfully");
                   Validations.MyAlertBoxIntent(ContactAndResumeupload.this,"updated successfully",JobSeekerMainScreen.class);
//                    Intent intent = new Intent(ContactAndResumeupload.this, JobSeekerMainScreen.class);
//                    startActivity(intent);

                }else
                {
                    return;
                }
            }


        });

        bt_upload_jseeker_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPath == null)
                {
                    // Picasso.with(getApplicationContext()).load("https://jobcoin.co.in/uploads/profile-pic.png").resize(200, 200).into(et_hr_pro_img);
                    mediaPath = "https://jobcoin.co.in/uploads/profile-pic.png";
                }else {

                    // uploadAadharImage();
                    uploadResume();

                }


            }
        });

        bt_cho_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 2);
            }
        });
        
        
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                mediaPath = getPDFPath(selectedImage);
                Log.d(mediaPath,"file path");
                File file1 = new File(mediaPath);
                String s = file1.getName();

                et_resumename.setText(s);
            }else
            {

            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String getPDFPath(Uri selectedImage) {
        final String id = DocumentsContract.getDocumentId(selectedImage);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private void uploadResume() {
        progressDialog.show();
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());


        Log.d("filename",file.getName());
        Call<ResumeUploadResponse> getResponse =  Api.getClient().uploadResume(fileToUpload, filename);

        getResponse.enqueue(new Callback<ResumeUploadResponse>() {
            @Override
            public void onResponse(Call<ResumeUploadResponse> call, Response<ResumeUploadResponse> response) {
                Log.d("inside response","test");
                ResumeUploadResponse serverResponse = (ResumeUploadResponse) response.body();
                String s = serverResponse.getStatus();
                Log.d("inside response",s);
                if (serverResponse != null) {
                    if (serverResponse.getStatus().equalsIgnoreCase("success")) {
                        Validations.MyAlertBox(ContactAndResumeupload.this, serverResponse.getMessage());
                        // Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Validations.MyAlertBox(ContactAndResumeupload.this, serverResponse.getMessage());
                        // Toast.makeText(getApplicationContext(),"testing"+ serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Validations.MyAlertBox(ContactAndResumeupload.this, "resume upload failed.");
                // Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    private void jseekerAccountdataUpdate() {

        final ProgressDialog progressDialog = new ProgressDialog(ContactAndResumeupload.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        Api.getapiContext(getApplicationContext());
        Api.getClient().jseekerAccountUpdate(id,fullname,email,phone,fathername,mothername,
                dob,permadd,presadd,location,genders,merriage,disablity,qualification,passyear,
                percentage,jobson,skill1,skill2,certifname,experience,protitle,prodesc,grade,
                et_resumename.getText().toString().trim(),mediumofstudy).enqueue(new retrofit2.Callback<JseekerAccountUpdateResponse>()
        {


            @Override
            public void onResponse(Call<JseekerAccountUpdateResponse> call, Response<JseekerAccountUpdateResponse> response) {
               // Validations.MyAlertBox(ContactAndResumeupload.this,response.body().getMessage());
                //progressDialog.dismiss();
                if (response.body().getStatus().equals("success")) {
                    HrData data = response.body().getData();

                    id = data.getId();
                    fullname = data.getUserName();
                    email = data.getEmail();
                    phone = data.getPhone();
                    fathername = data.getFatherName();
                    mothername = data.getMotherName();
                    dob = data.getDateOfBirth();
                    permadd = data.getPermenentAdress();
                    presadd = data.getPresentAddress();
                    location = data.getMandal();
                    genders = data.getGender();
                    merriage = data.getMarital();
                    disablity = data.getHandicapped();
                    qualification = data.getQualification();
                    passyear = data.getYearOfPassing();
                    percentage = data.getPercentage();
                    jobson = data.getSubject1();
                    skill1 = data.getSubject2();
                    skill2 = data.getSubject3();
                    certifname = data.getHighercertificateCourses();
                    experience = data.getTotalexperience();
                    protitle = data.getProjectTitle();
                    prodesc = data.getProjectDescription();
                    grade = data.getGrade();
                    resume = data.getFile();
                    mediumofstudy = data.getStudy();

                    new PrefManager(getApplicationContext()).savejobseekerResume(fullname,dob,genders,merriage,disablity,fathername,mothername);
                    new PrefManager(getApplicationContext()).savejobseekerQualification(qualification,passyear,grade,percentage,mediumofstudy);
                    new PrefManager(getApplicationContext()).savejobseekercertificatecourse(certifname,jobson,skill1,skill2);
                    new PrefManager(getApplicationContext()).savejobseekerexpandaddr(experience,permadd,presadd,location);
                    new PrefManager(getApplicationContext()).savejobseekerprojectintern(protitle,prodesc,tel_checkbox,eng_checkbox,hindi_checkbox,oria_checkbox);
                    new PrefManager(getApplicationContext()).savejobseekerContactResume(email,phone,resume);
                }



            }

            @Override
            public void onFailure(Call<JseekerAccountUpdateResponse> call, Throwable t) {
                Validations.MyAlertBox(ContactAndResumeupload.this,"update failed");
                progressDialog.dismiss();
            }
        });
    }

    public boolean validate() {

        boolean valid = true;

        String email = et_contact_email.getText().toString();
        String phone = et_contact_phone.getText().toString();
        String resume = et_resumename.getText().toString();


        if (email.isEmpty()||!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_contact_email.setError("enter email");
            valid = false;
        } else {
            et_contact_email.setError(null);
        }

        if (phone.isEmpty() ) {
            et_contact_phone.setError("enter phone");
            valid = false;
        } else {
            et_contact_phone.setError(null);
        }

        if (resume.isEmpty() ) {
            et_resumename.setError("upload Resume");
            valid = false;
        } else {
            et_resumename.setError(null);
        }


        return valid;
    }


}
