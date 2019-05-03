package com.media3.jobcoin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.media3.jobcoin.API.PaymentApi;
import com.media3.jobcoin.Pojo.PayoutResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Response;

public class WebView extends AppCompatActivity {

    // public android.webkit.WebView webView;
    // public static final String weburl = "https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";
    String seeker_id, mkey, txnid, hash, amount, current_amount, total_amount, name,
            productinfo, product_id, mailid, phoneno, address, membershipName, timestamp, action, surl, furl, cancel,msg,msg_str,checksum,checksum_value;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        seeker_id = new PrefManager(getApplicationContext()).getId();
        mkey = new PrefManager(getApplicationContext()).getmkey();
        txnid = new PrefManager(getApplicationContext()).gettxnid();
        hash = new PrefManager(getApplicationContext()).gethash();
        amount = new PrefManager(getApplicationContext()).getamount();
        current_amount = new PrefManager(getApplicationContext()).getcurrent_amount();
        total_amount = new PrefManager(getApplicationContext()).gettotal_amount();
        name = new PrefManager(getApplicationContext()).getname();
        productinfo = new PrefManager(getApplicationContext()).getpayproductinfo();
        product_id = new PrefManager(getApplicationContext()).getproduct_id();
        mailid = new PrefManager(getApplicationContext()).getpaymailid();
        phoneno = new PrefManager(getApplicationContext()).getpayphoneno();
        address = new PrefManager(getApplicationContext()).getpayaddress();
        membershipName = new PrefManager(getApplicationContext()).getpaymembershipName();
        timestamp = new PrefManager(getApplicationContext()).gettimestamp();
        action = new PrefManager(getApplicationContext()).getaction();
        surl = new PrefManager(getApplicationContext()).getsurl();
        furl = new PrefManager(getApplicationContext()).getfurl();
        cancel = new PrefManager(getApplicationContext()).getcancel();
       //; msg = new PrefManager(getApplicationContext()).getmsg();
       // msg = "gtKFFx|b38e969ee0bf5382c2dd|NA|5000|NA|NA|NA|INR|NA|R|ibmedpvtlt|NA|NA|F|9032137221|naveenkkkl@gmail.com|9032137221|NA|NA|NA|NA|IBMEDPVTLT|2b6a86f8d809a8e9f418|NA|5000|NA|NA|NA|INR|NA|R|ibmedpvtlt|NA|NA|F|9032137221|naveenkkkl@gmail.com|9032137221|NA|NA|NA|NA|https://jobcoin.co.in/app-My-Account/425/1|8C13C0E17CCC1B1151DAD503119E6E7792340EE9E233BD923D411F97A2DFD871";
          msg=null;

        //payemnt data start**************
        String mkey = "gtKFFx";
        String salt = "eCwWELxi";
        String txnId = "0nf7" + System.currentTimeMillis();
        String mobile = new PrefManager(getApplicationContext()).getpayphoneno();
        String total_amount = new PrefManager(getApplicationContext()).gettotal_amount();
        String udf1 = "";
        String udf2 = "";
        String udf3 = "";
        String udf4 = "";
        String udf5 = "";
        String productinfo = new PrefManager(getApplicationContext()).getpayproductinfo();
        String name = new PrefManager(getApplicationContext()).getname();
        String mailid = new PrefManager(getApplicationContext()).getpaymailid();
        String sUrl =  "https://jobcoin-app.co.in/payment-success/";
        String fUrl =  "https://jobcoin-app.co.in/payment-success/";

        String INR = "INR";
        String R = "R";

        String serverCalculatedHash = hashCal(mkey+"|"+txnId+"|"+total_amount+"|"+productinfo+"|"
                +name+"|"+mailid+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+salt);

        Toast.makeText(getApplicationContext(),serverCalculatedHash,Toast.LENGTH_LONG).show();

        msg_str = serverCalculatedHash+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+INR+"|"+"NA"+"|"+R+"|"+"ibmedpvtlt"+"|"+
                "NA"+"|"+"F"+"|"+mobile+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+"https://jobcoin-app.co.in/payment-success/"+"";

        // System.out.println(msg_str);

        Toast.makeText(getApplicationContext(),msg_str,Toast.LENGTH_LONG).show();
        //String sha256 = getSHA();
       // checksum = hash_hmac("sha256",msg_str,"wdLcDV6LHYCV");
       // checksum_value = checksum.toUpperCase();
        Toast.makeText(getApplicationContext(),checksum_value,Toast.LENGTH_LONG).show();
      // msg = msg_str+"|"+checksum_value;
        //MerchantID|CustomerID|NA|TxnAmount|NA|NA|NA|CurrencyType|NA|TypeField1|SecurityID|NA|NA|TypeField2|Txtadditionalinfo1| Txtadditionalinfo2| Txtadditionalinfo3|Txtadditionalinfo4| Txtadditionalinfo5| Txtadditionalinfo6| Txtadditionalinfo7|RU
        msg="gtKFFx|1b971a5d43c868d9bc78|NA|5000|NA|NA|NA|INR|NA|R|ibmedpvtlt|NA|NA|F|9032137221|testjobs@gmail.com|9032137221|NA|NA|NA|NA|https://jobcoin-app.co.in/payment-success/|87C4A7DA48DBCE183F355C514EC0C9C6C6BFB99C1AC469E6A9AA5391808A168D";

