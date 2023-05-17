package com.project.appcv.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.appcv.APIService.APIService;
import com.project.appcv.DTO.CompanyDto;
import com.project.appcv.DTO.ProfileUserDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Model.Company;
import com.project.appcv.Model.ProfileCandidate;
import com.project.appcv.Model.ProfileUser;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;
import com.project.appcv.View.AddressActivity;
import com.project.appcv.View.CompanyFollowActivity;
import com.project.appcv.View.Edit.EditBirthdayActivity;
import com.project.appcv.View.Edit.EditCvCandidateActivity;
import com.project.appcv.View.Edit.EditImageActivity;
import com.project.appcv.View.Edit.EditPasswordActivity;
import com.project.appcv.View.EditJob.EditExperienceActivity;
import com.project.appcv.View.EditJob.EditProfileCompanyActivity;
import com.project.appcv.View.JobActivity;
import com.project.appcv.View.JobFollowActivity;
import com.project.appcv.View.JobPassedActivity;
import com.project.appcv.View.LoginActivity;
import com.project.appcv.View.ProfileUserActivity;
import com.project.appcv.View.SelectAddressActivity;
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

    Button btnLogout,btnPAddress,btnPProfile, btnPCv, btnEditExperience, btnEditProfession, btnEditAddress,
    btnJobApply,btnJobForCandidate,btnJobPassed,btnJobFollow,btnCompanyFollow,btnPCamera,btnChangePassword;
    ImageButton imageButtonAvatar;
    TextView textViewName, textViewID, textViewExperience, textViewProfession,textViewAddress,
    textViewExperienceHeader,textViewPositionHeader,textViewAddressHeader,textViewFindJobHeader
            ,textViewIDHeader;
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
        btnPProfile=view.findViewById(R.id.btnPProfile);
        imageButtonAvatar=view.findViewById(R.id.imagePAvatar);
        textViewName=view.findViewById(R.id.tvPName);
        textViewID=view.findViewById(R.id.tvPId);
        textViewIDHeader=view.findViewById(R.id.tvCompanytIDHeader);
        textViewExperience=view.findViewById(R.id.tvPExperience);
        textViewProfession=view.findViewById(R.id.tvPProfession);
        textViewAddress=view.findViewById(R.id.tvPAddress);
        textViewExperienceHeader=view.findViewById(R.id.tvPExperienceHeader);
        textViewPositionHeader=view.findViewById(R.id.tvPPositionHeader);
        textViewAddressHeader=view.findViewById(R.id.tvAddressHeader);
        textViewFindJobHeader=view.findViewById(R.id.findjobHeader);
        btnEditExperience=view.findViewById(R.id.btnPExperience);
        btnEditProfession=view.findViewById(R.id.btnPProfession);
        btnEditAddress=view.findViewById(R.id.btnPEditAddress);

        btnPAddress=view.findViewById(R.id.btnPAddress);
        btnPCv=view.findViewById(R.id.btnPCv);
        btnJobApply=view.findViewById(R.id.btnPJob);
        btnJobForCandidate=view.findViewById(R.id.btnPCompany);
        btnJobPassed=view.findViewById(R.id.btnJobPassed);
        btnJobFollow=view.findViewById(R.id.btnPTagJob);
        btnCompanyFollow=view.findViewById(R.id.btnPFollowCompany);
        btnPCamera=view.findViewById(R.id.btnPCamera);
        btnChangePassword=view.findViewById(R.id.btnPChangePassword);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager.getInstance(getContext()).clear();

                // Chuyển người dùng đến màn hình đăng nhập
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        if (SharedPrefManager.getInstance(getContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getContext()).getRole();
            if (role.equals("company") || role.equals("candidate")) {
                btnPAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), AddressActivity.class);
                        startActivity(intent);
                    }
                });
                imageButtonAvatar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ProfileUserActivity.class);
                        startActivity(intent);
                    }
                });
                btnPProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), ProfileUserActivity.class);
                        startActivity(intent);
                    }
                });

                btnJobPassed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (role.equals("candidate")) {
                            Intent intent = new Intent(getContext(), JobPassedActivity.class);
                            String cv_id = textViewID.getText().toString();
                            intent.putExtra("cv_id", cv_id);
                            startActivity(intent);
                        }
                    }
                });
                btnJobFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (role.equals("candidate")) {
                            Intent intent = new Intent(getContext(), JobFollowActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                btnCompanyFollow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (role.equals("candidate")) {
                            Intent intent = new Intent(getContext(), CompanyFollowActivity.class);
                            startActivity(intent);
                        }
                    }
                });
                btnPCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), EditImageActivity.class);
                        startActivity(intent);
                    }
                });
                btnChangePassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), EditPasswordActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
        btnPCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new CvFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnJobApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new NoticeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        btnJobForCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, new ConnectFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        if (SharedPrefManager.getInstance(getContext()).getRole()!=null) {
            String role = SharedPrefManager.getInstance(getContext()).getRole();
            if (role.equals("candidate"))
                GetInforUser();
            else if (role.equals("company")) {
                GetInforUserCompany();
                textViewAddressHeader.setText("Ngày thành lập ");
                textViewPositionHeader.setText("Lĩnh vực của công ty");
                textViewExperienceHeader.setText("Số lượng công việc hiện tại");
                textViewFindJobHeader.setText("Quản lí tuyển dụng");
                btnJobApply.setText("Các công việc đang tuyển dụng");
                btnJobForCandidate.setText("Công việc quanh đây");
                btnPCv.setText("Việc làm của công ty");
                btnJobPassed.setText("Tất cả ứng viên đã được tuyển dụng");
                textViewIDHeader.setText("Mã công ty: ");
            }
        }
        return  view;
    }
    private void EditInforCV(){
        String role= SharedPrefManager.getInstance(getContext()).getRole();
        btnEditExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")){
                    Intent intent=new Intent(getContext(), EditExperienceActivity.class);
                    String id=textViewID.getText().toString();
                    intent.putExtra("cv_id", id);
                    String experience=textViewExperience.getText().toString();
                    intent.putExtra("experience", experience);
                    startActivity(intent);
                } else if (role.equals("company")) {

                }

            }
        });
        btnEditProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    /*
                    Intent intent = new Intent(getContext(), EditCvCandidateActivity.class);
                    String id = textViewID.getText().toString();
                    intent.putExtra("cv_id", id);
                    String profession = textViewProfession.getText().toString();
                    intent.putExtra("profession", profession);
                    startActivity(intent);*/

                    Intent intent=new Intent(getContext(), EditCvCandidateActivity.class);
                    String id=textViewID.getText().toString();
                    intent.putExtra("cv_id", id);
                    String position=textViewProfession.getText().toString();
                    intent.putExtra("position", position);
                    startActivity(intent);
                } else if (role.equals("company")) {
                    Intent intent = new Intent(getContext(), EditProfileCompanyActivity.class);
                    String field = textViewProfession.getText().toString();
                    intent.putExtra("field", field);
                    startActivity(intent);
                }
            }
        });
        btnEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role.equals("candidate")) {
                    Intent intent = new Intent(getContext(), SelectAddressActivity.class);
                    String id = textViewID.getText().toString();
                    intent.putExtra("cv_id", id);
                    startActivity(intent);
                }else if (role.equals("company")) {
                    Intent intent = new Intent(getContext(), EditBirthdayActivity.class);
                    String foundedAt = textViewAddress.getText().toString();
                    intent.putExtra("foundedAt", foundedAt);
                    startActivity(intent);
                }
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
                    textViewID.setText(""+user.getId());
                    textViewExperience.setText(user.getExperience());
                    textViewProfession.setText(user.getPosition());
                    textViewAddress.setText(user.getAddress().getCity());
                    EditInforCV();

                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileUserDto> call, Throwable t) {

            }
        });
    }
    private void GetInforUserCompany(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        Call<ProfileUser> call=apiService.getInforUserCompany("Bearer " + jwtToken);
        call.enqueue(new Callback<ProfileUser>() {
            @Override
            public void onResponse(Call<ProfileUser> call, Response<ProfileUser> response) {
                if (response.isSuccessful()) {
                    ProfileUser user = response.body();
                    // Xử lý thông tin user
                    textViewName.setText(user.getFname());
                    Glide.with(getContext()).load(user.getImage()).into(imageButtonAvatar);
                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<ProfileUser> call, Throwable t) {

            }
        });
        Call<Company> callProfile= apiService.getInforCompany("Bearer " + jwtToken);
        callProfile.enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                if (response.isSuccessful()) {
                    Company company = response.body();
                    // Xử lý thông tin user
                    textViewID.setText(company.getId()+"");
                    btnEditExperience.setVisibility(View.GONE);
                    textViewExperience.setText(company.getInventoryJob()+"");
                    textViewProfession.setText(company.getField());
                    textViewAddress.setText(company.getFormattedDate());
                    EditInforCV();

                } else {
                    // Xử lý khi API trả về lỗi
                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {

            }
        });
    }
}