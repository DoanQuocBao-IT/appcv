package com.project.appcv.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.ProfileUserDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.LoginActivity;
import com.project.appcv.View.WelcomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button btnLogout;
    ImageButton imageButtonAvatar;
    TextView textViewName, textViewID, textViewExperience, textViewProfession,textViewAddress;
    APIService apiService;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout=view.findViewById(R.id.btnPLogout);
        imageButtonAvatar=view.findViewById(R.id.imagePAvatar);
        textViewName=view.findViewById(R.id.tvPName);
        textViewID=view.findViewById(R.id.tvPId);
        textViewExperience=view.findViewById(R.id.tvPExperience);
        textViewProfession=view.findViewById(R.id.tvPProfession);
        textViewAddress=view.findViewById(R.id.tvPAddress);
        GetInforUser();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).clear();

                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }
    private void GetInforUser(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<UserPref> call=apiService.getUser("Bearer " + jwtToken);
        call.enqueue(new Callback<UserPref>() {
            @Override
            public void onResponse(Call<UserPref> call, Response<UserPref> response) {
                if (response.isSuccessful()) {
                    UserPref user = response.body();
                    // Xử lý thông tin user
                    textViewName.setText(user.getFname());
                    Glide.with(getContext()).load(user.getImage()).into(imageButtonAvatar);
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<UserPref> call, Throwable t) {

            }
        });
        Call<ProfileUserDto> callProfile= apiService.getProfileUser("Bearer " + jwtToken);
        callProfile.enqueue(new Callback<ProfileUserDto>() {
            @Override
            public void onResponse(Call<ProfileUserDto> call, Response<ProfileUserDto> response) {
                if (response.isSuccessful()) {
                    ProfileUserDto user = response.body();
                    // Xử lý thông tin user
                    textViewID.setText("Mã ứng viên: "+user.getId());
                    textViewExperience.setText(user.getExperience());
                    textViewProfession.setText(user.getProfession());
                    textViewAddress.setText(user.getAddress().getCity());
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileUserDto> call, Throwable t) {

            }
        });
    }
}