package com.media3.jobcoin.API;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.media3.jobcoin.ApiInterface.ApiInterFace;
import com.media3.jobcoin.ApiInterface.ApiInterFace2;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PaymentApi {

        private static Retrofit retrofit = null;
        private static Context context2;
        public static void getapiContext(Context context)
        {
            context2=context;

        }

        public static ApiInterFace2 getClient() {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            int cacheSize = 10 * 1024 * 1024;

            //Cache cache = new Cache(context2.getCacheDir(), cacheSize);





//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .cache(cache).addInterceptor(logging).build();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                  .addInterceptor(logging).build();

            // change your base URL
            if (retrofit==null) {
                // retrofit=new Retrofit.Builder().baseUrl("http://104.236.67.117:4400/").addConverterFactory(GsonConverterFactory.create()).build();
                retrofit=new Retrofit.Builder().baseUrl("https://pgi.billdesk.com/").addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();

            }
            //Creating object for our interface
            ApiInterFace2 api = retrofit.create(ApiInterFace2.class);

            return api; // return the APIInterface object
        }

    }
