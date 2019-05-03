package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.AadharUploadResponse;
import com.media3.jobcoin.Pojo.HrData;
import com.media3.jobcoin.Pojo.HrSignUpResponse;
import com.media3.jobcoin.Pojo.ImageUploadResponse;
import com.media3.jobcoin.Pojo.JSeekerSignUpResponse;
import com.media3.jobcoin.Pojo.ResumeUploadResponse;
import com.squareup.picasso.Picasso;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HrSignup extends AppCompatActivity {
   Button bt_hr_signup,bt_hr_upload_img,bt_select_Aadhar_file,bt_upload_Aadhar_file,et_hr_pro_img,bt_select_Resume,bt_upload_resume;
  // ImageView et_hr_pro_img;
   String mediaPath = null;
   String adharmediaPath = null;
   //String filePath = null;
   // String[] mediaColumns = { MediaStore.Video.Media._ID };
   ProgressDialog progressDialog;
    TextView textView;
   EditText et_hr_sign_name,et_hr_sign_pass,et_hr_Email,et_hr_Phone,et_hr_alt_phone,et_hr_company_type,et_hr_qual,et_hr_Address,
           et_hr_Zipcode,et_hr_adhar,et_hr_linked,et_hr_fbook,et_hr_pro_pic,et_hr_adhar_img,et_hr_resume;
   ImageView img_hr_Back;
   String id,name,password,email,phone,altph,companytype,hrqualification,addres,zipcode,aadharno,linkedIn,
           facebook,profile,AadharImage,file;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_hr_signup);
      textView = (TextView)findViewById(R.id.tv_login);
//       et_hr_sign_name.setFocusableInTouchMode(true);
//       et_hr_sign_name.requestFocus();
      hrSignupData();
      progressDialog = new ProgressDialog(this);
      progressDialog.setMessage("Uploading...");

             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
             getSupportActionBar().setHomeButtonEnabled(true);
              textView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent ilogin = new Intent(HrSignup.this,Login.class);
                      startActivity(ilogin);
                  }
              });
      bt_hr_upload_img.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(mediaPath == null)
            {
               // Picasso.with(getApplicationContext()).load("https://jobcoin.co.in/uploads/profile-pic.png").resize(200, 200).into(et_hr_pro_img);
               mediaPath = "https://jobcoin.co.in/uploads/profile-pic.png";
            }else {

               uploadFile();

            }
         }
      });
      et_hr_pro_img.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 0);

         }
      });
      bt_upload_Aadhar_file.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if(adharmediaPath == null)
            {
               // Picasso.with(getApplicationContext()).load("https://jobcoin.co.in/uploads/profile-pic.png").resize(200, 200).into(et_hr_pro_img);
                adharmediaPath = "https://jobcoin.co.in/uploads/profile-pic.png";
            }else {

               uploadAadharImage();

            }

         }
      });
      bt_select_Aadhar_file.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, 1);
         }
      });


       bt_upload_resume.setOnClickListener(new View.OnClickListener() {
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
       bt_select_Resume.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //ACTION_GET_CONTENT
//               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//               intent.setType("application/msword,application/pdf");
//               intent.addCategory(Intent.CATEGORY_OPENABLE);
//
//               startActivityForResult(intent, 2);

               Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               intent.setType("*/*");
               startActivityForResult(intent, 2);
           }
       });




      bt_hr_signup.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//                Intent intent = new Intent(HrSignup.this,HrMainScreen.class);
