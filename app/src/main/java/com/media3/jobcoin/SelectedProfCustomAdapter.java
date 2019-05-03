package com.media3.jobcoin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class SelectedProfCustomAdapter extends RecyclerView.Adapter<SelectedProfCustomAdapter.MyViewHolder> {

    ArrayList jseekernames,experience,qualification,phoneno,gender,skill1,skill2,age;
    Context context;


    public  SelectedProfCustomAdapter(Context context,ArrayList<String> jseekernames,ArrayList<String> experience,
                                      ArrayList<String> qualification,ArrayList<String> phoneno, ArrayList<String> gender,ArrayList<String> skill1,
                                      ArrayList<String> skill2,ArrayList<String> age )   {
        this.context = context;
        this.jseekernames = jseekernames;
        this.experience = experience;
        this.qualification = qualification;
        this.phoneno = phoneno;
        this.gender = gender;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.age = age;
    Log.d("","testing in adapter");
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_selected_resumes_item, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        Log.d("","test in oncreateviwholder");
        return mvh;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {
        Log.d("","testing in adapter binding before set");
         holder.name1.setText(String.valueOf(jseekernames.get(i)));
       // holder.name1.setText("binding");
        Log.d("","testing in adapter binding");
        holder.gender.setText(gender.get(i).toString());
        holder.experience.setText(experience.get(i).toString());
        holder.mobileno.setText(phoneno.get(i).toString());
        holder.qualification.setText(qualification.get(i).toString());
        holder.skill1.setText(skill1.get(i).toString());
        holder.skill2.setText(skill2.get(i).toString());
        holder.age.setText(String.valueOf(age.get(i)));


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,  personnames.get(i).toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }



    @Override
    public int getItemCount() {
        return jseekernames.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1,gender,mobileno,qualification,experience,skill1,skill2,age;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name1 = (TextView) itemView.findViewById(R.id.tv_selected_seeker_name);
            gender = (TextView) itemView.findViewById(R.id.tv_selected_gender_type);
            mobileno = (TextView)itemView.findViewById(R.id.tv_selected_mob_num);
            qualification = (TextView) itemView.findViewById(R.id.tv_select_edudetails);
            experience = (TextView)itemView.findViewById(R.id.tv_select_exp_year);
            skill1 = (TextView)itemView.findViewById(R.id.tv_skil_name1);
            skill2 = (TextView)itemView.findViewById(R.id.tv_skil_name2);
            age = (TextView)itemView.findViewById(R.id.tv_pla_age_num);
        }
    }
}
