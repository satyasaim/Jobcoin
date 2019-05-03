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
import com.media3.jobcoin.Pojo.HrAccountResponse;
import com.media3.jobcoin.Pojo.HrData;
import com.media3.jobcoin.Pojo.ResumeUploadResponse;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EditHrAccount extends AppCompatActivity {
    Button bt_hr_edit_save, et_hr_edit_pro_img, bt_hr_edit_upload_img, bt_hr_edit_select_Aadhar_img, bt_upload_Aadhar_img, bt_hr_edit_select_Resume, bt_hr_edit_upload_resume;
    EditText et_hr_edit_name, et_hr_edit_pass, et_hr_edit_Email, et_hr_edit_Phone, et_hr_edit_alt_phone, et_hr_edit_company_type,
            et_hr_edit_qual, et_hr_edit_Address, et_hr_edit_zipcode, et_hr_edit_adhar, et_hr_edit_linked, et_hr_edit_fbook,
            et_hr_edit_pro_pic, et_hr_adhar_img, et_hr_resume;

    String id, name, password, email, phone, altph, comanytype, hrqualification, addres, zipcode, aadharno, linkedIn,
            facebook, profile, AadharImage, file,role,membershipid,membershipname, mediaPath=null;
    ProgressDialog progressDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_edit_account);
        hrAccountUpdateData();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");

        bt_hr_edit_select_Resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 2);
            }
        });

        bt_hr_edit_upload_resume.setOnClickListener(new View.OnClickListener() {
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







        et_hr_edit_name.setEnabled(false);
        et_hr_edit_Email.setEnabled(false);
        et_hr_edit_adhar.setEnabled(false);


        bt_hr_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    updateHrAccount();
                }
                if(!validate())
                {
                    onupdateFailed();
                    return;
                }
            }

            private void onupdateFailed() {
                Toast.makeText(getBaseContext(), "Account Update failed", Toast.LENGTH_LONG).show();
                bt_hr_edit_save.setEnabled(true);
            }

            public boolean validate() {
                boolean valid = true;
                 name = et_hr_edit_name.getText().toString();
                 //password = et_hr_edit_pass.getText().toString();
                 email = et_hr_edit_Email.getText().toString();
                 phone = et_hr_edit_Phone.getText().toString();
                 altph = et_hr_edit_alt_phone.getText().toString();
                 comanytype = et_hr_edit_company_type.getText().toString();
                 hrqualification = et_hr_edit_qual.getText().toString();
                 addres = et_hr_edit_Address.getText().toString();
                 zipcode = et_hr_edit_zipcode.getText().toString();
                 aadharno = et_hr_edit_adhar.getText().toString();
                 linkedIn = et_hr_edit_linked.getText().toString();
                 facebook = et_hr_edit_fbook.getText().toString();
               //  profile = et_hr_edit_pro_pic.getText().toString();
                // AadharImage = et_hr_adhar_img.getText().toString();
                 file = et_hr_resume.getText().toString();

//                if (name.isEmpty()) {
//                    et_hr_edit_name.setError("Enter name");
//                    valid = false;
//                }else {
//                    et_hr_edit_name.setError(null);
//                }

//                if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//                    et_hr_edit_pass.setError("Between 4 and 10 alphanumeric characters");
//                    valid = false;
//                } else {
//                    et_hr_edit_pass.setError(null);
//                }

//                if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    et_hr_edit_Email.setError("enter a valid email address");
//                    valid = false;
//                } else {
//                    et_hr_edit_Email.setError(null);
//                }
                if (phone.isEmpty()) {
                    et_hr_edit_Phone.setError("Enter Phone");
                    valid = false;
                }else {
                    et_hr_edit_Phone.setError(null);
                }
                if (altph.isEmpty()) {
                    et_hr_edit_alt_phone.setError("Enter Alternate Phone");
                    valid = false;
                }else {
                    et_hr_edit_alt_phone.setError(null);
                }
                if (comanytype.isEmpty()) {
                    et_hr_edit_company_type.setError("Enter name");
                    valid = false;
                }else {
                    et_hr_edit_company_type.setError(null);
                }

                if (hrqualification.isEmpty()) {
                    et_hr_edit_qual.setError("Enter Hr Qualification");
                    valid = false;
                }else {
                    et_hr_edit_qual.setError(null);
                }
                if(addres.isEmpty()) {
                    et_hr_edit_Address.setError("Enter Address");
                    valid=false;

                }else {
                    et_hr_edit_Address.setError(null);
                }

                if(zipcode.isEmpty()) {
                    et_hr_edit_zipcode.setError("Enter Zipcode");
                    valid = false;
                }else {
                    et_hr_edit_zipcode.setError(null);
                }

//                if(aadharno.isEmpty()|| aadharno.length() < 10){
//                    et_hr_edit_adhar.setError("Enter 10 digit Aadhar no");
//                    valid = false;
//                }
//                else {
//                    et_hr_edit_adhar.setError(null);
//                }
                if(linkedIn.isEmpty()) {
                    et_hr_edit_linked.setError("Enter linkedIn id");
                    valid = false;

                }else {
                    et_hr_edit_linked.setError(null);
                }
                if(facebook.isEmpty()) {
                    et_hr_edit_fbook.setError("Enter Facebook id");
                    valid = false;
                }else {
                    et_hr_edit_fbook.setError(null);
                }
//                if(profile.isEmpty())
//                {
//                    et_hr_edit_pro_pic.setError("Upload Profile pic");
//                    valid = false;
//                }else {
//                    et_hr_edit_pro_pic.setError(null);
//
//                }

                if(file.isEmpty())
                {
                    et_hr_resume.setError("Upload Resume");
                    valid = false;
                }else {
                    et_hr_resume.setError(null);

                }
//                if(AadharImage.isEmpty())
//                {
//                    et_hr_adhar_img.setError("Upload Profile pic");
//                    valid = false;
//                }else {
//                    et_hr_adhar_img.setError(null);
//
//                }
                return valid;


            }


        });
        id = new PrefManager(getApplicationContext()).getId();
        name = new PrefManager(getApplicationContext()).gethrname();
        password = new PrefManager(getApplicationContext()).gethrpass();
        email = new PrefManager(getApplicationContext()).gethremil();
        phone = new PrefManager(getApplicationContext()).gethrmobile();
        altph = new PrefManager(getApplicationContext()).gethraltph();
        comanytype = new PrefManager(getApplicationContext()).gethrcomtype();
        hrqualification = new PrefManager(getApplicationContext()).gethrqual();
        addres = new PrefManager(getApplicationContext()).gethraddr();
        zipcode = new PrefManager(getApplicationContext()).gethrzip();
        aadharno = new PrefManager(getApplicationContext()).gethraadharno();
        linkedIn = new PrefManager(getApplicationContext()).gethrlink();
        facebook = new PrefManager(getApplicationContext()).gethrface();
        //profile = new PrefManager(getApplicationContext()).gethrppic();
       // AadharImage = new PrefManager(getApplicationContext()).gethraadimg();
        file = new PrefManager(getApplicationContext()).gethrresume();


        et_hr_edit_name.setText(name);
        //et_hr_edit_pass.setText(password);
        et_hr_edit_Email.setText(email);
        et_hr_edit_Phone.setText(phone);
        et_hr_edit_alt_phone.setText(altph);
        et_hr_edit_company_type.setText(comanytype);
        et_hr_edit_qual.setText(hrqualification);
        et_hr_edit_Address.setText(addres);
        et_hr_edit_zipcode.setText(zipcode);
        et_hr_edit_adhar.setText(aadharno);
        et_hr_edit_linked.setText(linkedIn);
        et_hr_edit_fbook.setText(facebook);
        //et_hr_edit_pro_pic.setText(profile);
        //et_hr_adhar_img.setText(AadharImage);
        et_hr_resume.setText(file);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                mediaPath = getPDFPath(selectedImage);
                Log.d(mediaPath,"file path");
                File file1 = new File(mediaPath);
                String s = file1.getName();

                et_hr_resume.setText(s);
            }else {}
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

    private void hrAccountUpdateData() {

        et_hr_edit_name = findViewById(R.id.et_hr_edit_name);
       // et_hr_edit_pass = findViewById(R.id.et_hr_edit_pass);
        et_hr_edit_Email = findViewById(R.id.et_hr_edit_Email);
        et_hr_edit_Phone = findViewById(R.id.et_hr_edit_Phone);
        et_hr_edit_alt_phone = findViewById(R.id.et_hr_edit_alt_phone);
        et_hr_edit_company_type = findViewById(R.id.et_hr_edit_company_type);
        et_hr_edit_qual = findViewById(R.id.et_hr_edit_qual);
        et_hr_edit_Address = findViewById(R.id.et_hr_edit_Address);
        et_hr_edit_zipcode = findViewById(R.id.et_hr_edit_zipcode);
        et_hr_edit_adhar = findViewById(R.id.et_hr_edit_adhar);
        et_hr_edit_linked = findViewById(R.id.et_hr_edit_linked);
        et_hr_edit_fbook = findViewById(R.id.et_hr_edit_fbook);
        //et_hr_edit_pro_pic = findViewById(R.id.et_hr_edit_pro_pic);
        //et_hr_adhar_img = findViewById(R.id.et_hr_adhar_img);
        et_hr_resume = findViewById(R.id.et_hr_resume);
        bt_hr_edit_select_Resume = findViewById(R.id.bt_hr_edit_select_Resume);
        bt_hr_edit_upload_resume = findViewById(R.id.bt_hr_edit_upload_resume);
        bt_hr_edit_save = findViewById(R.id.bt_hr_edit_save);

    }

      // et_hr_edit_pass.getText().toString().trim(),
    public void updateHrAccount() {

        final ProgressDialog progressDialog = new ProgressDialog(EditHrAccount.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait");
       // Toast.makeText();
     // Toast.makeText(getApplicationContext(), et_hr_edit_adhar.getText().toString().trim(), Toast.LENGTH_LONG).show();

        progressDialog.show();
        Api.getapiContext(getApplicationContext());
        Api.getClient().hrAccountUpdate(id, et_hr_edit_name.getText().toString().trim(),et_hr_edit_Email.getText().toString().trim(), et_hr_edit_Phone.getText().toString().trim(),
                et_hr_edit_alt_phone.getText().toString().trim(),et_hr_edit_company_type.getText().toString().trim(), et_hr_edit_Address.getText().toString().trim(),et_hr_edit_zipcode.getText().toString().trim(),
                et_hr_edit_qual.getText().toString().trim(),et_hr_edit_adhar.getText().toString().trim(), et_hr_edit_fbook.getText().toString().trim(),et_hr_edit_linked.getText().toString().trim(),
                et_hr_resume.getText().toString().trim()).enqueue(new retrofit2.Callback<HrAccountResponse>() {

            @Override
            public void onResponse(Call<HrAccountResponse> call, Response<HrAccountResponse> response) {
               // Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_LONG).show();
                Validations.MyAlertBox(EditHrAccount.this,response.body().getMessage());
                HrData data = response.body().getData();
                progressDialog.dismiss();
                id = data.getId();
                name = data.getUserName();
                //password =data.getPassword();
                email=data.getEmail();
                phone= data.getPhone();
                altph = data.getAltPhone();
                comanytype=data.getCompanyType();
                hrqualification =data.getQualification();
                addres = data.getAddress();
                zipcode = data.getZipcode();
                aadharno = data.getAadharNo();
                linkedIn = data.getLinkedIn();
                facebook =data.getFacebook();
                //profile =data.getProfile();
                //AadharImage =data.getAadharImage();
                file = data.getFile();
                role = data.getRole();
                membershipid = data.getMembershipId();
                membershipname = data.getMembership();

                new PrefManager(getApplicationContext()).saveUserData(id,name,password,email,phone,altph,comanytype,hrqualification,addres,zipcode,aadharno,linkedIn,facebook,profile,AadharImage,file,role,membershipid,membershipname);
                Intent intent = new Intent(EditHrAccount.this,HrMainScreen
                        .class);
                    startActivity(intent);
            }

            @Override
            public void onFailure(Call<HrAccountResponse> call, Throwable throwable) {
              //  Toast.makeText(getApplicationContext(), "Update failed", Toast.LENGTH_LONG).show();
               Validations.MyAlertBox(EditHrAccount.this,"Update failed");
                progressDialog.dismiss();

            }
        });


    }


    public void uploadResume() {
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
                        Validations.MyAlertBox(EditHrAccount.this, serverResponse.getMessage());
//                        Intent intent = new Intent(EditHrAccount.this,Login.class);
//                        startActivity(intent);
                        // Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Validations.MyAlertBox(EditHrAccount.this, serverResponse.getMessage());
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
                Validations.MyAlertBox(EditHrAccount.this, "resume upload failed.");
                // Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

//    @Override
//    public void onBackPressed()
//    {
//        //do whatever you want the 'Back' button to do
//        //as an example the 'Back' button is set to start a new Activity named 'NewActivity'
//        this.startActivity(new Intent(EditHrAccount.this,HrMainScreen.class));
//
//        return;
//    }
}
