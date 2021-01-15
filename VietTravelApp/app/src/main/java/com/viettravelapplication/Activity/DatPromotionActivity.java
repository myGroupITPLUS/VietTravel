package com.viettravelapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class DatPromotionActivity extends AppCompatActivity {
    TextView txtTourname,txtTimeve,txtKhoihanh,txtPrice;
    EditText edtHoten,edtDiachi,edtEmail,edtDidong;
    Button btnXacnhantour,btnHuytour;
    ActionBar toolbar;
    SharedPreferences sharedPreferences;
    int tourid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_tour);
        mapping();
        init();
        btnXacnhantour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    datTour();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void datTour() throws JSONException {
        String hoten = edtHoten.getText().toString().trim();
        String diachi = edtDiachi.getText().toString().trim();
        String phone = edtDidong.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        if(hoten.equals("")||diachi.equals("")||phone.equals("")||email.equals("||")){
            Toast.makeText(DatPromotionActivity.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("hoten", hoten);
            jsonObject.put("diachi", diachi);
            jsonObject.put("phone", phone);
            jsonObject.put("email",email);
            jsonObject.put("tourid",tourid);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST,StringUtil.API_DAT_TOUR,jsonObject,response -> {
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DatPromotionActivity.this);
                        builder.setTitle("Thông Báo");
                        builder.setIcon(R.drawable.ic_oke);
                        builder.setMessage("Đặt tour thành công! Chúng tôi sẽ liên hệ lại với quý khách!");
                        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                startActivity(new Intent(DatPromotionActivity.this,MainActivity.class));
                            }
                        });
                        builder.create().show();
                    } else {
                        Toast.makeText(DatPromotionActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                error.printStackTrace();
                Toast.makeText(DatPromotionActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            requestQueue.add(objectRequest);
        }
    }

    private void init() {
        toolbar = getSupportActionBar();
        toolbar.setTitle("Đặt tour của bạn");
        Intent intent = getIntent();
        Promotion promotion = (Promotion) intent.getSerializableExtra("promotionDetail");
        tourid = promotion.getId();
        txtTourname.setText("Tên tour: "+promotion.getNametour());
        txtKhoihanh.setText("Nơi khởi hành: "+promotion.getDiemdi());
        txtTimeve.setText("Thời gian đi: "+promotion.getTimeve());
        DecimalFormat Dformat = new DecimalFormat("###,###,###");
        txtPrice.setText(Dformat.format(promotion.getPrice())+" VNĐ");
        initInfo();
    }

    private void initInfo() {
        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");
        edtEmail.setText(email);
    }

    private void mapping() {
        txtTourname = findViewById(R.id.txtTourname);
        txtTimeve = findViewById(R.id.txtTimeve);
        txtKhoihanh = findViewById(R.id.txtKhoihanh);
        txtPrice = findViewById(R.id.txtPrice);
        edtHoten = findViewById(R.id.edtHoten);
        edtDiachi = findViewById(R.id.edtDiachi);
        edtEmail = findViewById(R.id.edtEmail);
        edtDidong = findViewById(R.id.edtDidong);
        btnXacnhantour = findViewById(R.id.btnXacnhantour);
        btnHuytour = findViewById(R.id.btnHuytour);
    }
}