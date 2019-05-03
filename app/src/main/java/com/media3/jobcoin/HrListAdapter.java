package com.media3.jobcoin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class HrListAdapter  extends RecyclerView.Adapter<HrListAdapter.MyViewHolder>{

    ArrayList<String> hrnames,phoneno,email,experience;
    Context context;
    public  HrListAdapter(Context context,ArrayList<String> hrnames,ArrayList<String>phoneno,ArrayList<String> email, ArrayList<String> experience)
    {
        this.context = context;
        this.hrnames = hrnames;
        this.phoneno = phoneno;
        this.email = email;
        this.experience = experience;
    }

    @Override
    public HrListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_select_yr_hr_item, viewGroup, false);
        HrListAdapter.MyViewHolder mvh = new HrListAdapter.MyViewHolder(v);
        Log.d("","test in oncreateviwholder");
        return mvh;

    }

    @Override
    public void onBindViewHolder(HrListAdapter.MyViewHolder holder, final int i) {
        Log.d("","testing in adapter binding before set");
        holder.name1.setText(String.valueOf(hrnames.get(i)));
        // holder.name1.setText("binding");
        Log.d("","testing in adapter binding");
       holder.email.setText(String.valueOf(email.get(i)));
      holder.experience.setText(String.valueOf(experience.get(i)));
       holder.mobileno.setText(String.valueOf(phoneno.get(i)));
//        holder.qualification.setText(String.valueOf(qualification.get(i).toString()));
//        holder.skill1.setText(String.valueOf(skill1.get(i).toString()));
//        holder.skill2.setText(String.valueOf(skill2.get(i).toString()));
//        holder.age.setText(String.valueOf(age.get(i).toString()));
//

    }



    @Override
    public int getItemCount() {
        return hrnames.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name1,email,mobileno,qualification,experience,skill1,skill2,age;// init the item view's
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name1 = (TextView) itemView.findViewById(R.id.tv_hr_name);
            email = (TextView) itemView.findViewById(R.id.tv_emailid);
           mobileno = (TextView)itemView.findViewById(R.id.tv_mo_number);
//            qualification = (TextView) itemView.findViewById(R.id.tv_find_qualific);
          experience = (TextView)itemView.findViewById(R.id.tv_inyears);
//            skill1 = (TextView)itemView.findViewById(R.id.tv_find_skil1);
//            skill2 = (TextView)itemView.findViewById(R.id.tv_find_skil2);
//            age = (TextView)itemView.findViewById(R.id.tv_find_age_num);
        }
    }
}
