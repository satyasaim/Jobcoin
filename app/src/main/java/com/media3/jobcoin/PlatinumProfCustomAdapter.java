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

import com.media3.jobcoin.API.Api;
import com.media3.jobcoin.Pojo.MoveToSelectedProfileResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PlatinumProfCustomAdapter extends RecyclerView.Adapter<PlatinumProfCustomAdapter.MyViewHolder> {
    ArrayList jseekernames,experience,qualification,phoneno,gender,skill1,skill2,age;
    ArrayList select_icon,jseekerids,hridarrays,download_icon;
    Context context;
    View view;
public  PlatinumProfCustomAdapter(Context context, ArrayList<String> jseekernames, ArrayList<String> experience,
                            ArrayList<String> qualification, ArrayList<String> phoneno, ArrayList<String> gender,
                            ArrayList<String> skill1, ArrayList<String> skill2, ArrayList<String> age,ArrayList select_icon,
                                  ArrayList jseekerids,ArrayList hridarrays,ArrayList download_icon)
{
    this.context = context;
    this.jseekernames = jseekernames;
    this.experience = experience;
    this.qualification = qualification;
    this.phoneno = phoneno;
    this.gender = gender;
    this.skill1= skill1;
    this.skill2 = skill2;
    this.age = age;
    this.select_icon = select_icon;
    this.jseekerids = jseekerids;
    this.hridarrays = hridarrays;
    this.download_icon = download_icon;
}
    @Override
    public PlatinumProfCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_find_resumes_item, viewGroup, false);
        PlatinumProfCustomAdapter.MyViewHolder mvh = new PlatinumProfCustomAdapter.MyViewHolder(v);
        Log.d("","test in oncreateviwholder");
        return mvh;

    }

    @Override
    public void onBindViewHolder(final PlatinumProfCustomAdapter.MyViewHolder holder, final int i) {
        Log.d("","testing in adapter binding before set");
        holder.name1.setText(String.valueOf(jseekernames.get(i)));
        // holder.name1.setText("binding");
        Log.d("","testing in adapter binding");
        holder.gender.setText(String.valueOf(gender.get(i)));
        holder.experience.setText(String.valueOf(experience.get(i).toString()));
        holder.mobileno.setText(String.valueOf(phoneno.get(i).toString()));
        holder.qualification.setText(String.valueOf(qualification.get(i).toString()));
        holder.skill1.setText(String.valueOf(skill1.get(i).toString()));
        holder.skill2.setText(String.valueOf(skill2.get(i).toString()));
        holder.img_addprofile1.setImageResource((Integer)select_icon.get(i));
        holder.img_download1.setImageResource((Integer)download_icon.get(i));
        holder.age.setText(String.valueOf(age.get(i).toString()));
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
                        boolean FileDownloaded = DownloadFile(response.body());
                        Log.d("onResponse", "Image is downloaded and saved ? " + FileDownloaded);
                        Validations.MyAlertBox(context,"Resume downloading as resume.doc pls check in downloads");
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
            FileOutputStream out = null;
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = new File(path.getAbsolutePath(),"resume.docx");
            try {
                // char[] filereader = new char[1024*1024];
                byte[] filereader = new byte[1024*1024];
                long filesize = 0;
                long filesizedownloaded = filesize;


                in =  body.byteStream();
                out = new FileOutputStream(file);


                while (true) {
                    int read = in.read(filereader);
                    if(read == -1)
                    {
                        break;
                    }
                    out.write(filereader,0,read);
                    filesizedownloaded += read;
                    System.out.println("out data "+out.toString());
                }
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

    public void delete(int position) { //removes the row
        jseekernames.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public int getItemCount() {
       // new PrefManager(view.getContext()).saveTotalBronzeProfiles(String.valueOf(jseekernames.size()));
    return jseekernames.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1,gender,mobileno,qualification,experience,skill1,skill2,age;// init the item view's
        ImageView img_addprofile1,img_download1;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name1 = (TextView) itemView.findViewById(R.id.tv_find_seeker_name);
            gender = (TextView) itemView.findViewById(R.id.tv_find_gender_type);
            mobileno = (TextView)itemView.findViewById(R.id.tv_find_mob_num);
            qualification = (TextView) itemView.findViewById(R.id.tv_find_qualific);
            experience = (TextView)itemView.findViewById(R.id.tv_find_exp_year);
            skill1 = (TextView)itemView.findViewById(R.id.tv_find_skil1);
           skill2 = (TextView)itemView.findViewById(R.id.tv_find_skil2);
            age = (TextView)itemView.findViewById(R.id.tv_find_age_num);
            img_addprofile1 = (ImageView) itemView.findViewById(R.id.img_addprofile1);
            img_download1 = (ImageView) itemView.findViewById(R.id.img_download1);
        }
    }

}
