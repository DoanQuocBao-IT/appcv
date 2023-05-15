package com.project.appcv.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.CvApplyActivity;
import com.project.appcv.View.JobDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobApplyAdapter extends RecyclerView.Adapter<JobApplyAdapter.MyViewHolder> {
    List<Job> jobList;
    Fragment context;
    APIService apiService;

    public JobApplyAdapter(List<Job> jobList, Fragment context) {
        this.jobList = jobList;
        this.context = context;
    }

    public JobApplyAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job_company,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull JobApplyAdapter.MyViewHolder holder, int position) {
        Job job =jobList.get(position);
        holder.nameJob.setText(job.getPosition());
        Glide.with(context)
                .load(job.getCompany().getCompany().getImage())
                .into(holder.image);
        holder.address.setText(job.getAddress().getCity());
        holder.experience.setText(job.getExperience());
        holder.salary.setText(job.getSalary());
        holder.countdown.setText("Còn "+job.getCountdown()+" ngày để ứng tuyẻn ");
        String jwtToken= SharedPrefManager.getInstance(context.getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.countApplyCV("Bearer " + jwtToken,job.getId()).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Log.d("Count",response.toString());
                if (response.isSuccessful()){
                    int apply=response.body();
                    holder.apply.setText(apply+" ứng viên");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getContext(), CvApplyActivity.class);
                String job_id=String.valueOf(job.getId());
                intent.putExtra("job_id", job_id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList==null?0:jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView nameJob,address,experience,salary,countdown,apply;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_ApplyCompany);
            nameJob=itemView.findViewById(R.id.tvNameApplyJob);
            address=itemView.findViewById(R.id.tvApplyAddress);
            experience=itemView.findViewById(R.id.tvApplyExperience);
            salary=itemView.findViewById(R.id.tvApplySalary);
            countdown=itemView.findViewById(R.id.tvApplyTime);
            apply=itemView.findViewById(R.id.tvCountApply);
        }
    }
}