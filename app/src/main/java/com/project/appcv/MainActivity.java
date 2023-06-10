package com.project.appcv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.appcv.Fragment.ConnectFragment;
import com.project.appcv.Fragment.CvFragment;
import com.project.appcv.Fragment.HomeFragment;
import com.project.appcv.Fragment.MessageFragment;
import com.project.appcv.Fragment.NoticeFragment;
import com.project.appcv.Fragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.navigation);
        navigationSelected();
    }
    public void navigationSelected(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        transaction.add(R.id.frame_container,homeFragment);
        transaction.commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        // load fragment
                        HomeFragment homeFragment = new HomeFragment();
                        transaction.replace(R.id.frame_container, homeFragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_cv:
                        CvFragment cvFragment = new CvFragment();
                        transaction.replace(R.id.frame_container, cvFragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_connect:
                        MessageFragment messageFragment = new MessageFragment();
                        transaction.replace(R.id.frame_container, messageFragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_notify:
                        NoticeFragment noticeFragment = new NoticeFragment();
                        transaction.replace(R.id.frame_container, noticeFragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_profile:
                        ProfileFragment profileFragment = new ProfileFragment();
                        transaction.replace(R.id.frame_container, profileFragment);
                        transaction.commit();
                        return true;
                }
                HomeFragment homepageFragment = new HomeFragment();
                transaction.replace(R.id.frame_container, homepageFragment);
                transaction.commit();
                return false;
            }
        });
    }
}