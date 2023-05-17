package com.project.appcv.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.JobCompanyActivity;
import com.project.appcv.View.JobDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobCompanyAdapter extends RecyclerView.Adapter<JobCompanyAdapter.MyViewHolder> {
    List<Job> jobList;
    Fragment context;
    private boolean tag;
    APIService apiService;
    public JobCompanyAdapter(List<Job> jobList, Fragment context) {
        this.jobList = jobList;
        this.context = context;
    }

    public JobCompanyAdapter(List<Job> jobList) {
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
    public void onBindViewHolder(@NonNull JobCompanyAdapter.MyViewHolder holder, int position) {
        Job job =jobList.get(position);
        holder.nameJob.setText(job.getPosition());
        holder.nameCompany.setText(job.getCompany().getCompany().getFname());
        Glide.with(context)
                .load(job.getCompany().getCompany().getImage())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getContext(), JobCompanyActivity.class);
                String job_id=String.valueOf(job.getId());
                intent.putExtra("job_id", job_id);
                context.startActivity(intent);
            }
        });
        holder.address.setText(job.getAddress().getCity());
        holder.experience.setText(job.getExperience());
        holder.salary.setText(job.getSalary());
        holder.countdown.setText("Còn "+job.getCountdown()+" ngày để ứng tuyẻn ");
        String jwtToken= SharedPrefManager.getInstance(context.getContext()).getJwtToken();
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.followedJob("Bearer " + jwtToken,job.getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    tag=response.body();
                    if (tag==false){
                        holder.btnTag.setBackgroundResource(R.drawable.tag);
                    }else holder.btnTag.setBackgroundResource(R.drawable.untag);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
        holder.btnTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag==false) {
                    apiService.followJob("Bearer " + jwtToken,job.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.d("khtarespone",response.toString());
                            if (response.isSuccessful()){
                                Toast.makeText(context.getContext(), "Follow thanh cong",Toast.LENGTH_SHORT).show();
                                holder.btnTag.setBackgroundResource(R.drawable.untag);
                                tag=false;
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });

                }else {
                    apiService.delfollowJob("Bearer " + jwtToken,job.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(context.getContext(), "Xoa Follow thanh cong",Toast.LENGTH_SHORT).show();
                                holder.btnTag.setBackgroundResource(R.drawable.tag);
                                tag=true;
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
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
