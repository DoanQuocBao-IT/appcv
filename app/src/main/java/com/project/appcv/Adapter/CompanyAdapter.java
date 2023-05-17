package com.project.appcv.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.CompanyDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.MyViewHolder> {
    List<Company> companyList;
    Fragment context;
    APIService apiService;
    private boolean tag;


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
                Intent intent = new Intent(context.getContext(), CompanyDetailActivity.class);
                String company_id=String.valueOf(company.getId());
                intent.putExtra("company_id", company_id);
                context.startActivity(intent);
            }
        });
        holder.field.setText(company.getField());
        holder.inventory_job.setText(company.getInventoryJob()+" việc làm ");
        String jwtToken= SharedPrefManager.getInstance(context.getContext()).getJwtToken();
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.followedCompany("Bearer " + jwtToken,company.getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    tag=response.body();
                    if (tag==false){
                        holder.btnFollow.setText("Theo dõi");
                    }else holder.btnFollow.setText("Hủy theo dõi");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
        holder.btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tag==false) {
                    apiService.followCompany("Bearer " + jwtToken, company.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(context.getContext(), "Follow thanh cong", Toast.LENGTH_SHORT).show();
                                holder.btnFollow.setText("Hủy theo dõi");
                                tag=false;
                                showSuccessDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }else {
                    apiService.delfollowCompany("Bearer " + jwtToken,company.getId()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(context.getContext(), "Xoa Follow thanh cong",Toast.LENGTH_SHORT).show();
                                holder.btnFollow.setText("Theo dõi");
                                tag=true;
                                showSuccessDialog();
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
        return companyList==null?0:companyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView fname;
        public TextView field;
        public TextView inventory_job;
        public Button btnFollow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image_company);
            fname=itemView.findViewById(R.id.tvNameCompany);
            field=itemView.findViewById(R.id.tvField);
            inventory_job=itemView.findViewById(R.id.tvInventory_job);
            btnFollow=itemView.findViewById(R.id.btnFollow);
        }
    }
    private void showSuccessDialog() {
        // Tạo một AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getContext());
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.success_message, null);
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
