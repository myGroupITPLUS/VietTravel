package com.viettravelapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.text.DecimalFormat;

public class TourDetailActivity extends AppCompatActivity {
    int id = 0;
    int CategoryId;
    int promotionId;
    String name = "";
    String diemdi ="";
    String diemden ="";
    String timedi ="";
    String timeve ="";
    String descriptions ="";
    String images = "";
    double price = 0;
    ImageView imgTour;
    TextView txtvNameTour;
    TextView txtvMaTour;
    TextView txtvThoiGianDi;
    TextView txtvThoiGianVe;
    TextView txtvDiemDi;
    TextView txtvDiemDen;
    TextView txtvGiaTour;
    TextView txtvMota;
    Button btnDatTour;
    SharedPreferences sharedPreferences;
    ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour);
        mapping();
        init();
        btnDatTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
                int id = sharedPreferences.getInt("id", -1);
                if (id == -1){
                    startActivity(new Intent(TourDetailActivity.this,RegisterActivity.class));
                }else{
                    Intent intent1 = getIntent();
                    Tour tour = (Tour) intent1.getSerializableExtra("tourDetail");
                    Intent intent2 = new Intent(TourDetailActivity.this, DatTourActivity.class);
                    intent2.putExtra("tourDetail",(Serializable) tour);
                    startActivity(intent2);
                }
            }
        });
    }


    private void init() {
        toolbar = getSupportActionBar();
        toolbar.setTitle("Chi tiết tour");
        Intent intent1 = getIntent();
        Tour tour = (Tour) intent1.getSerializableExtra("tourDetail");
        System.out.println(tour.toString());
        id = tour.getId();
        CategoryId = tour.getCategoryid();
        promotionId = tour.getPromotionid();
        name = tour.getNametour();
        diemdi = tour.getDiemdi();
        diemden = tour.getDiemden();
        timedi = tour.getTimedi();
        timeve = tour.getTimeve();
        descriptions = tour.getDescriptions();
        images = tour.getImages();
        price = tour.getPrice();
        Picasso.get().load(StringUtil.LOAD_IMAGES+tour.getImages())
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(imgTour);
        txtvNameTour.setText(tour.getNametour());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        txtvMaTour.setText("Mã Tour: "+decimalFormat.format(tour.getId()));
        txtvThoiGianDi.setText(tour.getTimedi());
        txtvThoiGianVe.setText(tour.getTimeve());
        txtvDiemDi.setText(tour.getDiemdi());
        txtvDiemDen.setText(tour.getDiemden());
        DecimalFormat Dformat = new DecimalFormat("###,###,###");
        txtvGiaTour.setText("Giá: "+Dformat.format(tour.getPrice())+" VNĐ");
        txtvMota.setText(tour.getDescriptions());
    }

    private void mapping() {
        imgTour = findViewById(R.id.imgTour);
        txtvNameTour = findViewById(R.id.txtvNameTour);
        txtvMaTour = findViewById(R.id.txtvMaTour);
        txtvThoiGianDi = findViewById(R.id.txtvThoiGianDi);
        txtvThoiGianVe = findViewById(R.id.txtvThoiGianVe);
        txtvDiemDi = findViewById(R.id.txtvDiemDi);
        txtvDiemDen = findViewById(R.id.txtvDiemDen);
        txtvGiaTour = findViewById(R.id.txtvGiaTour);
        txtvMota = findViewById(R.id.txtvMota);
        btnDatTour = findViewById(R.id.btnDatTour);
    }
}