package com.viettravelapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viettravelapplication.Fragment.AboutUsFragment;
import com.viettravelapplication.Fragment.AccFragment;
import com.viettravelapplication.Fragment.HomeFragment;
import com.viettravelapplication.Fragment.PromotionFragment;
import com.viettravelapplication.Fragment.TipFragment;
import com.viettravelapplication.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigation;
    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }
    private void init() {
        toolbar = getSupportActionBar();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        toolbar.setTitle("Trang chủ");
        loadFragment(new HomeFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    toolbar.setTitle("Trang chủ");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_promotion:
                    toolbar.setTitle("Ưu đãi");
                    fragment = new PromotionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_camnang:
                    toolbar.setTitle("Cẩm nang");
                    fragment = new TipFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_account:
                    toolbar.setTitle("Tài khoản");
                    fragment = new AccFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_aboutus:
                    toolbar.setTitle("Giới Thiệu");
                    fragment = new AboutUsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };
    private void loadFragment(Fragment fragment) {
        // load Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void mapping(){
        navigation= (BottomNavigationView) findViewById(R.id.navigation);
    }
}