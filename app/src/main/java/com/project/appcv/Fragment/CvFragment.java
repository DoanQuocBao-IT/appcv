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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.JobCompanyAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.Model.Cv;
import com.project.appcv.Model.Job;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.CreateCvActivity;
import com.project.appcv.View.CreateJobActivity;
import com.project.appcv.View.Edit.EditCvCandidateActivity;
import com.project.appcv.View.LoginActivity;
import com.project.appcv.View.SelectAddressActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CvFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CvFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ConstraintLayout constraintLayoutNoCv,constraintLayoutPageCv,constraintLayoutPageJob;
    TextView textViewHeader, textViewBirthday,textViewGender,textViewPhone,textViewEmail,textViewWebsite,
    textViewAddress, textViewIntroduce,textViewStudy,textViewExperienceWork,
    textViewSkill,textViewPrize, textViewCertificate,textViewName,textViewPosition,textViewID;
    ImageView imageViewAvatar,btnEditSkill, btnEditPrize, btnEditCertificate, btnEditStudy,btnEditWork, btnEditGoal,btnEditAddress,btnEditPosition;
    Button btnAddCv,btnAddJob;
    APIService apiService;
    List<Job> jobList;
    JobCompanyAdapter jobAdapter;
    RecyclerView rc_job;
    public CvFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CvFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CvFragment newInstance(String param1, String param2) {
        CvFragment fragment = new CvFragment();
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
        View view= inflater.inflate(R.layout.fragment_cv, container, false);
        textViewHeader=view.findViewById(R.id.tvCvHeader);
        textViewBirthday=view.findViewById(R.id.tvCvBirthday);
        textViewGender=view.findViewById(R.id.tvCvGender);
        textViewPhone=view.findViewById(R.id.tvCvPhone);
        textViewEmail=view.findViewById(R.id.tvCvEmail);
        textViewWebsite=view.findViewById(R.id.tvCvWebsite);
        textViewAddress=view.findViewById(R.id.tvCvAddress);
        textViewIntroduce=view.findViewById(R.id.tvCvIntroduce);
        textViewStudy=view.findViewById(R.id.tvCvStudy);
        textViewExperienceWork=view.findViewById(R.id.tvCvExperienceWork);
        textViewSkill=view.findViewById(R.id.tvCvSkill);
        textViewPrize=view.findViewById(R.id.tvCvPrize);
        textViewCertificate=view.findViewById(R.id.tvCvCertificate);
        imageViewAvatar=view.findViewById(R.id.imageCvAvatar);
        textViewName=view.findViewById(R.id.tvCvName);
        textViewPosition=view.findViewById(R.id.tvCvPosition);
        textViewID=view.findViewById(R.id.tvCvID);

        btnEditGoal=view.findViewById(R.id.btnCCVGoal);
        btnEditStudy=view.findViewById(R.id.btnCCVStudy);
        btnEditWork=view.findViewById(R.id.btnCCVWork);
        btnEditSkill=view.findViewById(R.id.btnCCvSkill);
        btnEditPrize=view.findViewById(R.id.btnCCVPrize);
        btnEditCertificate=view.findViewById(R.id.btnCCVCertificate);
        btnEditAddress=view.findViewById(R.id.btnCVEditAddress);
        btnEditPosition=view.findViewById(R.id.btnCCVPosition);

        constraintLayoutNoCv=view.findViewById(R.id.constraintNoCv);
        constraintLayoutPageCv=view.findViewById(R.id.constraintPageCv);
        constraintLayoutPageJob=view.findViewById(R.id.constraintPageJob);

        btnAddCv=view.findViewById(R.id.btnCvAddCV);
        if (SharedPrefManager.getInstance(getContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getContext()).getRole();

            if (role.equals("candidate")) {
                btnAddCv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CreateCvActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }else {
            btnAddCv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
        btnAddJob=view.findViewById(R.id.btnAddJob);
        btnAddJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), CreateJobActivity.class);
                startActivity(intent);
            }
        });
        rc_job=view.findViewById(R.id.rc_JCjob);
        rc_job.setLayoutManager(new LinearLayoutManager(getContext()));
        constraintLayoutPageJob.setVisibility(View.GONE);
        constraintLayoutNoCv.setVisibility(View.VISIBLE);
        constraintLayoutPageCv.setVisibility(View.GONE);
        if (SharedPrefManager.getInstance(getContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getContext()).getRole();

            if (role.equals("company")) {
                textViewHeader.setText("Tin tuyển dụng của công ty");
                constraintLayoutPageJob.setVisibility(View.VISIBLE);
                constraintLayoutNoCv.setVisibility(View.GONE);
                constraintLayoutPageCv.setVisibility(View.GONE);
                GetJob();
            } else if (role.equals("candidate")) {
                constraintLayoutPageJob.setVisibility(View.GONE);
                constraintLayoutNoCv.setVisibility(View.GONE);
                constraintLayoutPageCv.setVisibility(View.VISIBLE);
                GetCv();
            }
        }

        return view;
    }
    private void EditCv(){
        btnEditGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String goal=textViewIntroduce.getText().toString();
                intent.putExtra("goals", goal);
                startActivity(intent);
            }
        });
        btnEditStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String study=textViewStudy.getText().toString();
                intent.putExtra("study", study);
                startActivity(intent);
            }
        });
        btnEditWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String work=textViewExperienceWork.getText().toString();
                intent.putExtra("work", work);
                startActivity(intent);
            }
        });
        btnEditSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String skill=textViewSkill.getText().toString();
                intent.putExtra("skill", skill);
                startActivity(intent);
            }
        });
        btnEditPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String prize=textViewPrize.getText().toString();
                intent.putExtra("prize", prize);
                startActivity(intent);
            }
        });
        btnEditCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String certificate=textViewCertificate.getText().toString();
                intent.putExtra("certificate", certificate);
                startActivity(intent);
            }
        });
        btnEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), SelectAddressActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                startActivity(intent);
            }
        });
        btnEditPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                String id=textViewID.getText().toString();
                intent.putExtra("cv_id", id);
                String position=textViewPosition.getText().toString();
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }
    private void GetCv(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        Call<Cv> call=apiService.getCvUser("Bearer "+ jwtToken);
        call.enqueue(new Callback<Cv>() {
            @Override
            public void onResponse(Call<Cv> call, Response<Cv> response) {
                if (response.isSuccessful()) {
                    Cv cv = response.body();
                    // Xử lý thông tin user
                    textViewID.setText(""+cv.getId());
                    textViewName.setText(cv.getCandidates().getCandidate().getFname());
                    textViewPosition.setText(cv.getPosition());
                    textViewBirthday.setText(cv.getCandidates().getFormattedDate());
                    textViewGender.setText(cv.getCandidates().getGender());
                    textViewPhone.setText(cv.getCandidates().getCandidate().getPhone());
                    textViewEmail.setText(cv.getCandidates().getCandidate().getEmail());
                    textViewWebsite.setText(cv.getCandidates().getCandidate().getWebsite());
                    textViewAddress.setText(cv.getAddress().getCity());
                    textViewIntroduce.setText(cv.getGoals());
                    textViewStudy.setText(cv.getStudy());
                    textViewExperienceWork.setText(cv.getWork());
                    textViewSkill.setText(cv.getSkill());
                    textViewPrize.setText(cv.getPrize());
                    textViewCertificate.setText(cv.getCertificate());
                    Glide.with(getContext()).load(cv.getCandidates().getCandidate().getImage()).into(imageViewAvatar);
                    EditCv();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Cv> call, Throwable t) {
                constraintLayoutNoCv.setVisibility(View.VISIBLE);
                constraintLayoutPageCv.setVisibility(View.GONE);
            }
        });
    }
    private void GetJob(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService=RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllJobForCompany("Bearer " + jwtToken).enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d("Getjoc",response.toString());
                if (response.isSuccessful()) {
                    jobList = response.body();
                    jobAdapter = new JobCompanyAdapter(jobList, CvFragment.this);
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
}