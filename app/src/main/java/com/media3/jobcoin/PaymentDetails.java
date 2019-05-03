package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.LoginResponse;
import com.media3.jobcoin.Pojo.PaymentData;
import com.media3.jobcoin.Pojo.PaymentgatewayResponse;

import retrofit2.Call;
import retrofit2.Response;

public class PaymentDetails extends AppCompatActivity {

Button bt_confpay;
EditText et_payment_name,et_payment_email,et_payment_ph,et_payment_address,et_payment_prductid,et_payment_prodname,et_payment_prodprice,ct_payment_currentbal,et_payment_totalbal;
String seeker_id,name,mailid,phoneno,address,product_id,membershipName,amount,current_amount,total_amount,mkey,txnid,hash,
    timestamp,action,surl,furl,cancel,msg,productinfo;
    PaymentData paymentdata;


 public static final String weburl = "https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);
        paymentData();



        seeker_id = new PrefManager(getApplicationContext()).getId();
        name = new PrefManager(getApplicationContext()).gethrname();
        et_payment_name.setText(name);

        mailid = new PrefManager(getApplicationContext()).gethremil();
        et_payment_email.setText(mailid);

        phoneno = new PrefManager(getApplicationContext()).gethrmobile();
        et_payment_ph.setText(phoneno);

        address = new PrefManager(getApplicationContext()).getjseekerpreadd();
        et_payment_address.setText(address);

        product_id = new PrefManager(getApplicationContext()).getmembershipid();
        et_payment_prductid.setText(product_id);

        membershipName = new PrefManager(getApplicationContext()).getmembershipname();
        et_payment_prodname.setText(membershipName);

        amount = new PrefManager(getApplicationContext()).getmembershipprice();
        et_payment_prodprice.setText(amount);

        current_amount = new PrefManager(getApplicationContext()).getcurrentbalance();
        ct_payment_currentbal.setText(current_amount);

        total_amount = new PrefManager(getApplicationContext()).gettotalamount();
        et_payment_totalbal.setText(total_amount);

        productinfo = new PrefManager(getApplicationContext()).getproductinfo();









        bt_confpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {



                    payment();



                }else
                {
                    return;
                }
            }


                public boolean validate() {
                    boolean valid = true;

                    String name = et_payment_name.getText().toString();
                    String email = et_payment_email.getText().toString();
                    String phone = et_payment_ph.getText().toString();
                    String address = et_payment_address.getText().toString();
                    String prodid = et_payment_prductid.getText().toString();
                    String prodname = et_payment_prodname.getText().toString();
                    String prodprice = et_payment_prodprice.getText().toString();
                    String currbal = ct_payment_currentbal.getText().toString();
                    String totalbal = et_payment_totalbal.getText().toString();

                    if (name.isEmpty()) {
                        et_payment_name.setError("enter name");
                        valid = false;
                    } else {
                        et_payment_name.setError(null);
                    }

                    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        et_payment_email.setError("enter a valid email address");
                        valid = false;
                    } else {
                        et_payment_email.setError(null);
                    }

                    if (phone.isEmpty()) {
                        et_payment_ph.setError("enter phone");
                        valid = false;
                    } else {
                        et_payment_ph.setError(null);
                    }

                    if (address.isEmpty()) {
                        et_payment_address.setError("enter address");
                        valid = false;
                    } else {
                        et_payment_address.setError(null);
                    }
                    if (prodid.isEmpty()) {
                        et_payment_prductid.setError("enter product id");
                        valid = false;
                    } else {
                        et_payment_prductid.setError(null);
                    }
                    if (prodname.isEmpty() ) {
                        et_payment_prodname.setError("enter product name");
                        valid = false;
                    } else {
                        et_payment_prodname.setError(null);
                    }
                    if (prodprice.isEmpty() ) {
                        et_payment_prodprice.setError("enter product price");
                        valid = false;
                    } else {
                        et_payment_prodprice.setError(null);
                    }
                    if(currbal.isEmpty())
                    {
                        ct_payment_currentbal.setError("enter Current Balance");
                         valid = false;
                    } else {
                        ct_payment_currentbal.setError(null);
                    }
                    if(totalbal.isEmpty())
                    {
                        et_payment_totalbal.setError("enter total Balance");
                        valid = false;
                    } else {
                        et_payment_totalbal.setError(null);
                    }

                    return valid;
                }

        });
    }

//    public void loadwebpage(View view) {
//        WebViewFragment webViewFragment = new WebViewFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer,webViewFragment).commit();
//    }
    private void payment() {
        final ProgressDialog progressDialog = new ProgressDialog(PaymentDetails.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        Api.getapiContext(getApplicationContext());
        (Api.getClient().payement(product_id,seeker_id)).enqueue(new retrofit2.Callback<PaymentgatewayResponse>() {


            @Override
            public void onResponse(Call<PaymentgatewayResponse> call, Response<PaymentgatewayResponse> response) {
               // Validations.MyAlertBox(PaymentDetails.this,"success payment");
                Log.d("payment message status",response.body().getMessage());
                 paymentdata = response.body().getData();

                mkey = paymentdata.getMkey();
                txnid = paymentdata.getTxnid();
                hash = paymentdata.getHash();
                amount = paymentdata.getAmount();
                current_amount = paymentdata.getCurrentAmount();
                total_amount = String.valueOf(paymentdata.getTotalAmount());
                name = paymentdata.getName();
                productinfo = paymentdata.getProductinfo();
                product_id = paymentdata.getProductId();
                mailid = paymentdata.getMailid();
                phoneno = paymentdata.getPhoneno();
                address = paymentdata.getAddress();
                membershipName = paymentdata.getMembershipName();
                timestamp = paymentdata.getTimestamp();
                action = paymentdata.getAction();
                surl = paymentdata.getSurl();
                furl = paymentdata.getFurl();
                cancel = paymentdata.getCancel();
                msg = paymentdata.getMsg();
              //  Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                new PrefManager(getApplicationContext()).SavePaymentData(seeker_id,mkey,txnid,hash,amount,current_amount,total_amount,name,
                        productinfo,product_id,mailid,phoneno,address,membershipName,timestamp,action,surl,furl,cancel,msg);
                    if(response.body().getStatus().equals("success"))
                    {
                        Intent intent = new Intent(PaymentDetails.this, WebView.class);
                        startActivity(intent);
                    }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PaymentgatewayResponse> call, Throwable t) {
                Validations.MyAlertBox(PaymentDetails.this,"fail payment");
                progressDialog.dismiss();
            }
        });
    }

    public void paymentData()
    {
        bt_confpay = findViewById(R.id.bt_confpay);
        et_payment_name = findViewById(R.id.et_payment_name);
        et_payment_email = findViewById(R.id.et_payment_email);
        et_payment_ph = findViewById(R.id.et_payment_ph);
        et_payment_address = findViewById(R.id.et_payment_address);
        et_payment_prductid = findViewById(R.id.et_payment_prductid);
        et_payment_prodname = findViewById(R.id.et_payment_prodname);
        et_payment_prodprice = findViewById(R.id.et_payment_prodprice);
        ct_payment_currentbal = findViewById(R.id.ct_payment_currentbal);
        et_payment_totalbal = findViewById(R.id.et_payment_totalbal);
    }
}
