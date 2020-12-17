package com.viettravelapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.Adapter.CategoryAdapter;
import com.viettravelapplication.Adapter.PromotionAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    AdapterViewFlipper adapterViewFlipper;
    RecyclerView rvCategory;
    RecyclerView rvPromotion;
    RecyclerView rvTour;
    List<Tour> promotionList;
    List<Tour> tourList;
    List<Category> listCategory;
    TourAdapter tourAdapter;
    CategoryAdapter categoryAdapter;
    PromotionAdapter promotionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
        //Do du lieu ra phan Du lich khap chon
        tourList = new ArrayList<>();
        tourAdapter = new TourAdapter(MainActivity.this, R.layout.line_tour, tourList);
        getAllTour();
        rvTour.setAdapter(tourAdapter);
        rvTour.setLayoutManager( new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

//        //Do du lieu ra phan uu dai
//        promotionList = new ArrayList<>();
//        tourAdapter = new TourAdapter(MainActivity.this, R.layout.line_promotion, promotionList);
//        getAllPromotion();
//        rvPromotion.setAdapter(promotionAdapter);
//        rvPromotion.setLayoutManager( new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));

//        //do du lieu ra phan category - theo dia chi noi den
//        listCategory = new ArrayList<>();
//        categoryAdapter = new CategoryAdapter(MainActivity.this, R.layout.line_category, listCategory);
//        getAllCategory();
//        rvCategory.setAdapter(categoryAdapter);
//        rvCategory.setLayoutManager( new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
    }
    private void getAllPromotion(){
        String url = "#";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tourList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Tour tour = new Tour(jsonObject.getInt("id"), jsonObject.getInt("categoryID"), jsonObject.getInt("promotionid"), jsonObject.getString("name"), jsonObject.getString("diemdi"), jsonObject.getString("diemden"), jsonObject.getString("timedi"), jsonObject.getString("timeve"), jsonObject.getString("descriptions"), jsonObject.getString("images"), (float) jsonObject.getDouble("price"));
                                Toast.makeText(MainActivity.this, ""+tour.toString(), Toast.LENGTH_SHORT).show();
                                tourList.add(tour);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tourAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);

    }
    private void getAllTour() {
        String url = "http://54.169.31.141:8080/api/tour/";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tourList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Tour tour = new Tour(jsonObject.getInt("id"), jsonObject.getInt("categoryID"), jsonObject.getInt("promotionid"), jsonObject.getString("name"), jsonObject.getString("diemdi"), jsonObject.getString("diemden"), jsonObject.getString("timedi"), jsonObject.getString("timeve"), jsonObject.getString("descriptions"), jsonObject.getString("images"), (float) jsonObject.getDouble("price"));
                                Toast.makeText(MainActivity.this, ""+tour.toString(), Toast.LENGTH_SHORT).show();
                                tourList.add(tour);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tourAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
    }
    private void getAllCategory() {
        String url = "#";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listCategory.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Category category = new Category(jsonObject.getInt("id"), jsonObject.getString("categoryName"), jsonObject.getString("descriptions"), jsonObject.getString("images"));
                                Toast.makeText(MainActivity.this, ""+category.toString(), Toast.LENGTH_SHORT).show();
                                listCategory.add(category);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
    }
    private void init() {
        initViewFlipper();
    }

    private void initViewFlipper() {
//        List<String> imageUrlList = new ArrayList<>();
//        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/Banner-chung-595x100.png");
//        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/595x100iPhone-12-v2.png");
//        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/oppo_normal_sale_24.08.2020.png");
//        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/Cate_595x100_2.png");
//        imageUrlList.add("https://cdn.cellphones.com.vn/media/ltsoft/promotioncategory/normal_sale_nokia_24.08.2020.png");
//
//        ImageView imageView;
//        for (int i = 0; i < imageUrlList.size(); i++) {
//            imageView = new ImageView(this);
//            Picasso.get().load(imageUrlList.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            AdapterViewFlipper.addView(imageView);
//            AdapterViewFlipper.setFlipInterval(3000);
//            AdapterViewFlipper.setAutoStart(true);
//        }
//        Animation animationIn = AnimationUtils.loadAnimation(this, R.anim.anim_flipper_in);
//        Animation animationOut =AnimationUtils.loadAnimation(this, R.anim.anim_filpper_out);
//        AdapterViewFlipper.setInAnimation(animationIn);
//        AdapterViewFlipper.setInAnimation(animationOut);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnLogin:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                return true;
            case R.id.btnSearch:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                return true;
            case R.id.home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            case R.id.uudai:
                startActivity(new Intent(MainActivity.this, UuDaiActivity.class));
                return true;
            case R.id.gioithieu:
                startActivity(new Intent(MainActivity.this, GioiThieuActivity.class));
                return true;
            case R.id.camnang:
                startActivity(new Intent(MainActivity.this, TipActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void mapping(){
        Button btnLogin = findViewById(R.id.btnLogin);
        EditText edtSearch = findViewById(R.id.edtSearch);
        Button btnSearch = findViewById(R.id.btnSearch);
        RecyclerView rvUudai = findViewById(R.id.rvUudai);
        RecyclerView rvCategory = findViewById(R.id.rvCategory);
        RecyclerView rvTour = findViewById(R.id.rvTour);

    }
}