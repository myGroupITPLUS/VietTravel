package com.viettravelapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viettravelapplication.Adapter.BannerAdapter;
import com.viettravelapplication.Adapter.CategoryAdapter;
import com.viettravelapplication.Adapter.PromotionAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Fragment.home;
import com.viettravelapplication.Model.Banner;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        loadFragment(new home());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    toolbar.setTitle("Trang chủ");
                    fragment = new home();
                    loadFragment(fragment);
                    return true;
                case R.id.nav_promotion:
                    toolbar.setTitle("Ưu đãi");
                    return true;
                case R.id.nav_camnang:
                    toolbar.setTitle("Cẩm nang");
                    return true;
                case R.id.nav_account:
                    toolbar.setTitle("Tài khoản");
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