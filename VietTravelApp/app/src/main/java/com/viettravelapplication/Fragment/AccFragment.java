package com.viettravelapplication.Fragment;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viettravelapplication.Activity.ChangePasswordActivity;
import com.viettravelapplication.Activity.LoginActivity;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.R;

import static android.content.Context.MODE_PRIVATE;

public class AccFragment extends Fragment {
    Button btnDangNhap, btnLogin, btnThongBao, btnThayDoiNgonNgu;
    TextView trungtamhotro, danhgia, phanhoi;
    Button btnTrangChu, btnVanPhongDaiDien, btnChangePassword;
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acc,container,false);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnTrangChu = view.findViewById(R.id.btn_trangchu);
        btnThongBao = view.findViewById(R.id.btn_nhanthongbao);
        btnThayDoiNgonNgu = view.findViewById(R.id.btn_thaydoingonngu);
        btnVanPhongDaiDien = view.findViewById(R.id.btn_vanphongdaidien);
        btnChangePassword = view.findViewById(R.id.btnChangePassword);
        trungtamhotro = view.findViewById(R.id.trungtamhotro);
        danhgia = view.findViewById(R.id.danhgia);
        phanhoi = view.findViewById(R.id.phanhoi);
        spaceChangePassword = view.findViewById(R.id.spaceChangePassword);
        navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        toolbar = getActivity().getActionBar();


        sharedPreferences = getActivity().getSharedPreferences("userProfile", MODE_PRIVATE);
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
        btnTrangChu.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                fragment = new HomeFragment();
                loadFragment(fragment, R.id.nav_home);

            }
        });
        btnThongBao.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(getActivity(), "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        btnThayDoiNgonNgu.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(getActivity(), "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        btnVanPhongDaiDien.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                fragment = new AboutUsFragment();
                loadFragment(fragment, R.id.nav_aboutus);
            }
        });
        trungtamhotro.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                fragment = new AboutUsFragment();
                loadFragment(fragment, R.id.nav_aboutus);
            }
        });
        danhgia.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(getActivity(), "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });
        phanhoi.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Toast.makeText(getActivity(), "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
            }
        });

        btnChangePassword.setOnClickListener(this::handleChangeActivityChangePassword);
        return view;
    }

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

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void handleChangeActivityChangePassword(View view) {
        startActivity(new Intent(getActivity(), ChangePasswordActivity.class)); ;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == CODE_LOGIN) {
                String returnedResult = data.getData().toString();
                if (returnedResult.equals(SUCCESS)) {
                    spaceChangePassword.setVisibility(View.VISIBLE);
                    btnLogin.setText("Đăng xuất");
                    btnLogin.setOnClickListener(this::handleLogout);
                }
            }
        }
    }

    public void handleOpenLogin(View view){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
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