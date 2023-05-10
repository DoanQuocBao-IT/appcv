package com.project.appcv.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.View.CompanyDetailActivity;

import java.util.List;

public class CompanyCTAdapter extends RecyclerView.Adapter<CompanyCTAdapter.MyViewHolder> {
    List<Company> companyList;
    Context context;

    public CompanyCTAdapter(List<Company> companyList, Context context) {
        this.companyList = companyList;
        this.context = context;
    }

    public CompanyCTAdapter(List<Company> companyList) {
        this.companyList = companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_company,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyCTAdapter.MyViewHolder holder, int position) {
        Company company =companyList.get(position);
        holder.fname.setText(company.getCompany().getFname());
        Glide.with(context)
                .load(company.getCompany().getImage())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CompanyDetailActivity.class);
                String company_id=String.valueOf(company.getId());
                intent.putExtra("company_id", company_id);
                context.startActivity(intent);
            }
        });
        holder.field.setText(company.getField());
        holder.inventory_job.setText(company.getInventoryJob()+" việc làm ");
    }

    @Override
    public int getItemCount() {
        return companyList==null?0:companyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView fname;
        public TextView field;
        public TextView inventory_job;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_company);
            fname=itemView.findViewById(R.id.tvNameCompany);
            field=itemView.findViewById(R.id.tvField);
            inventory_job=itemView.findViewById(R.id.tvInventory_job);
        }
    }
}