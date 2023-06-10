package com.project.appcv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.MessageDto;
import com.project.appcv.R;
import com.project.appcv.View.CompanyDetailActivity;
import com.project.appcv.View.MessageDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.MyViewHolder>  {
    List<MessageDto> jobList;
    Context context;
    private boolean tag;
    APIService apiService;
    public MessageDetailAdapter(List<MessageDto> jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    public MessageDetailAdapter(List<MessageDto> jobList) {
        this.jobList = jobList;
    }

    public void setJobList(List<MessageDto> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_message,null);
        MessageDetailAdapter.MyViewHolder holder=new MessageDetailAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageDetailAdapter.MyViewHolder holder, int position) {
        MessageDto message =jobList.get(position);
        holder.nameContent.setText(message.getContent());
        Glide.with(context)
                .load(message.getSender().getImage())
                .into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompanyDetailActivity.class);
                String user_id=String.valueOf(message.getSender().getId());
                intent.putExtra("user_id", user_id);
                context.startActivity(intent);
            }
        });
        Date date=new Date();
        if (date.getDate()==message.getCreateAt().getDate()){
            int hours = message.getCreateAt().getHours();
            if (hours >= 0 && hours < 12) {
                holder.time.setText(hours+":"+message.getCreateAt().getMinutes()+" AM");
            } else {
                holder.time.setText(hours-12+":"+message.getCreateAt().getMinutes()+" PM");
            }
        }else {
            SimpleDateFormat ft =
                    new SimpleDateFormat("dd/MM/yyyy");
            holder.time.setText(""+ft.format(message.getCreateAt()));
        }
    }

    @Override
    public int getItemCount() {
        return jobList==null?0:jobList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView nameContent,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_sender);
            nameContent=itemView.findViewById(R.id.tvContent);
            time=itemView.findViewById(R.id.tvTime);
        }
    }
}
