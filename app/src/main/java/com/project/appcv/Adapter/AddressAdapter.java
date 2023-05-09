package com.project.appcv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.appcv.DTO.AddressWorkDto;
import com.project.appcv.DTO.OnAddressSelectedListener;
import com.project.appcv.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder>{
    List<AddressWorkDto> addressWorkDtoList;
    Context context;
    OnAddressSelectedListener onAddressSelectedListener;

    public void setOnAddressSelectedListener(OnAddressSelectedListener onAddressSelectedListener) {
        this.onAddressSelectedListener = onAddressSelectedListener;
    }

    public AddressAdapter(List<AddressWorkDto> addressWorkDtoList, Context context) {
        this.addressWorkDtoList = addressWorkDtoList;
        this.context = context;
    }

    public AddressAdapter(List<AddressWorkDto> addressWorkDtoList) {
        this.addressWorkDtoList = addressWorkDtoList;
    }

    public void setAddressWorkDtoList(List<AddressWorkDto> addressWorkDtoList) {
        this.addressWorkDtoList = addressWorkDtoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AddressWorkDto addressWorkDto=addressWorkDtoList.get(position);
        holder.textViewCity.setText(addressWorkDto.getCity());
        holder.textViewAddress.setText(addressWorkDto.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(),"Bạn đã chọn cong ty"+addressWorkDto.getAddress(), Toast.LENGTH_SHORT).show();

                Log.d("AdapteGui",addressWorkDto.getAddress());
                onAddressSelectedListener.onAddressSelected(addressWorkDto);
                setOnAddressSelectedListener(onAddressSelectedListener);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressWorkDtoList==null?0:addressWorkDtoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewCity,textViewAddress;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity=itemView.findViewById(R.id.tvACity);
            textViewAddress=itemView.findViewById(R.id.tvAAddress);
        }
    }
}
