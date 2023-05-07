package com.project.appcv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.Model.Job;
import com.project.appcv.R;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {
    List<Job> jobList;
    Fragment context;
    private boolean tag;
    public JobAdapter(List<Job> jobList, Fragment context) {
        this.jobList = jobList;
        this.context = context;
    }

    public JobAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobAdapter.MyViewHolder holder, int position) {
        Job job =jobList.get(position);
        holder.nameJob.setText(job.getPosition());
        holder.nameCompany.setText(job.getCompany().getCompany().getFname());
        Glide.with(context)
                .load(job.getCompany().getCompany().getImage())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getContext(),"Bạn đã chọn cong ty"+holder.nameJob.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        holder.address.setText(job.getAddress().getCity());
        holder.experience.setText(job.getExperience());
        holder.salary.setText(job.getSalary());
        holder.countdown.setText("Còn "+job.getCountdown()+" ngày để ứng tuyẻn ");
        tag=true;
        holder.btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag) {
                    holder.btnTag.setBackgroundResource(R.drawable.untag);
                    tag=false;
                }else {
                    holder.btnTag.setBackgroundResource(R.drawable.tag);
                    tag=true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList==null?0:jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView nameJob,nameCompany,address,experience,salary,countdown;
        public ImageButton btnTag;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_jcompany);
            nameJob=itemView.findViewById(R.id.tvNameJob);
            nameCompany=itemView.findViewById(R.id.tvNameJCompany);
            address=itemView.findViewById(R.id.tvAddress);
            experience=itemView.findViewById(R.id.tvExperience);
            salary=itemView.findViewById(R.id.tvSalary);
            countdown=itemView.findViewById(R.id.tvTime);
            btnTag=itemView.findViewById(R.id.btnTag);
        }
    }
}
