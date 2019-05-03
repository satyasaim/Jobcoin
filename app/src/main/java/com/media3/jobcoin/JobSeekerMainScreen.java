package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.ImageUploadResponse;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobSeekerMainScreen extends AppCompatActivity {
Button bt_seeker_resume,bt_seeker_logout,tb_seeker_myaccount,bt_seeker_buycoin,bt_meet_hr_executive ;
ImageView img_seeker;
 String mediaPath = null,imgurl,imgname;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_seeker_main_screen);
        bt_seeker_resume = findViewById(R.id.bt_seeker_resume);
        bt_seeker_logout = findViewById(R.id.bt_seeker_logout);
        //tb_seeker_myaccount = findViewById(R.id.tb_seeker_myaccount);
        bt_seeker_buycoin = findViewById(R.id.bt_seeker_buycoin);
        bt_meet_hr_executive = findViewById(R.id.bt_meet_hr_executive);
        img_seeker = findViewById(R.id.img_seeker);
        bt_seeker_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerMainScreen.this,JseekerResume.class);
                startActivity(intent);
            }
        });
        bt_seeker_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerMainScreen.this,Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
//        tb_seeker_myaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(JobSeekerMainScreen.this, EditHrAccount.class);
//                startActivity(intent);
//
//            }
//        });
        bt_seeker_buycoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerMainScreen.this,BuyCoin.class);
                startActivity(intent);

            }
        });
        bt_meet_hr_executive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobSeekerMainScreen.this,SelectYrHr.class);
                startActivity(intent);
            }
        });
        img_seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });


        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {


                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);

                File file1 = new File(mediaPath);
                String s = file1.getName();
                Log.d(mediaPath,"file path");
                // et_hr_pro_pic.setText(s);
                // Set the Image in ImageView for Previewing the Media
                img_seeker.setImageBitmap(BitmapFactory.decodeFile(mediaPath));

                uploadFile();
                cursor.close();

            } else {
                //Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public void uploadFile() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("profile", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Log.d("filename",file.getName());
        Call<ImageUploadResponse> getResponse =  Api.getClient().uploadImage(fileToUpload, filename);

        getResponse.enqueue(new Callback<ImageUploadResponse>() {
            @Override
            public void onResponse(Call<ImageUploadResponse> call, Response<ImageUploadResponse> response) {
                Log.d("inside response","test");
                ImageUploadResponse serverResponse = (ImageUploadResponse) response.body();
                String s = serverResponse.getStatus();
                imgurl = serverResponse.getUrl();
                Log.d(imgurl,"img url from response");
                imgname = serverResponse.getProfile();
                new PrefManager(getApplicationContext()).saveImgurl(imgurl,imgname);

                Log.d("inside response",s);
                if (serverResponse != null) {
                    if (serverResponse.getStatus().equalsIgnoreCase("success")) {
                        Validations.MyAlertBox(JobSeekerMainScreen.this,serverResponse.getMessage());
                      //  Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();

                    } else {
                        Validations.MyAlertBox(JobSeekerMainScreen.this,serverResponse.getMessage());
                        //Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.d("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Validations.MyAlertBox(JobSeekerMainScreen.this,"fail");
               // Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
