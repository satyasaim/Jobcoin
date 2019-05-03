package com.media3.jobcoin.ApiInterface;

import com.media3.jobcoin.Pojo.AadharUploadResponse;
import com.media3.jobcoin.Pojo.ForgotpasswordResponse;
import com.media3.jobcoin.Pojo.HrAccountResponse;
import com.media3.jobcoin.Pojo.HrListResponse;
import com.media3.jobcoin.Pojo.HrSignUpResponse;
import com.media3.jobcoin.Pojo.ImageUploadResponse;
import com.media3.jobcoin.Pojo.JSeekerSignUpResponse;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.JseekerAccountUpdateResponse;
import com.media3.jobcoin.Pojo.LoginResponse;
import com.media3.jobcoin.Pojo.MoveToSelectedProfileResponse;
import com.media3.jobcoin.Pojo.PaymentgatewayResponse;
import com.media3.jobcoin.Pojo.PayoutResponse;
import com.media3.jobcoin.Pojo.ResumeUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

@SuppressWarnings("ALL")
public interface ApiInterFace2 {



    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    //@FormUrlEncoded
    @POST("/pgidsk/PGIMerchantPayment")

    Call<PayoutResponse>payout(
//                               @Query("mkey") String mkey,
//                               @Query("txnid") String txnid,
//                               @Query("hash") String hash,
//                               @Query("amount") String amount,
//                               @Query("current_amount") String current_amount,
//                               @Query("total_amount") String total_amount,
//                               @Query("name") String name,
//                               @Query("productinfo") String productinfo,
//                               @Query("product_id") String product_id,
//                               @Query("mailid") String mailid,
//                               @Query("phoneno") String phoneno,
//                               @Query("address") String address,
//                               @Query("membershipName") String membershipName,
//                               @Query("timestamp") String timestamp,
//                               @Query("action") String action,
//                               @Query("surl") String surl,
//                               @Query("furl") String furl,
//                               @Query("cancel") String cancel,
                               @Query("msg") String msg);







}
