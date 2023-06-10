package com.project.appcv.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.CompanyAdapter;
import com.project.appcv.Adapter.MessageAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.DTO.MessageDto;
import com.project.appcv.Model.Company;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MessageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rcMessage;
    List<MessageDto> messageDtoList;
    APIService apiService;
    MessageAdapter messageAdapter;

    public MessageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
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
        View view= inflater.inflate(R.layout.fragment_message, container, false);
        rcMessage=view.findViewById(R.id.rc_message);
        rcMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        GetMessage();
        return view;
    }
    private void GetMessage(){
        String jwtToken= SharedPrefManager.getInstance(getContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getAllMessages("Bearer "+ jwtToken).enqueue(new Callback<List<MessageDto>>() {
            @Override
            public void onResponse(Call<List<MessageDto>> call, Response<List<MessageDto>> response) {
                if (response.isSuccessful()) {
                    messageDtoList = response.body();
                    messageAdapter = new MessageAdapter(messageDtoList, MessageFragment.this);
                    rcMessage.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
                    rcMessage.setLayoutManager(layoutManager);
                    rcMessage.addItemDecoration(new ItemSpacingDecoration(50));
                    rcMessage.setAdapter(messageAdapter);
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MessageDto>> call, Throwable t) {
                Log.d("logg",t.getMessage());
            }
        });
    }
}