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
import com.project.appcv.DTO.MessageDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.R;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.MessageDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {
    List<MessageDto> jobList;
    Fragment context;
    private boolean tag;
    APIService apiService;
    public MessageAdapter(List<MessageDto> jobList, Fragment context) {
        this.jobList = jobList;
        this.context = context;
    }

    public MessageAdapter(List<MessageDto> jobList) {
        this.jobList = jobList;
    }

    public void setJobList(List<MessageDto> jobList) {
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,null);
        MessageAdapter.MyViewHolder holder=new MessageAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        MessageDto message =jobList.get(position);
        String id= SharedPrefManager.getInstance(context.getContext()).getID();
        int id_user=Integer.parseInt(id);
        if (id_user!=message.getSender().getId()) {
            holder.nameContent.setText(message.getContent());
            holder.nameUser.setText(message.getSender().getFname());
            Glide.with(context)
                    .load(message.getSender().getImage())
                    .into(holder.image);
        }else {
            holder.nameContent.setText("Bạn: "+message.getContent());
            holder.nameUser.setText(message.getReceiver().getFname());
            Glide.with(context)
                    .load(message.getReceiver().getImage())
                    .into(holder.image);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getContext(), MessageDetailActivity.class);
                if (id_user!=message.getSender().getId())
                    intent.putExtra("sender",message.getSender());
                else
                    intent.putExtra("sender",message.getReceiver());
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
        public TextView nameUser,nameContent,time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_sender);
            nameUser=itemView.findViewById(R.id.tvNameUser);
            nameContent=itemView.findViewById(R.id.tvContent);
            time=itemView.findViewById(R.id.tvTime);
        }
    }
}
