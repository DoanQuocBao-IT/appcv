package com.project.appcv.Fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.Cv;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

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
    ConstraintLayout constraintLayoutNoCv,constraintLayoutPageCv;
    TextView textViewBirthday,textViewGender,textViewPhone,textViewEmail,textViewWebsite,
    textViewAddress, textViewIntroduce,textViewStudy,textViewExperienceWork,
    textViewSkill,textViewPrize, textViewCertificate,textViewName,textViewPosition;
    ImageView imageViewAvatar;
    APIService apiService;
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
        constraintLayoutNoCv=view.findViewById(R.id.constraintNoCv);
        constraintLayoutPageCv=view.findViewById(R.id.constraintPageCv);
        GetCv();
        return view;
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
                    textViewName.setText(cv.getCandidates().getCandidate().getFname());
                    textViewPosition.setText(cv.getPosition());
                    textViewBirthday.setText(cv.getCandidates().getFormattedDate());
                    textViewGender.setText(cv.getCandidates().getGender());
                    textViewPhone.setText(cv.getCandidates().getCandidate().getPhone());
                    textViewEmail.setText(cv.getCandidates().getCandidate().getEmail());
                    textViewWebsite.setText(cv.getCandidates().getCandidate().getWebsite());
                    textViewAddress.setText(cv.getAddress().getCity());
                    textViewIntroduce.setText(cv.getCandidates().getIntroduce());

                    textViewExperienceWork.setText(cv.getExperience());

                    Glide.with(getContext()).load(cv.getCandidates().getCandidate().getImage()).into(imageViewAvatar);
                } else {
                    constraintLayoutNoCv.setVisibility(View.VISIBLE);
                    constraintLayoutPageCv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Cv> call, Throwable t) {

            }
        });
    }
}