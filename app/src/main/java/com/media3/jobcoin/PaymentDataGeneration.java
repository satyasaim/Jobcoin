package com.media3.jobcoin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PaymentDataGeneration extends AppCompatActivity {

    String msg, msg_str,checksum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        String sUrl =  "https://jobcoin.co.in/Status";
        String fUrl =  "https://jobcoin.co.in/Status";

        String INR = "INR";
        String R = "R";

        String serverCalculatedHash = hashCal(mkey+"|"+txnId+"|"+total_amount+"|"+productinfo+"|"
                +name+"|"+mailid+"|"+udf1+"|"+udf2+"|"+udf3+"|"+udf4+"|"+udf5+"|"+salt);

        Toast.makeText(getApplicationContext(),serverCalculatedHash,Toast.LENGTH_LONG).show();

        msg_str = serverCalculatedHash+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+INR+"|"+"NA"+"|"+R+"|"+"ibmedpvtlt"+"|"+
                "NA"+"|"+"F"+"|"+mobile+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+"NA"+"|"+"https://jobcoin/co.in/payment-success"+"";

       // System.out.println(msg_str);

        Toast.makeText(getApplicationContext(),msg_str,Toast.LENGTH_LONG).show();
        //String sha256 = getSHA();
       checksum = hash_hmac("sha256",msg_str,"wdLcDV6LHYCV");
        Toast.makeText(getApplicationContext(),checksum,Toast.LENGTH_LONG).show();
         //msg = msg_str+"|"+checksum;






//        $msg_str  = "IBMEDPVTLT|".$txnid."|NA|".$total_amount."|NA|NA|NA|INR|NA|R|ibmedpvtlt|NA|NA|F|".$customer_mobile."|".$customer_emial."|".$customer_mobile."|NA|NA|NA|NA|".$billsuccess."";
//        $checksum = hash_hmac('sha256',$msg_str,'wdLcDV6LHYCV', false);
//        $checksum = strtoupper($checksum);
//        $msg = $msg_str."|".$checksum;



    }

//    private String hash_hmac(String sha256, String msg_str, String wdLcDV6LHYCV) {
//        String msg ;
//        msg = sha256+msg_str+wdLcDV6LHYCV+true;
//
//        return msg;
//    }

    private static String hash_hmac(String KEY, String VALUE, String SHA_TYPE) {
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

    private String hashCal(String str) {
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

//    private String getSHA(String str) {
//
//        MessageDigest md;
//        String out = "";
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//            md.update(str.getBytes());
//            byte[] mb = md.digest();
//
//            for (int i = 0; i < mb.length; i++) {
//                byte temp = mb[i];
//                String s = Integer.toHexString(new Byte(temp));
//                while (s.length() < 2) {
//                    s = "0" + s;
//                }
//                s = s.substring(s.length() - 2);
//                out += s;
//            }
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return out;
//
//    }

    public String getmsg() {
        msg =  msg_str+"|"+checksum;
        return msg;
    }
}
