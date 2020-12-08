package com.viettravelapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AdapterViewFlipper;

import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    AdapterViewFlipper adapterViewFlipper;
    RecyclerView rvCategory;
    RecyclerView rvPromotion;
    RecyclerView rvTour;
    List<Tour> tourList;
    List<Category> listCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void mapping(){

    }
}