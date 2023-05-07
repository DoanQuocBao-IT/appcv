package com.project.appcv.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.Model.Company;
import com.project.appcv.R;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {
    List<Company> companyList;
    Fragment context;

    public CompanyAdapter(List<Company> companyList, Fragment context) {
        this.companyList = companyList;
        this.context = context;
    }

    public CompanyAdapter(List<Company> companyList) {
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
    public void onBindViewHolder(@NonNull CompanyAdapter.MyViewHolder holder, int position) {
        Company company =companyList.get(position);
        holder.fname.setText(company.getCompany().getFname());
        Glide.with(context)
                .load(company.getCompany().getImage())
                .into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getContext(),"Bạn đã chọn cong ty"+holder.fname.getText().toString(), Toast.LENGTH_SHORT).show();

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
