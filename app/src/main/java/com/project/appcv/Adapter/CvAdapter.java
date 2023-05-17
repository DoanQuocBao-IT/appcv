package com.project.appcv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Cv;
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

public class CvAdapter extends RecyclerView.Adapter<CvAdapter.MyViewHolder> {
    List<Cv> cvList;
    Context context;
    int job_id;
    boolean approved;
    APIService apiService;
    public CvAdapter(List<Cv> cvList, Context context) {
        this.cvList = cvList;
        this.context = context;
    }

    public CvAdapter(List<Cv> cvList, Context context, int job_id, boolean approved) {
        this.cvList = cvList;
        this.context = context;
        this.job_id = job_id;
        this.approved = approved;
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
        String jwtToken= SharedPrefManager.getInstance(context).getJwtToken();
        if (approved==false) {
            holder.btnApproved.setVisibility(View.VISIBLE);
            holder.btnApproved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    apiService = RetrofitClient.getRetrofit().create(APIService.class);
                    apiService.approvedCv("Bearer " + jwtToken, cv.getId(), job_id).enqueue(new Callback<List<Cv>>() {
                        @Override
                        public void onResponse(Call<List<Cv>> call, Response<List<Cv>> response) {
                            if (response.isSuccessful()) {
                                showSuccessDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Cv>> call, Throwable t) {

                        }
                    });
                }
            });
        }else holder.btnApproved.setVisibility(View.GONE);
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
        public Button btnApproved;
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
            btnApproved=itemView.findViewById(R.id.btnApproved);
        }
    }
    private void showSuccessDialog() {
        // Tạo một AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.success_message, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        // Lấy reference tới TextView và Button trong layout
        TextView tvSuccessMessage = view.findViewById(R.id.tv_success_message);
        Button btnOK = view.findViewById(R.id.btn_ok);

        // Đặt message cho TextView
        tvSuccessMessage.setText("Thao tác thành công");

        // Xử lý khi người dùng nhấn OK
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Đóng dialog
            }
        });

        dialog.show(); // Hiển thị dialog
    }
}
