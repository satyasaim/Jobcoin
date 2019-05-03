package com.media3.jobcoin;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.JobSeekerListResponse;
import com.media3.jobcoin.Pojo.MoveToSelectedProfileResponse;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class BronzeProfCustomAdapter extends RecyclerView.Adapter<BronzeProfCustomAdapter.MyViewHolder> {

    ArrayList<String> jseekernames, experience, qualification, phoneno, gender, skill1, skill2, age;
    ArrayList select_icon,jseekerids,hridarrays,download_icon;
    Context context;
    View view;

   // String hrId = new PrefManager(view.getContext()).getId();

    public BronzeProfCustomAdapter(Context context, ArrayList<String> jseekernames, ArrayList<String> experience,
                                   ArrayList<String> qualification, ArrayList<String> phoneno, ArrayList<String> gender,
                                   ArrayList<String> skill1, ArrayList<String> skill2, ArrayList<String> age,ArrayList select_icon,
                                   ArrayList jseekerids,ArrayList hridarrays, ArrayList download_icon) {
        this.context = context;
        this.jseekernames = jseekernames;
        this.experience = experience;
        this.qualification = qualification;
        this.phoneno = phoneno;
        this.gender = gender;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.age = age;
        this.select_icon = select_icon;
        this.jseekerids = jseekerids;
        this.hridarrays = hridarrays;
        this.download_icon = download_icon;

    }

    @Override
    public BronzeProfCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_find_resumes_item, viewGroup, false);
        BronzeProfCustomAdapter.MyViewHolder mvh = new BronzeProfCustomAdapter.MyViewHolder(v);
        Log.d("", "test in oncreateviwholder");
        return mvh;

    }

    @Override
    public void onBindViewHolder(final BronzeProfCustomAdapter.MyViewHolder holder, final int i) {
        Log.d("", "testing in adapter binding before set");
        holder.name1.setText(String.valueOf(jseekernames.get(i)));
        // holder.name1.setText("binding");
        Log.d("", "testing in adapter binding");
        holder.gender.setText(String.valueOf(gender.get(i)));
        holder.experience.setText(String.valueOf(experience.get(i)));
        holder.mobileno.setText(String.valueOf(phoneno.get(i)));
        holder.qualification.setText(String.valueOf(qualification.get(i)));
        holder.skill1.setText(String.valueOf(skill1.get(i)));
        holder.skill2.setText(String.valueOf(skill2.get(i)));
        holder.age.setText(String.valueOf(age.get(i)));
        holder.img_addprofile1.setImageResource((Integer)select_icon.get(i));
        holder.img_download1.setImageResource((Integer)download_icon.get(i));
        final String jseekerid=(String)jseekerids.get(i);
        final String hrid=(String)hridarrays.get(i);

        holder.img_download1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (Api.getClient().getImageDetails(jseekerid)).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        //Validations.MyAlertBox(context,"");
                        //Log.d("file ", String.valueOf(response.body()));
                       // Log.d("file msg", response.message());
//                        try {
//                            String responsedata = response.body().string();
//                            System.out.println(responsedata);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        boolean FileDownloaded = DownloadFile(response.body());
                        Log.d("onResponse", "Image is downloaded and saved ? " + FileDownloaded);
                        Validations.MyAlertBox(context,"Resume downloaded");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Validations.MyAlertBox(context,"coun't download Resume");
                    }
                });
            }
        });

        holder.img_addprofile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(hridarrays),"hrid");
                (Api.getClient().moveToSelectedProfile(jseekerid,hrid)).enqueue(new Callback<MoveToSelectedProfileResponse>() {
                    @Override
                    public void onResponse(Call<MoveToSelectedProfileResponse> call, Response<MoveToSelectedProfileResponse> response) {
                        Validations.MyAlertBox(context,"Profile moved to selected Resumes");
                        delete(holder.getAdapterPosition());

                    }

                    @Override
                    public void onFailure(Call<MoveToSelectedProfileResponse> call, Throwable t) {
                        Validations.MyAlertBox(context,"can't move");

                    }
                });

            }
        });

    }

    public boolean DownloadFile(ResponseBody body) {

        try {
            //Log.d(String.valueOf(file),"downloaded file" );
            System.out.println(body);
            InputStream in = null;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            FileOutputStream out = null;
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(path.getAbsolutePath(),"resume.doc");
            try {
               // char[] filereader = new char[1024*1024];
               byte[] filereader = new byte[1024*1024];
                long filesize = 0;
                long filesizedownloaded = filesize;


                in =  body.byteStream();
               // BufferedReader reader = new BufferedReader(new InputStreamReader(in));
               // bos = new FileOutputStream(file);

                for(int read;(read =in.read(filereader))!= -1;)
                {
                    bos.write(filereader,0,read);
                    //filesizedownloaded += read;
                }
//                while (true) {
//                    int read = in.read(filereader);
//                   // char chrread = (char)read;
//                    if(read == -1)
//                    {
//                        break;
//                    }
//                    bos.write(filereader,0,read);
//
//                    filesizedownloaded += read;
//                   // System.out.println("out data "+out.toString());
//                }
                byte[] outbyte = bos.toByteArray();

                out = new FileOutputStream(file);
                out.write(outbyte);
                out.flush();
            } catch (Exception e) {
                return false;
            }



        finally{
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            return true;
        }catch (Exception e)
        {
            return false;
        }


    }


    @Override
    public int getItemCount() {
    //new PrefManager(view.getContext()).saveTotalBronzeProfiles(String.valueOf(jseekernames.size()));
       // Log.d(String.valueOf(jseekernames.size()),"size of recyclerview");
        return jseekernames.size();
    }
    public void delete(int position) { //removes the row
        jseekernames.remove(position);
        notifyItemRemoved(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1, gender, mobileno, qualification, experience, skill1, skill2, age;// init the item view's
        ImageView img_addprofile1,img_download1;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name1 = (TextView) itemView.findViewById(R.id.tv_find_seeker_name);
            gender = (TextView) itemView.findViewById(R.id.tv_find_gender_type);
            mobileno = (TextView) itemView.findViewById(R.id.tv_find_mob_num);
            qualification = (TextView) itemView.findViewById(R.id.tv_find_qualific);
            experience = (TextView) itemView.findViewById(R.id.tv_find_exp_year);
            skill1 = (TextView) itemView.findViewById(R.id.tv_find_skil1);
           skill2 = (TextView) itemView.findViewById(R.id.tv_find_skil2);
            age = (TextView) itemView.findViewById(R.id.tv_find_age_num);
            img_addprofile1 = (ImageView) itemView.findViewById(R.id.img_addprofile1);
            img_download1 = (ImageView) itemView.findViewById(R.id.img_download1);
        }
    }


//    public String convertHexToString(String hex){
//
//        StringBuilder sb = new StringBuilder();
//        StringBuilder temp = new StringBuilder();
//
//        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
//        for( int i=0; i<hex.length()-1; i+=2 ){
//
//            //grab the hex in pairs
//            String output = hex.substring(i, (i + 2));
//            //convert hex to decimal
//            int decimal = Integer.parseInt(output, 16);
//            //convert the decimal to character
//            sb.append((char)decimal);
//
//            temp.append(decimal);
//        }
//        System.out.println("Decimal : " + temp.toString());
//
//        return sb.toString();
//    }
}
