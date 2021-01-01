package com.viettravelapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.sql.SQLOutput;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tour);
        mapping();
        init();
    }


    private void init() {
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
        txtvNameTour.setText("Tên của tour: "+tour.getNametour());
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