//                startActivity(intent);
             if(validate()) {
                 hrRegistration();
             }
             if(!validate())
             {
                 onSignupFailed();
                 return;
             }
         }

          public void onSignupFailed() {
             Validations.MyAlertBox(HrSignup.this,"SignUp failed");
              //Toast.makeText(getBaseContext(), "SignUp failed", Toast.LENGTH_LONG).show();
              bt_hr_signup.setEnabled(true);
          }

          public boolean validate() {
              boolean valid = true;
              String name = et_hr_sign_name.getText().toString();
              String password = et_hr_sign_pass.getText().toString();
              String email = et_hr_Email.getText().toString().trim();
              String phone = et_hr_Phone.getText().toString();
              //String altphone = et_hr_alt_phone.getText().toString();
             // String companytype = et_hr_company_type.getText().toString();
              String hrquali = et_hr_qual.getText().toString();
             // String hraddres = et_hr_Address.getText().toString();
             // String zipcode = et_hr_Zipcode.getText().toString();
             // String aadharno = et_hr_adhar.getText().toString();
             // String linkedin = et_hr_linked.getText().toString();
             // String fbook = et_hr_fbook.getText().toString();
              String ppic = et_hr_pro_pic.getText().toString();
              String aadharpic = et_hr_adhar_img.getText().toString();
              String resume = et_hr_resume.getText().toString();





              if (name.isEmpty()) {
                  et_hr_sign_name.setError("Enter name");
                  valid = false;
              }else {
                  et_hr_sign_name.setError(null);
              }

              if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                  et_hr_sign_pass.setError("Between 4 and 10 alphanumeric characters");
                  valid = false;
              } else {
                  et_hr_sign_pass.setError(null);
              }

              if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                  et_hr_Email.setError("enter a valid email address");
                  valid = false;
              } else {
                  et_hr_Email.setError(null);
              }
              if (phone.isEmpty() || phone.length()>10) {
                  et_hr_Phone.setError("Enter Phone");
                  valid = false;
              }else {
                  et_hr_Phone.setError(null);
              }
//              if (altphone.isEmpty()) {
//                  et_hr_alt_phone.setError("Enter Alternate Phone");
//                  valid = false;
//              }else {
//                  et_hr_alt_phone.setError(null);
//              }
//              if (companytype.isEmpty()) {
//                  et_hr_company_type.setError("Enter name");
//                  valid = false;
//              }else {
//                  et_hr_company_type.setError(null);
//              }

              if (hrquali.isEmpty()) {
                  et_hr_qual.setError("Enter Hr Qualification");
                  valid = false;
              }else {
                  et_hr_qual.setError(null);
              }
