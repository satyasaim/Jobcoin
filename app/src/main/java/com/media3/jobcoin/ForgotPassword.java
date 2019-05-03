package com.media3.jobcoin;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.ForgotpasswordResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {
Button bt_submit;
EditText et_forgot_email;
ImageView img_Back_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        bt_submit = findViewById(R.id.bt_submit);
        et_forgot_email = findViewById(R.id.et_forgot_email);
        img_Back_arrow = findViewById(R.id.img_Back_arrow);
//        String email = getIntent().getStringExtra("Email");
//        et_forgot_email.setText(email);

        img_Back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassword.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.recoverpasswordalert);
                ImageView img_close = dialog.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                dialog.show();
                //Validations.MyAlertBox(ForgotPassword.this,et_forgot_email.getText().toString());
                dialog.dismiss();

                if(et_forgot_email.getText().toString().trim().equals(null)||et_forgot_email.getText().toString().trim().equals("")) {
                    Validations.MyAlertBox(ForgotPassword.this,"Enter email");
                   // Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_LONG).show();
                }else
                {
                    Api.getapiContext(getApplicationContext());
                    (Api.getClient().forgotpassword(et_forgot_email.getText().toString().trim())).enqueue(new Callback<ForgotpasswordResponse>() {
                        @Override
                        public void onResponse(Call<ForgotpasswordResponse> call, Response<ForgotpasswordResponse> response) {

                            Validations.MyAlertBox(ForgotPassword.this,response.body().getMessage());
                            Log.d("id",response.body().getData().getId());
//                            Intent intent = new Intent(ForgotPassword.this,Login.class);
//                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ForgotpasswordResponse> call, Throwable t) {
                           // Validations.MyAlertBox(ForgotPassword.this,"");
                            dialog.dismiss();

                        }
                    });
                }
                //Toast.makeText(getApplicationContext(),et_forgot_email.getText().toString(),Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(ForgotPassword.this,Login.class);
//                startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed() {
      Log.d("tag", "this is forgot");
//    Intent intent = new Intent(ForgotPassword.this,Login.class);
//    startActivity(intent);
       super.onBackPressed();

    }
}
