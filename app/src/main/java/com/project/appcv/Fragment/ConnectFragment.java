package com.project.appcv.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.JobAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText editTextSearchJob;
    Button btnSearchJob;
    RecyclerView rc_findJob;
    APIService apiService;
    List<Job> jobList;
    JobAdapter jobAdapter;

    public ConnectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConnectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConnectFragment newInstance(String param1, String param2) {
        ConnectFragment fragment = new ConnectFragment();
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
        View view= inflater.inflate(R.layout.fragment_connect, container, false);
        editTextSearchJob=view.findViewById(R.id.editTextSearchJob);
        btnSearchJob=view.findViewById(R.id.btnSearchJob);
        rc_findJob=view.findViewById(R.id.rc_search_job);
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getTop6JobAll().enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobAdapter = new JobAdapter(jobList, ConnectFragment.this);
                    rc_findJob.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    rc_findJob.setLayoutManager(layoutManager);
                    rc_findJob.addItemDecoration(new ItemSpacingDecoration(50));
                    rc_findJob.setAdapter(jobAdapter);
                    jobAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
        btnSearchJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobList.clear();
                jobAdapter.notifyDataSetChanged();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                apiService.findJob(editTextSearchJob.getText().toString()).enqueue(new Callback<List<Job>>() {
                    @Override
                    public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                        if (response.isSuccessful()) {
                            jobList = response.body();
                            jobAdapter = new JobAdapter(jobList, ConnectFragment.this);
                            rc_findJob.setHasFixedSize(true);
                            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                            rc_findJob.setLayoutManager(layoutManager);
                            rc_findJob.addItemDecoration(new ItemSpacingDecoration(50));
                            rc_findJob.setAdapter(jobAdapter);
                            jobAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Job>> call, Throwable t) {
                        Log.d("logg",t.getMessage());
                    }
                });
            }
        });
        return  view;
    }
}