//              if(hraddres.isEmpty()) {
//                  et_hr_Address.setError("Enter Address");
//                  valid=false;
//
//              }else {
//                  et_hr_Address.setError(null);
//              }
//
//              if(zipcode.isEmpty()) {
//                  et_hr_Zipcode.setError("Enter Zipcode");
//                  valid = false;
//              }else {
//                  et_hr_Zipcode.setError(null);
//              }
//
//              if(aadharno.isEmpty()|| aadharno.length() < 10){
//                  et_hr_adhar.setError("Enter 10 digit Aadhar no");
//                  valid = false;
//              }
//              else {
//                  et_hr_adhar.setError(null);
//              }
//              if(linkedin.isEmpty()) {
//                  et_hr_linked.setError("Enter linkedIn id");
//                  valid = false;
//
//              }else {
//                  et_hr_linked.setError(null);
//              }
//             if(fbook.isEmpty()) {
//                 et_hr_fbook.setError("Enter Facebook id");
//                 valid = false;
//             }else {
//                 et_hr_fbook.setError(null);
//             }
             if(ppic.isEmpty())
             {
                     et_hr_pro_pic.setError("Upload Profile pic");
                     valid = false;
             }else {
                 et_hr_pro_pic.setError(null);

             }
              if(aadharpic.isEmpty())
              {
                  et_hr_adhar_img.setError("Upload Aadhar pic");
                  valid = false;
              }else {
                  et_hr_adhar_img.setError(null);

              }
              if(resume.isEmpty())
              {
                  et_hr_resume.setError("Upload Resume");
                  valid = false;
              }else {
                  et_hr_resume.setError(null);

              }
              return valid;
          }

      });

}
   public void hrSignupData()
   {
      et_hr_sign_name = findViewById(R.id.et_hr_sign_name);
      et_hr_sign_pass = findViewById(R.id.et_hr_sign_pass);
      et_hr_Email = findViewById(R.id.et_hr_Email);
      et_hr_Phone = findViewById(R.id.et_hr_Phone);
     // et_hr_alt_phone = findViewById(R.id.et_hr_alt_phone);
     // et_hr_company_type = findViewById(R.id.et_hr_company_type);
      et_hr_qual = findViewById(R.id.et_hr_qual);
      //et_hr_Address = findViewById(R.id.et_hr_Address);
      //et_hr_Zipcode = findViewById(R.id.et_hr_Zipcode);
      //et_hr_adhar = findViewById(R.id.et_hr_adhar);
      //et_hr_linked = findViewById(R.id.et_hr_linked);
      //et_hr_fbook = findViewById(R.id.et_hr_fbook);
      et_hr_pro_pic = findViewById(R.id.et_hr_pro_pic);
      et_hr_pro_img = findViewById(R.id.et_hr_pro_img);
      et_hr_adhar_img = findViewById(R.id.et_hr_adhar_img);
      et_hr_resume = findViewById(R.id.et_hr_resume);
      bt_hr_upload_img = findViewById(R.id.bt_hr_upload_img);
      bt_select_Aadhar_file = findViewById(R.id.bt_select_Aadhar_file);
      bt_upload_Aadhar_file = findViewById(R.id.bt_upload_Aadhar_file);
       bt_select_Resume = findViewById(R.id.bt_select_Resume);
       bt_upload_resume = findViewById(R.id.bt_upload_resume);
       bt_hr_signup = findViewById(R.id.bt_hr_signup);
   }
   public void hrRegistration()
   {
      final ProgressDialog progressDialog = new ProgressDialog(HrSignup.this);
      progressDialog.setCancelable(false); // set cancelable to false
      progressDialog.setMessage("Please Wait"); // set message
      progressDialog.show(); // show progress dialog
      Api.getapiContext(getApplicationContext());
      Api.getClient().hrregistration(et_hr_sign_name.getText().toString().trim(), et_hr_sign_pass.getText().toString().trim(),
              et_hr_Email.getText().toString().trim(), et_hr_Phone.getText().toString().trim(), et_hr_qual.getText().toString().trim(),
              et_hr_pro_pic.getText().toString().trim(),et_hr_adhar_img.getText().toString().trim(),
              et_hr_resume.getText().toString().trim()).enqueue(new retrofit2.Callback<HrSignUpResponse>() {
         @Override
         public void onResponse(Call<HrSignUpResponse> call, Response<HrSignUpResponse> response) {
            // Validations.MyAlertBox(HrSignup.this, response.body().getMessage());
             Validations.MyAlertBox(HrSignup.this,response.body().getMessage());
             //Validations.MyAlertBoxIntent(HrSignup.this,"Signup successfully",Login.class);
           // Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
//            Log.d("hr id",response.body().getData().getId());
//             Log.d("hr qualification",response.body().getData().getHRqualification());
//             Log.d("hr facebook",response.body().getData().getFacebook());
           // HrData hrdata = response.body().getData();
//            id = hrdata.getId();
//            name = hrdata.getUserName();
//            password =hrdata.getPassword();
//            email=hrdata.getEmail();
//            phone= hrdata.getPhone();
//            altph = hrdata.getAltPhone();
//            companytype=hrdata.getCompanyType();
//            hrqualification =hrdata.getQualification();
//            addres = hrdata.getAddress();
//            zipcode = hrdata.getZipcode();
//            aadharno = hrdata.getAadharNo();
//            linkedIn = hrdata.getLinkedIn();
//            facebook =hrdata.getFacebook();
//            profile =hrdata.getProfile();
//            AadharImage =hrdata.getAadharImage();
//            file = hrdata.getFile();


           //  new PrefManager(getApplicationContext()).saveHrData(id,name,password,email,phone,altph,companytype,hrqualification,addres,zipcode,aadharno,linkedIn,facebook,profile,AadharImage,file);


         }

         @Override
         public void onFailure(Call<HrSignUpResponse> call, Throwable t) {
            //Toast.makeText(getApplicationContext(), "Email already existed", Toast.LENGTH_LONG).show();
             Validations.MyAlertBox(HrSignup.this, "Email already existed");

            progressDialog.dismiss();

         }
      });
   }
   @RequiresApi(api = Build.VERSION_CODES.O)
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            et_hr_pro_pic.setText(s);
            // Set the Image in ImageView for Previewing the Media
            //et_hr_pro_img.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            cursor.close();

         }else {
            //Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
         }
      } catch (Exception e) {
         Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
      }


      try {
         // When an Image is picked
         if (requestCode == 1 && resultCode == RESULT_OK && null != data) {


            // Get the Image from data
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             adharmediaPath = cursor.getString(columnIndex);

            File file1 = new File(adharmediaPath);
            String s = file1.getName();

            et_hr_adhar_img.setText(s);
            // Set the Image in ImageView for Previewing the Media
            //et_hr_pro_img.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            cursor.close();

         }else {
            //Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
         }
      } catch (Exception e) {
         Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
      }
       try {
           // When an Image is picked
           if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
//               Uri uri;
//               final String id = DocumentsContract.getDocumentId(uri);
              Uri selectedImage = data.getData();

//             String[] filePathColumn = {MediaStore.Images.Media.ORIENTATION};
//
//             Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//              assert cursor != null;
//              cursor.moveToFirst();
//
//             int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//               mediaPath = cursor.getString(columnIndex);
//               System.out.println(selectedImage+"file path::");
                 //   Uri uri;
               mediaPath = getPDFPath(selectedImage);


             //  mediaPath = getRealPathFromURI(data.getData());
                Log.d(mediaPath,"file path");
               File file1 = new File(mediaPath);
               String s = file1.getName();

               et_hr_resume.setText(s);
               // Set the Image in ImageView for Previewing the Media
               //et_hr_pro_img.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
            //cursor.close();
            //MediaStore.Images.Media.ORIENTATION

           }else {
               //Toast.makeText(this, "You haven't picked Image/Video", Toast.LENGTH_LONG).show();
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

//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }

    //Upload Profile Image
   public void uploadFile() {
      progressDialog.show();
      // Map is used to multipart the file using okhttp3.RequestBody
      File file = new File(mediaPath);
      // File file1 = new File(getRealPathFromURI(selectedFileURI));
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
            Log.d("inside response",s);
            if (serverResponse != null) {
               if (serverResponse.getStatus().equalsIgnoreCase("success")) {
                   Validations.MyAlertBox(HrSignup.this,serverResponse.getMessage());
                  //Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
               } else {
                   Validations.MyAlertBox(HrSignup.this,serverResponse.getMessage());
                 // Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
               }
            } else {
               assert serverResponse != null;
               Log.v("Response", serverResponse.toString());
            }
            progressDialog.dismiss();
         }

         @Override
         public void onFailure(Call call, Throwable t) {
             Validations.MyAlertBox(HrSignup.this,"Fail");
            //Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

         }
      });
   }

   //Upload Aadhar file
   public void uploadAadharImage() {
      progressDialog.show();
      // Map is used to multipart the file using okhttp3.RequestBody
      File file = new File(adharmediaPath);

      RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
      MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("AadharImage", file.getName(), requestBody);
      RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

      Log.d("filename",file.getName());
      Call<AadharUploadResponse> getResponse =  Api.getClient().uploadAadharFile(fileToUpload, filename);

      getResponse.enqueue(new Callback<AadharUploadResponse>() {
         @Override
         public void onResponse(Call<AadharUploadResponse> call, Response<AadharUploadResponse> response) {
            Log.d("inside response","test");
            AadharUploadResponse serverResponse = (AadharUploadResponse) response.body();
            String s = serverResponse.getStatus();
            Log.d("inside response",s);
            if (serverResponse != null) {
               if (serverResponse.getStatus().equalsIgnoreCase("success")) {
                   Validations.MyAlertBox(HrSignup.this, serverResponse.getMessage());
                 // Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
               } else {
                   Validations.MyAlertBox(HrSignup.this, serverResponse.getMessage());
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
             Validations.MyAlertBox(HrSignup.this, "Registraion failed.");
            //Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
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
                        Validations.MyAlertBox(HrSignup.this, serverResponse.getMessage());
//                        Intent intent = new Intent(HrSignup.this,Login.class);
//                        startActivity(intent);
                        // Toast.makeText(getApplicationContext(), serverResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    } else {
                        Validations.MyAlertBox(HrSignup.this, serverResponse.getMessage());
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
                Validations.MyAlertBox(HrSignup.this, "resume upload failed.");
               // Toast.makeText(getApplicationContext(), "fail",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });


    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
