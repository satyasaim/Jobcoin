package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.JSeekerSignUpResponse;

import retrofit2.Call;
import retrofit2.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class JseekerSignUp extends AppCompatActivity {
Button bt_signup;
EditText sp_ref_code,et_signup_name,et_signup_email,et_signup_phone,et_adhar,et_signup_pasword;
ImageView im_js_signup_logo,img_jseeker_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jseeker_sign_up);
        jseekerSignupData();

        img_jseeker_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JseekerSignUp.this,Login.class);
                startActivity(intent);

            }
        });


        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(JseekerSignUp.this,JobSeekerMainScreen.class);
//                startActivity(intent);
                if(validate()){
                    registration();
                }
                if(!validate())
                {
                    onSignupFailed();
                    return;
                }
            }

            public void onSignupFailed() {

                    Validations.MyAlertBox(JseekerSignUp.this,"SignUp failed");
                    //Toast.makeText(getBaseContext(), "SignUp failed", Toast.LENGTH_LONG).show();

                    bt_signup.setEnabled(true);

            }


            public boolean validate() {
                    boolean valid = true;
                    String name = et_signup_name.getText().toString();
                    String phone = et_signup_phone.getText().toString();
                    String aadharno = et_adhar.getText().toString();

                    String email = et_signup_email.getText().toString().trim();
                    String password = et_signup_pasword.getText().toString();


                    if (name.isEmpty()) {
                        et_signup_name.setError("Enter name");
                        valid = false;
                    }else {
                        et_signup_name.setError(null);
                    }

                    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        et_signup_email.setError("Enter a valid email address");
                        valid = false;
                    } else {
                        et_signup_email.setError(null);
                    }
                    if (phone.isEmpty()||phone.length()>10) {
                            et_signup_phone.setError("Enter Phone");
                            valid = false;
                        }else {
                            et_signup_phone.setError(null);
                        }
                   if(aadharno.isEmpty()|| aadharno.length() < 12 || aadharno.length() > 12){
                        et_adhar.setError("Enter 12 digit Aadhar no");
                        valid = false;
                    }
                   else {
                       et_adhar.setError(null);
                   }

                    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                        et_signup_pasword.setError("Between 4 to 10 alphanumeric characters");
                        valid = false;
                    } else {
                        et_signup_pasword.setError(null);
                    }

                    return valid;
                }
                    });


    }


    public void jseekerSignupData()
    {
        bt_signup = findViewById(R.id.bt_signup);
        sp_ref_code = findViewById(R.id.sp_ref_code);
        et_signup_name = findViewById(R.id.et_signup_name);
        et_signup_email = findViewById(R.id.et_signup_email);
        et_signup_phone = findViewById(R.id.et_signup_phone);
        et_adhar = findViewById(R.id.et_adhar);
        et_signup_pasword = findViewById(R.id.et_signup_pasword);
        img_jseeker_Back = findViewById(R.id.img_jseeker_Back);
        //et_signup_conpasword = findViewById(R.id.et_signup_conpasword);
    }

        public void registration ()
        {
            //Log.d(TAG, "Login");
            try
            {
            final ProgressDialog progressDialog = new ProgressDialog(JseekerSignUp.this);
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show(); // show progress dialog
            //Toast.makeText(getApplicationContext(), "" + et_adhar.getText().toString().trim(), Toast.LENGTH_LONG).show();

            Api.getapiContext(getApplicationContext());

            Api.getClient().jseekerregistration(et_signup_name.getText().toString().trim(), et_signup_email.getText().toString().trim(), et_signup_phone.getText().toString().trim(), et_adhar.getText().toString().trim(), et_signup_pasword.getText().toString().trim(), sp_ref_code.getText().toString().trim()).enqueue(new retrofit2.Callback<JSeekerSignUpResponse>() {
                @Override
                public void onResponse(Call<JSeekerSignUpResponse> call, Response<JSeekerSignUpResponse> response) {

                //Log.d("adhar no:",response.body().getMessage());
                   // Validations.MyAlertBoxIntent(JseekerSignUp.this,"Signup successfully",Login.class);
                    Validations.MyAlertBox(JseekerSignUp.this,response.body().getMessage());
                   // Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_LONG).show();

                   progressDialog.dismiss();
                    //success
                    //below if condition is for after registration was succeded go back to login page.
//                    if(response.body().getStatus().equals("success"))
//                    {
//                        Intent intent = new Intent(JseekerSignUp.this,Login.class);
//                        startActivity(intent);
//                    }


                }


                @Override
                public void onFailure(Call<JSeekerSignUpResponse> call, Throwable t) {

                  // onSignupFailed();
                    Validations.MyAlertBox(JseekerSignUp.this,"Email already existed");
                   // Toast.makeText(getApplicationContext(), "Email already existed", Toast.LENGTH_LONG).show();

                    progressDialog.dismiss();
                }

            });
        }catch(Exception e)
            {
                e.printStackTrace();
            }
//            im_js_signup_logo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(JseekerSignUp.this,Login.class);
//                    startActivity(intent);
//                }
//            });
    }
    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(JseekerSignUp.this,Login.class);
//        startActivity(intent);
        //super.onBackPressed();

    }
}