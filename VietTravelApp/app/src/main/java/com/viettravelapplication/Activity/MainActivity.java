package com.viettravelapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

//    public static int REQ_CODE = 222;

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
        loadFragment(new HomeFragment(), R.id.nav_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment, R.id.nav_home);
                    return true;
                case R.id.nav_promotion:
                    fragment = new PromotionFragment();
                    loadFragment(fragment, R.id.nav_promotion);
                    return true;
                case R.id.nav_camnang:
                    fragment = new TipFragment();
                    loadFragment(fragment, R.id.nav_camnang);
                    return true;
                case R.id.nav_account:
//                    Intent intent = new Intent(MainActivity.this, AccFragment.class);
//                    startActivityForResult(intent, REQ_CODE);
//                    return true;
                    fragment = new AccFragment();
                    loadFragment(fragment, R.id.nav_account);
                    return true;
                case R.id.nav_aboutus:
                    fragment = new AboutUsFragment();
                    loadFragment(fragment, R.id.nav_aboutus);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment, int id) {
        // load Fragment

        Menu menu = navigation.getMenu();
        MenuItem item = null;
        for (int i = 0, size = menu.size(); i < size; i++) {
            MenuItem tmpItem = menu.getItem(i);
            if (tmpItem.getItemId() == id) {
                item = tmpItem;
                break;
            }
        }
        if (item != null){
            item.setChecked(true);
            toolbar.setTitle(item.getTitle());
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void mapping() {
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQ_CODE) {
//            loadFragment(new HomeFragment());
//        }
//    }

    @Override
    public void onBackPressed() {
        loadFragment(new HomeFragment(), R.id.nav_home);
    }
}