package com.project.appcv.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.View.JobDetailActivity;

import java.util.List;

public class CvAdapter extends RecyclerView.Adapter<CvAdapter.MyViewHolder> {
    List<Cv> cvList;
    Context context;
    public CvAdapter(List<Cv> cvList, Context context) {
        this.cvList = cvList;
        this.context = context;
    }

    public CvAdapter(List<Cv> cvList) {
        this.cvList = cvList;
    }

    public void setJobList(List<Cv> cvList) {
        this.cvList = cvList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cv_candidate,null);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CvAdapter.MyViewHolder holder, int position) {
        Cv cv =cvList.get(position);
        holder.nameCandidate.setText(cv.getCandidates().getCandidate().getFname());
        holder.textViewPosition.setText(cv.getPosition());
        Glide.with(context)
                .load(cv.getCandidates().getCandidate().getImage())
                .into(holder.imageAvatar);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.textViewID.setText(cv.getId()+"");
        holder.textViewBirthday.setText(cv.getCandidates().getFormattedDate());
        holder.textViewPhone.setText(cv.getCandidates().getCandidate().getPhone());
        holder.textViewGender.setText(cv.getCandidates().getGender());
        holder.textViewEmail.setText(cv.getCandidates().getCandidate().getEmail());
        holder.textViewWebsite.setText(cv.getCandidates().getCandidate().getWebsite());
        holder.textViewAddress.setText(cv.getAddress().getCity()+": "+cv.getAddress().getAddress());
        holder.textViewSkill.setText(cv.getSkill());
        holder.textViewPrize.setText(cv.getPrize());
        holder.textViewCertificate.setText(cv.getCertificate());
        holder.textViewGoal.setText(cv.getGoals());
        holder.textViewStudy.setText(cv.getStudy());
        holder.textViewWork.setText(cv.getWork());

    }

    @Override
    public int getItemCount() {
        return cvList==null?0:cvList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageAvatar;
        public TextView nameCandidate,textViewPosition,textViewID,
                textViewBirthday,textViewPhone,textViewGender,textViewEmail,textViewWebsite,textViewAddress,
                textViewSkill,textViewPrize,textViewCertificate,textViewGoal,textViewStudy,textViewWork;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAvatar=itemView.findViewById(R.id.imageCvApplyAvatar);
            nameCandidate=itemView.findViewById(R.id.tvCvApplyName);
            textViewPosition=itemView.findViewById(R.id.tvCvApplyPosition);
            textViewID=itemView.findViewById(R.id.tvCvApplyID);
            textViewBirthday=itemView.findViewById(R.id.tvCvApplyBirthday);
            textViewPhone=itemView.findViewById(R.id.tvCvApplyPhone);
            textViewGender=itemView.findViewById(R.id.tvCvApplyGender);
            textViewEmail=itemView.findViewById(R.id.tvCvApplyEmail);
            textViewWebsite=itemView.findViewById(R.id.tvCvApplyWebsite);
            textViewAddress=itemView.findViewById(R.id.tvCvApplyAddress);

            textViewSkill=itemView.findViewById(R.id.tvCvApplySkill);
            textViewPrize=itemView.findViewById(R.id.tvCvApplyPrize);
            textViewCertificate=itemView.findViewById(R.id.tvCvApplyCertificate);
            textViewGoal=itemView.findViewById(R.id.tvCvApplyGoals);
            textViewStudy=itemView.findViewById(R.id.tvCvApplyStudy);
            textViewWork=itemView.findViewById(R.id.tvCvApplyExperienceWork);

        }
    }
}
