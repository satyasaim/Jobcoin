package com.media3.jobcoin;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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
import com.media3.jobcoin.Pojo.LoginResponse;
import com.media3.jobcoin.Pojo.Users;

import retrofit2.Call;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    Button bt_login;
    TextView tv_acc_signup, tv_forgotpas, tv_help;
    EditText et_login_email, et_login_password;

    String id,name,password,email,phone,altph,companytype,hrqualification,addres,zipcode,aadharno,linkedIn,facebook,profile,AadharImage,file,role,membershipid,membership;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logInData();
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate())
                {
                    logIn();
                }
                if(!validate())
                {
                    onLoginFailed();
                    return;
                }
            }

            private void onLoginFailed() {
                //Validations.MyAlertBox(getBaseContext(),"Login failed");
                //Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                bt_login.setEnabled(true);
            }


            public boolean validate() {
                    boolean valid = true;

                    String email = et_login_email.getText().toString();
                    String password = et_login_password.getText().toString();

                    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        et_login_email.setError("enter a valid email address");
                        valid = false;
                    } else {
                        et_login_email.setError(null);
                    }

                    if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                        et_login_password.setError("between 4 and 10 alphanumeric characters");
                        valid = false;
                    } else {
                        et_login_password.setError(null);
                    }

                    return valid;
                }

        });


        tv_forgotpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                intent.putExtra("Email",et_login_email.getText().toString().trim());
                startActivity(intent);
                finish();
            }
        });
        tv_acc_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,SignUp.class);
//                startActivity(intent);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
                View dview = getLayoutInflater().inflate(R.layout.activity_sinup_seperator, null);
                alertDialog.setView(dview);
                AlertDialog dialog = alertDialog.create();
                dialog.show();
                Button hr_signup = dview.findViewById(R.id.bt_dia_hrsign);
                Button jseek_sign = dview.findViewById(R.id.bt_dia_js_sign);
                ImageView img_cancel = dview.findViewById(R.id.img_cancel);
                hr_signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, HrSignup.class);
                        startActivity(intent);
                        finish();
                    }
                });
                jseek_sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, JseekerSignUp.class);
                        startActivity(intent);
                        finish();
                    }
                });
                img_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, Login.class);
                        startActivity(intent);
                        finish();

                    }
                });



            }
        });


    }

    public void logInData() {
        bt_login = findViewById(R.id.bt_login);
        tv_forgotpas = findViewById(R.id.tv_forgotpas);
        tv_help = findViewById(R.id.tv_help);
        tv_acc_signup = findViewById(R.id.tv_acc_signup);
        et_login_email = findViewById(R.id.et_login_email);
        et_login_password = findViewById(R.id.et_login_password);
    }

    public void logIn() {
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please wait profile accessing...."); // set message
        progressDialog.show(); // show progress dialog

        Api.getapiContext(getApplicationContext());
        (Api.getClient().signin(et_login_email.getText().toString().trim(), et_login_password.getText().toString().trim())).enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                //Toast.makeText(getApplicationContext(), response.body().getStatus(), Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
                if (response.body().getStatus().equals("success")) {

                 Log.d("status:",response.body().getStatus());
                    Users data = response.body().getData().getUsers();

                   

                    id = data.getId();
                    name = data.getUserName();
                    password =data.getPassword();
                    email=data.getEmail();
                    phone= data.getPhone();
                    altph = data.getAltPhone();
                    companytype=data.getCompanyType();
                    hrqualification =data.getHRqualification();
                    addres = data.getAddress();
                    zipcode = data.getZipcode();
                    aadharno = data.getAadharNo();
                    linkedIn = data.getLinkedIn();
                    facebook =data.getFacebook();
                    profile =data.getProfile();
                    AadharImage =data.getAadharImage();
                    file = data.getFile();
                    role = data.getRole();
                    membershipid = data.getMembershipId();
                    membership = data.getMembership();


                    Log.d("pass:",password);
                    new PrefManager(getApplicationContext()).saveUserData(id,name,password,email,phone,altph,companytype,hrqualification,addres,zipcode,aadharno,linkedIn,facebook,profile,AadharImage,file,role,membershipid,membership);






                    if ((response.body().getData().getUsers().getRole()).equalsIgnoreCase("User")) {
                        //Validations.MyAlertBox(Login.this,"U are login as Job Seeker");
                        //Toast.makeText(getApplicationContext(), "U are login as Job Seeker", Toast.LENGTH_SHORT).show();

                        Intent jsintent = new Intent(Login.this, JobSeekerMainScreen.class);
                        startActivity(jsintent);

                    } else if ((response.body().getData().getUsers().getRole()).equalsIgnoreCase("Hr")) {
                      //  Validations.MyAlertBox(Login.this,"U are login as HR");
                       //Toast.makeText(getApplicationContext(), "U are login as HR", Toast.LENGTH_SHORT).show();
                       Intent hrintent = new Intent(Login.this, HrMainScreen.class);
                        startActivity(hrintent);
                    }else {
                       // Validations.MyAlertBox(Login.this,"Invalid credentials");
                        Validations.MyAlertBox(Login.this,"Invalid credentials");
                        //Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }
                if(response.body().getStatus().equals("fail"))
               {
                   Validations.MyAlertBox(Login.this,"Enter Valid Email and Password");
                   //Toast.makeText(getApplicationContext(), "Entered wrong password", Toast.LENGTH_SHORT).show();
               }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
           Validations.MyAlertBox(Login.this,"Invalid credentials/ request failed");
              // Toast.makeText(getApplicationContext(), "Invalid credentials/ request failed ", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
       //Intent intent = new Intent(HrSignup.this,Login.class);
       //startActivity(intent);
        //super.onBackPressed();


  }
}


