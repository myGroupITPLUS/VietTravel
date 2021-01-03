package com.viettravelapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.viettravelapplication.Activity.LoginActivity;
import com.viettravelapplication.Activity.MainActivity;
import com.viettravelapplication.Adapter.BannerAdapter;
import com.viettravelapplication.Adapter.CategoryAdapter;
import com.viettravelapplication.Adapter.PromotionAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Fragment.AboutUsFragment;
import com.viettravelapplication.Fragment.HomeFragment;
import com.viettravelapplication.Fragment.PromotionFragment;
import com.viettravelapplication.Fragment.TipFragment;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Banner;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class AccActivity extends AppCompatActivity {
    Button btnDangNhap, btnDangKy, btnThongBao, btnThayDoiNgonNgu;
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
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fracment_acc);
        mapping();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AccActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
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
                Toast.makeText(AccActivity.this, "Chưa update tính năng này", Toast.LENGTH_SHORT).show();
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
        btnDangKy = findViewById(R.id.btnDangKy);
        btnGioiThieu = findViewById(R.id.btn_gioithieu);
        btnThongBao = findViewById(R.id.btn_nhanthongbao);
        btnThayDoiNgonNgu = findViewById(R.id.btn_thaydoingonngu);
        btnVanPhongDaiDien = findViewById(R.id.btn_vanphongdaidien);
        trungtamhotro = findViewById(R.id.trungtamhotro);
        danhgia = findViewById(R.id.danhgia);
        phanhoi = findViewById(R.id.phanhoi);
    }

}