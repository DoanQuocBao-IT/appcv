package com.project.appcv.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.project.appcv.MainActivity;
import com.project.appcv.R;
import com.project.appcv.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    Button btnLogin,btnSignup,btnNoLogin;
    ViewFlipper viewFlipperWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        viewFlipperWelcome=findViewById(R.id.viewFlipperMain);
        ActionViewFlipperWelcome();
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);
        btnNoLogin=findViewById(R.id.btnNoLogin);
        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void ActionViewFlipperWelcome(){
        List<String> arrayListFlipper=new ArrayList<>();
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.wNH9T6Q2rfIG5K9CAPztrQHaE8?w=255&h=180&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.8ym_U2DMyLhNVJt6438ufQHaE8?w=261&h=180&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.aArltRqdWqrf0ZUMcCefyQHaE8?w=278&h=186&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.sSHMiEa0UtdBOqhgmLn7uQHaFX?w=242&h=180&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.pCk_rmwdsMs1x4dPMTGaXAHaFj?w=234&h=180&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.MxGCQzM0KDtE-nplvupigwHaE6?w=255&h=180&c=7&r=0&o=5&pid=1.7");
        arrayListFlipper.add("https://th.bing.com/th/id/OIP.F3vDzN5kPPYdYfvgMoUlJgHaE7?w=266&h=180&c=7&r=0&o=5&pid=1.7");
        for (int i=0;i<arrayListFlipper.size();i++){
            ImageView imageView=new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayListFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperWelcome.addView(imageView);
        }
        viewFlipperWelcome.setFlipInterval(3000);
        viewFlipperWelcome.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipperWelcome.setInAnimation(slide_in);
        viewFlipperWelcome.setOutAnimation(slide_out);
    }
}