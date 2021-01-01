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
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.text.DecimalFormat;

public class PromotionDetailActivity extends AppCompatActivity {
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

    ImageView imagePromotion;
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
        setContentView(R.layout.detail_promotion);
        mapping();
        init();
    }


    private void init() {
        Intent intent1 = getIntent();
        Promotion promotion = (Promotion) intent1.getSerializableExtra("promotionDetail");
        System.out.println(promotion.toString());
        id = promotion.getId();
        CategoryId = promotion.getCategoryid();
        promotionId = promotion.getPromotionid();
        name = promotion.getNametour();
        diemdi = promotion.getDiemdi();
        diemden = promotion.getDiemden();
        timedi = promotion.getTimedi();
        timeve = promotion.getTimeve();
        descriptions = promotion.getDescriptions();
        images = promotion.getImage();
        price = promotion.getPrice();

        Picasso.get().load(StringUtil.LOAD_IMAGES+images)
                .placeholder(R.drawable.loading)
                .error(R.drawable.noimageicon)
                .into(imagePromotion);
        txtvNameTour.setText(promotion.getNametour());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        txtvMaTour.setText("Mã Tour: "+decimalFormat.format(promotion.getId()));
        txtvThoiGianDi.setText(promotion.getTimedi());
        txtvThoiGianVe.setText(promotion.getTimeve());
        txtvDiemDi.setText(promotion.getDiemdi());
        txtvDiemDen.setText(promotion.getDiemden());
        DecimalFormat Dformat = new DecimalFormat("###,###,###");
        txtvGiaTour.setText("Giá: "+Dformat.format(promotion.getPrice())+" VNĐ");
        txtvMota.setText(promotion.getDescriptions());
    }

    private void mapping() {
        imagePromotion = findViewById(R.id.IMGPromotion);
        txtvNameTour = findViewById(R.id.TXTNamePromotion);
        txtvMaTour = findViewById(R.id.TXTMaPromotion);
        txtvThoiGianDi = findViewById(R.id.TXTThoiGianDi);
        txtvThoiGianVe = findViewById(R.id.TXTThoiGianDen);
        txtvDiemDi = findViewById(R.id.TXTDiemDi);
        txtvDiemDen = findViewById(R.id.TXTDiemDen);
        txtvGiaTour = findViewById(R.id.TXTGiaTour);
        txtvMota = findViewById(R.id.TXTMota);
        btnDatTour = findViewById(R.id.btnDatPromotion);
    }
}
