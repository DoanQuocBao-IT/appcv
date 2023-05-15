package com.project.appcv.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.CvAdapter;
import com.project.appcv.Adapter.JobApplyAdapter;
import com.project.appcv.Adapter.JobCompanyAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.JobActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    APIService apiService;
    List<Job> jobList;
    List<Cv> cvList;
    JobCompanyAdapter jobAdapter;
    JobApplyAdapter jobApplyAdapter;
    RecyclerView rc_job,rc_applyJob;
    ConstraintLayout constraintPageJobApply,constraintPageCandidateApply;
    Button btnAllJob,btnAllCandidate;
    TextView jobHeader;
    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
        View view=  inflater.inflate(R.layout.fragment_notice, container, false);
        constraintPageJobApply=view.findViewById(R.id.constraintPageJobApply);
        constraintPageCandidateApply=view.findViewById(R.id.constraintPageCandidateApply);
        btnAllJob=view.findViewById(R.id.btnAllJob);
        jobHeader=view.findViewById(R.id.tvCvHeader);
        rc_job=view.findViewById(R.id.rc_jobApply);
        rc_job.setLayoutManager(new LinearLayoutManager(getContext()));

        rc_applyJob=view.findViewById(R.id.rc_candidateApply);
        rc_applyJob.setLayoutManager(new LinearLayoutManager(getContext()));

        btnAllCandidate=view.findViewById(R.id.btnAllCandidate);
        if (SharedPrefManager.getInstance(getContext()).getRole()!=null) {

            String role = SharedPrefManager.getInstance(getContext()).getRole();
            if (role.equals("company")) {
                constraintPageJobApply.setVisibility(View.GONE);
                constraintPageCandidateApply.setVisibility(View.VISIBLE);
                jobHeader.setText("Các công việc đang tuyển dụng");
                GetJobApply();
                btnAllCandidate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), JobActivity.class);
                        startActivity(intent);
                    }
                });

            } else if (role.equals("candidate")) {
                constraintPageCandidateApply.setVisibility(View.GONE);
                constraintPageJobApply.setVisibility(View.VISIBLE);
                GetJob();
                btnAllJob.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), JobActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
        return view;
    }
    private void GetJob(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllJobForApply("Bearer " + jwtToken).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d("Getjoc",response.toString());
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobAdapter = new JobCompanyAdapter(jobList, NoticeFragment.this);
                    rc_job.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    rc_job.setLayoutManager(layoutManager);
                    rc_job.addItemDecoration(new ItemSpacingDecoration(50));
                    rc_job.setAdapter(jobAdapter);
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
    private void GetJobApply(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllJobForCompany("Bearer " + jwtToken).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d("Getjoc",response.toString());
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobApplyAdapter = new JobApplyAdapter(jobList, NoticeFragment.this);
                    rc_applyJob.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    rc_applyJob.setLayoutManager(layoutManager);
                    rc_applyJob.addItemDecoration(new ItemSpacingDecoration(50));
                    rc_applyJob.setAdapter(jobApplyAdapter);
                    jobApplyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}