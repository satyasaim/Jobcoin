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

import com.media3.jobcoin.Pojo.PaymentDataResponse;
import com.media3.jobcoin.Pojo.PaymentgatewayResponse;
import com.media3.jobcoin.Pojo.PayoutResponse;
import com.media3.jobcoin.Pojo.ResumeUploadResponse;
import  com.media3.jobcoin.API.PaymentApi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

@SuppressWarnings("ALL")
public interface ApiInterFace {
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded
    //@POST("/signup")
    @POST("/app-jobseker-signup")
        // API's endpoints
    Call<JSeekerSignUpResponse> jseekerregistration(@Field("userName") String name,
                                                    @Field("email") String email,
                                                    @Field("phone") String phone,
                                                    @Field("aadhar") String adhar,
                                                    @Field("password") String password,
                                                    @Field("refCode") String refcode

    );

    //For Hr Registration

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded // annotation used in POST type requests
    //@POST("/signup")
    @POST("/app-hr-signup")
        // API's endpoints
    Call<HrSignUpResponse> hrregistration(@Field("userName") String name,
                                          @Field("password") String password,
                                          @Field("email") String email,
                                          @Field("phone") String phone,
                                          @Field("HRQualification") String hrqual,
                                          @Field("profile") String profile,
                                          @Field("AadharImage") String AadharImage,
                                          @Field("file") String file

    );

    //app-hr-update/386
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded // annotation used in POST type requests

    @POST("/app-hr-update/{id}")
    // API's endpoints
    //@Field("password") String password,
    Call<HrAccountResponse> hrAccountUpdate(@Path("id") String id,
                                            @Field("userName") String name,
                                            @Field("email") String email,
                                            @Field("phone") String phone,
                                            @Field("altPhone") String altph,
                                            @Field("companyType") String comtype,
                                            @Field("address") String adres,
                                            @Field("zipcode") String zipcode,
                                            @Field("HRQualification") String hrqual,
                                            @Field("aadharNo") String aadhar,
                                            @Field("facebook") String facebook,
                                            @Field("linkedIn") String linkedIn,
                                            @Field("file") String file


    );



    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded

    @POST("/app-jobseker-login")
    Call<LoginResponse> signin(@Field("email") String email,
                               @Field("password") String password);


    @Multipart
    @POST("/app-profile-image-upload")
//default
    Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part file, @Part("profile") RequestBody name);


    @Multipart
    @POST("/app-profile-adhar-upload")
//default
    Call<AadharUploadResponse> uploadAadharFile(@Part MultipartBody.Part file, @Part("AadharImage") RequestBody name);




   @Multipart
   @POST("/app-profile-file-upload")
   Call<ResumeUploadResponse> uploadResume( @Part MultipartBody.Part file,@Part("file") RequestBody name);


    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded
    @POST("/app-forgot-password")
    Call<ForgotpasswordResponse> forgotpassword(@Field("email") String email);


    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded // annotation used in POST type requests

    @POST("/app-jobseker-profile-data/{id}")

    Call<JseekerAccountUpdateResponse> jseekerAccountUpdate(@Path("id") String id,
                                                            @Field("userName") String name,
                                                            @Field("email") String email,
                                                            @Field("Mobile") String phone,
                                                            @Field("fatherName") String fatherName,
                                                            @Field("motherName") String motherName,
                                                            @Field("dateOfBirth") String dateOfBirth,
                                                            @Field("PermanentAddress") String PermanentAddress,
                                                            @Field("PresentAddress") String PresentAddress,
                                                            @Field("Mandal") String Mandal,
                                                            @Field("Gender") String Gender,
                                                            @Field("Marital") String Marital,
                                                            @Field("Handicapped") String Handicapped,
                                                            @Field("qualification") String qualification,
                                                            @Field("yearOfPassing") String yearOfPassing,
                                                            @Field("Percentage") String Percentage,
                                                            @Field("Subject1") String Subject1,
                                                            @Field("Subject2") String Subject2,
                                                            @Field("Subject3") String Subject3,
                                                            @Field("certificateCourses") String certificateCourses,
                                                            @Field("totalexperience") String totalexperience,
                                                            @Field("projectTitle") String projectTitle,
                                                            @Field("ProjectDescription") String ProjectDescription,
                                                            @Field("grade") String grade,
                                                            @Field("file") String file,
                                                            @Field("Study") String study
                                               );


    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-seeker-filter/1/{hrID}")

    Call<JobSeekerListResponse>bronzejobseekersList(@Path("hrID") String hrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-seeker-filter/2/{hrID}")

    Call<JobSeekerListResponse>silverjobseekersList(@Path("hrID") String hrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-seeker-filter/3/{hrID}")

    Call<JobSeekerListResponse>goldjobseekersList(@Path("hrID") String hrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-seeker-filter/4/{hrID}")

    Call<JobSeekerListResponse>platinumjobseekersList(@Path("hrID") String hrId);


    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-hr-user-views/{usrID}")

    Call<HrListResponse>hrListForJobSeeker(@Path("usrID") String userId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("app-hr-selected-profiles/{hrID}")

    Call<JobSeekerListResponse>jobseekersList(@Path("hrID") String hrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @POST("/app-accepted-user-profile/{usrId}/{hrID}")

    Call<MoveToSelectedProfileResponse>moveToSelectedProfile(@Path("usrId") String usrId,
                                                             @Path("hrID") String hrID);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @GET("/app-seeker-payment-now/{coinId}/{usrId}")

    Call<PaymentgatewayResponse>payement(@Path("coinId") String coinId,
                                          @Path("usrId") String usrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @GET("/app-profile-download/{usrId}")
    Call<ResponseBody> getImageDetails(@Path("usrId") String usrId);

    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @GET("/app-My-Account/{usrId}")
    Call<PaymentDataResponse> paymentData(@Path("usrId") String usrId);
}
