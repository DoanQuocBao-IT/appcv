package com.project.appcv.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.project.appcv.APIService.APIService;
import com.project.appcv.Adapter.MessageAdapter;
import com.project.appcv.Adapter.MessageDetailAdapter;
import com.project.appcv.DTO.ItemSpacingDecoration;
import com.project.appcv.DTO.MessageDto;
import com.project.appcv.DTO.UserPref;
import com.project.appcv.Fragment.MessageFragment;
import com.project.appcv.R;
import com.project.appcv.RetrofitClient;
import com.project.appcv.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageDetailActivity extends AppCompatActivity {
    RecyclerView rcMessage;
    List<MessageDto> messageDtoList;
    APIService apiService;
    MessageDetailAdapter messageAdapter;
    ImageView imageViewUser;
    TextView textViewNameUser;
    ImageButton btnSend;
    EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        imageViewUser=findViewById(R.id.image_user);
        textViewNameUser=findViewById(R.id.textViewNameUser);
        btnSend=findViewById(R.id.btnSend);
        editTextMessage=findViewById(R.id.editTextMessage);
        UserPref userPref = (UserPref) getIntent().getSerializableExtra("sender");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
                apiService= RetrofitClient.getRetrofit().create(APIService.class);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("content", editTextMessage.getText().toString());
                apiService.addMessage("Bearer "+ jwtToken,userPref.getId(),jsonObject).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Gui tin nhan thanh cong", Toast.LENGTH_SHORT).show();
                            editTextMessage.setText("");
                            GetMessage(userPref.getId());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        textViewNameUser.setText(userPref.getFname());
        Glide.with(getApplicationContext())
                .load(userPref.getImage())
                .into(imageViewUser);
        rcMessage=findViewById(R.id.rc_message_detail);
        GetMessage(userPref.getId());
    }
    private void GetMessage(int id){
        String jwtToken= SharedPrefManager.getInstance(getApplicationContext()).getJwtToken();
        apiService= RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getDetailMessage("Bearer "+ jwtToken,id).enqueue(new Callback<List<MessageDto>>() {
            @Override
            public void onResponse(Call<List<MessageDto>> call, Response<List<MessageDto>> response) {
                if (response.isSuccessful()) {
                    messageDtoList = response.body();
                    messageAdapter = new MessageDetailAdapter(messageDtoList, MessageDetailActivity.this);
                    rcMessage.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,true);
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