package com.media3.jobcoin;

import android.app.ProgressDialog;
//import android.content.Context;
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

public class HrMainScreen extends AppCompatActivity {
Button bt_coin_earnings,bt_logout,tb_myaccount,bt_hr_find_resume,bt_selected_resumes ;
ImageView img_hr;
String mediaPath = null,imgurl,imgname;
ProgressDialog progressDialog;
    //public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_main_screen);
        bt_coin_earnings = findViewById(R.id.bt_coin_earnings);
        bt_logout = findViewById(R.id.bt_logout);
        tb_myaccount = findViewById(R.id.tb_myaccount);
        bt_hr_find_resume = findViewById(R.id.bt_hr_find_resume);
        bt_selected_resumes = findViewById(R.id.bt_selected_resumes);
        img_hr = findViewById(R.id.img_hr);


        // img_hr.setImageBitmap(BitmapFactory.decodeFile(imgurl));
        String Path = new PrefManager(getApplicationContext()).getImgurl();
        Log.d(Path, "image url from shard");
        String name = new PrefManager(getApplicationContext()).getImgname();
        Log.d(name, "image name from shard");

        bt_coin_earnings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrMainScreen.this, CoinEarnings.class);
                startActivity(intent);

            }
        });
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrMainScreen.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        tb_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrMainScreen.this, EditHrAccount.class);
                startActivity(intent);

            }
        });
        bt_hr_find_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrMainScreen.this, FindResumes.class);
                startActivity(intent);

            }
        });
        bt_selected_resumes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HrMainScreen.this, SelectedResumes.class);
                startActivity(intent);
            }
        });


        img_hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });


//        Picasso.with(HrMainScreen.this).load(mediaPath).resize(200, 200).
//                centerCrop().into(img_hr);

//        if (imgurl == null) {
//             Picasso.with(HrMainScreen.this).load("https://jobcoin.co.in/uploads/profile-pic.png").resize(200, 200).into(img_hr);
//            //imgurl = "https://jobcoin.co.in/uploads/profile-pic.png";
//        } else {
//            imgurl = new PrefManager(getApplicationContext()).getImgurl();
//            Log.d(imgurl, "image url from shard");
//            //Picasso.with(HrMainScreen.this).load(imgurl).into(img_hr);
//            Picasso.with(HrMainScreen.this).load(imgurl).resize(200, 200).
//                    centerCrop().into(img_hr);
//            Log.d(imgurl, "after shared pr");
//       }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

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
                img_hr.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                if(mediaPath == null)
                {
                    // Picasso.with(getApplicationContext()).load("https://jobcoin.co.in/uploads/profile-pic.png").resize(200, 200).into(et_hr_pro_img);
                    mediaPath = "https://jobcoin.co.in/uploads/profile-pic.png";
                }else {
                    uploadFile();

                }
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
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                } else {
                    assert serverResponse != null;
                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

}
