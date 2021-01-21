package com.viettravelapplication.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viettravelapplication.Fragment.AboutUsFragment;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.R;

public class AccActivity extends AppCompatActivity {
    Button btnDangNhap, btnLogin, btnThongBao, btnThayDoiNgonNgu;
    TextView trungtamhotro, danhgia, phanhoi;
    Button btnGioiThieu, btnVanPhongDaiDien;
    private ItemClickListener itemClickListener;

    SharedPreferences sharedPreferences;
    public final int REQUEST_CODE = 123;

    public final int CODE_LOGIN = 12;

    public final String SUCCESS = "success";
    public final String FAIL = "fail";

    BottomNavigationView navigation;
    ActionBar toolbar;

    LinearLayout spaceChangePassword;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fracment_acc);
        mapping();
        btnGioiThieu.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                fragment = new AboutUsFragment();
                loadFragment(fragment);
            }
        });
        btnThongBao.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        btnThayDoiNgonNgu.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        btnVanPhongDaiDien.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                fragment = new AboutUsFragment();
                loadFragment(fragment);
            }
        });
        trungtamhotro.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        danhgia.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        phanhoi.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        // load Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void mapping(){
        btnLogin = findViewById(R.id.btnLogin);
        btnGioiThieu = findViewById(R.id.btn_gioithieu);
        btnThongBao = findViewById(R.id.btn_nhanthongbao);
        btnThayDoiNgonNgu = findViewById(R.id.btn_thaydoingonngu);
        btnVanPhongDaiDien = findViewById(R.id.btn_vanphongdaidien);
        trungtamhotro = findViewById(R.id.trungtamhotro);
        danhgia = findViewById(R.id.danhgia);
        phanhoi = findViewById(R.id.phanhoi);
        spaceChangePassword = findViewById(R.id.spaceChangePassword);

        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", -1);
        spaceChangePassword.setVisibility(id != -1 ? View.VISIBLE : View.GONE);
        if (id == -1){
            btnLogin.setText("Đăng nhập");
            btnLogin.setOnClickListener(this::handleOpenLogin);
            spaceChangePassword.setVisibility(View.GONE);

        }else{
            btnLogin.setText("Đăng xuất");
            btnLogin.setOnClickListener(this::handleLogout);
            spaceChangePassword.setVisibility(View.VISIBLE);

        }
    }


    public void handleChangeActivityChangePassword(View view) {
        startActivity(new Intent(AccActivity.this, ChangePasswordActivity.class)); ;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == CODE_LOGIN) {
                String returnedResult = data.getData().toString();
                if (returnedResult.equals(SUCCESS)) {
                    btnLogin.setText("Đăng xuất");
                    btnLogin.setOnClickListener(this::handleLogout);
                }
            }
        }
    }

    public void handleOpenLogin(View view){
        Intent intent = new Intent(AccActivity.this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @SuppressLint("SetTextI18n")
    public void handleLogout(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        btnLogin.setText("Đăng Nhập");
        btnLogin.setOnClickListener(this::handleOpenLogin);
        spaceChangePassword.setVisibility(View.GONE);
    }

}