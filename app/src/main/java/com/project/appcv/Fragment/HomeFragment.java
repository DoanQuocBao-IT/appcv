package com.project.appcv.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.CompanyAdapter;
import com.project.appcv.Adapter.JobAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.DTO.RoleDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.CompanyActivity;
import com.project.appcv.View.JobActivity;
import com.project.appcv.View.LoginActivity;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnLogin,btnSignup;
    RecyclerView rcCompany,rcJob;
    List<Company> companyList;
    List<Job> jobList;
    APIService apiService;
    CompanyAdapter companyAdapter;
    JobAdapter jobAdapter;
    ImageButton imageHAvatar,btnAllCompany,btnAllJob;
    TextView tvHHello;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        imageHAvatar=view.findViewById(R.id.imageHAvatar);
        tvHHello=view.findViewById(R.id.tvHHello);
        btnLogin=view.findViewById(R.id.btnHLogin);
        btnAllCompany=view.findViewById(R.id.btnAllCompany);
        btnAllCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CompanyActivity.class);
                startActivity(intent);
            }
        });
        btnAllJob=view.findViewById(R.id.btnAllJob);
        btnAllJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), JobActivity.class);
                startActivity(intent);
            }
        });
        // Kiểm tra nếu người dùng đã đăng nhập
        if (SharedPrefManager.getInstance(getContext()).isLoggedIn()) {
            btnLogin.setVisibility(View.GONE);
            imageHAvatar.setVisibility(View.VISIBLE);
            tvHHello.setVisibility(View.VISIBLE);
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            imageHAvatar.setVisibility(View.GONE);
            tvHHello.setVisibility(View.GONE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        rcCompany = view.findViewById(R.id.rc_company);
        rcCompany.setLayoutManager(new LinearLayoutManager(getContext()));
        GetInforUser();
        GetCompany();
        rcJob=view.findViewById(R.id.rc_job);
        rcJob.setLayoutManager(new LinearLayoutManager(getContext()));
        GetJob();
        return view;
    }
    private void GetCompany(){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getTop6CompanyAll().enqueue(new Callback<List<Company>>() {
            @Override
            public void onResponse(Call<List<Company>> call, Response<List<Company>> response) {
                if (response.isSuccessful()) {
                    companyList = response.body();
                    companyAdapter = new CompanyAdapter(companyList, HomeFragment.this);
                    rcCompany.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                    rcCompany.setLayoutManager(gridLayoutManager);
                    rcCompany.addItemDecoration(new ItemSpacingDecoration(50));
                    rcCompany.setAdapter(companyAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Company>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetJob(){
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getTop6JobAll().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobAdapter = new JobAdapter(jobList, HomeFragment.this);
                    rcJob.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    rcJob.setLayoutManager(layoutManager);
                    rcJob.addItemDecoration(new ItemSpacingDecoration(50));
                    rcJob.setAdapter(jobAdapter);
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetInforUser(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<UserPref> call=apiService.getUserCandidate("Bearer " + jwtToken);
        call.enqueue(new Callback<UserPref>() {
            @Override
            public void onResponse(Call<UserPref> call, Response<UserPref> response) {
                if (response.isSuccessful()) {
                    UserPref user = response.body();
                    Set<RoleDto> roles = user.getRoles(); // user là đối tượng chứa Set<Role>
                    if (!roles.isEmpty()) {
                        RoleDto firstRole = roles.iterator().next();
                        String roleName = firstRole.getName();
                        SharedPrefManager.getInstance(getContext()).saveRole(roleName);

                    }
                    // Xử lý thông tin user
                    tvHHello.setText("Chào, "+user.getFname());
                    Glide.with(getContext()).load(user.getImage()).into(imageHAvatar);
                } else {
                    // Xử lý khi API trả về lỗi

                }
            }

            @Override
            public void onFailure(Call<UserPref> call, Throwable t) {

            }
        });
    }
}