        System.out.println("original string: "+msg);
        try {
            jsonObject=new JSONObject(msg.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //payment data end****************
        // https://jobcoin.co.in/app-My-Account/425/1|913DF5B3B0231C9BC17FEB46669949C86A1A73D63E9BF08997A7EEAD60AA9989
        //  IBMEDPVTLT|2b6a86f8d809a8e9f418|NA|5000|NA|NA|NA|INR|NA|R|ibmedpvtlt|NA|NA|F|9032137221|naveenkkkl@gmail.com|9032137221|NA|NA|NA|NA|https://jobcoin.co.in/app-My-Account/425/1|8C13C0E17CCC1B1151DAD503119E6E7792340EE9E233BD923D411F97A2DFD871
       // System.out.println(seeker_id + " " + mkey + " " + txnid + " " + hash + " " + amount + " " + current_amount + " " + total_amount + " " + name + " " + productinfo + " " + product_id + " " + mailid + " " + phoneno + " " + address + " " + membershipName
         //       + " " + timestamp + " " + action + " " + surl + " " + furl + " " + cancel + " "  );

        //Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
//        webView = findViewById(R.id.webview);
    //  webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(weburl);


//        public void makePayment(View view){
//            PayUmoneySdkInitilizer.PaymentParam.Builder builder = new PayUmoneySdkInitilizer.PaymentParam.Builder();
//
//
//            builder.setAmount(getAmount())
//                    .setTnxId(txnId)
//                    .setPhone(phone)
//                    .setProductName(productName)
//                    .setFirstName(firstName)
//                    .setEmail(email)
//                    .setsUrl(sUrl)
//                    .setfUrl(fUrl)
//                    .setUdf1(udf1)
//                    .setUdf2(udf2)
//                    .setUdf3(udf3)
//                    .setUdf4(udf4)
//                    .setUdf5(udf5)
//                    .setIsDebug(isDebug)
//                    .setKey(key)
//                    .setMerchantId(merchantId);
//
//            PayUmoneySdkInitilizer.PaymentParam paymentParam = builder.build();
//        }

//        PaymentDataGeneration paymentdata = new PaymentDataGeneration();
//
//       msg = paymentdata.getmsg();
//       Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT);
        billdeskResponse();


        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();


//        handleIntent(getIntent());

    }

    private static String hash_hmac(String KEY, String VALUE, String SHA_TYPE ) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(KEY.getBytes("UTF-8"), SHA_TYPE);
            Mac mac = Mac.getInstance(SHA_TYPE);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(VALUE.getBytes("UTF-8"));

            byte[] hexArray = {
                    (byte)'0', (byte)'1', (byte)'2', (byte)'3',
                    (byte)'4', (byte)'5', (byte)'6', (byte)'7',
                    (byte)'8', (byte)'9', (byte)'a', (byte)'b',
                    (byte)'c', (byte)'d', (byte)'e', (byte)'f'
            };
            byte[] hexChars = new byte[rawHmac.length * 2];
            for ( int j = 0; j < rawHmac.length; j++ ) {
                int v = rawHmac[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }

            return new String(hexChars);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    public void handleIntent(Intent intent) {
//        String appLinkAction = intent.getAction();
//        Uri appLinkData = intent.getData();
//        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
//            String recipeId = appLinkData.getLastPathSegment();
//            Uri appData = Uri.parse("https://pgi.billdesk.com/pgidsk/PGIMerchantPayment").buildUpon()
//                    .appendPath(recipeId).build();
//            //showRecipe(appData);
//        }
//    }
        public void billdeskResponse () {

            //String url = "https://pgi.billdesk.com/pgidsk/PGIMerchantPayment";

            final ProgressDialog progressDialog = new ProgressDialog(WebView.this);
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show();
            PaymentApi.getapiContext(getApplicationContext());
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            (PaymentApi.getClient().payout(msg)).enqueue(new retrofit2.Callback<PayoutResponse>() {
                                                             @Override
                                                             public void onResponse(Call<PayoutResponse> call, Response<PayoutResponse> response) {
                                                                 Validations.MyAlertBox(WebView.this, "success");
                                                                 System.out.println("success notice: "+response.body().getMessage());
                                                                 progressDialog.dismiss();
                                                             }

                                                             @Override
                                                             public void onFailure(Call<PayoutResponse> call, Throwable t) {
                                                                 Validations.MyAlertBox(WebView.this, "failed");
                                                                 System.out.println("failure notice: "+t.getLocalizedMessage());
                                                                 progressDialog.dismiss();
                                                             }
                                                         }


            );

        }

    public static String hashCal(String str) {
        byte[] hashseq = str.getBytes();
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("SHA-512");
            algorithm.reset();
            algorithm.update(hashseq);
            byte messageDigest[] = algorithm.digest();
            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1) {
                    hexString.append("0");
                }
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException ignored) {
        }
        return hexString.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("Activity result: "+data.getDataString());
    }
}
