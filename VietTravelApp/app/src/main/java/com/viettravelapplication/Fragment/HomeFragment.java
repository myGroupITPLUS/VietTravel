package com.viettravelapplication.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.Activity.LoginActivity;
import com.viettravelapplication.Activity.MainActivity;
import com.viettravelapplication.Adapter.BannerAdapter;
import com.viettravelapplication.Adapter.CategoryAdapter;
import com.viettravelapplication.Adapter.PromotionAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
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

public class HomeFragment extends Fragment{
    AdapterViewFlipper bannerFlipper;
    RecyclerView rvCategory;
    RecyclerView rvPromotion;
    RecyclerView rvTour;
    Button btnLogin;
    EditText edtSearch;
    Button btnSearch;
    List<Promotion> promotionList;
    List<Tour> tourList;
    List<Category> listCategory;
    List<Banner> bannerList;
    TourAdapter tourAdapter;
    CategoryAdapter categoryAdapter;
    PromotionAdapter promotionAdapter;
    BannerAdapter bannerAdapter;
    private ItemClickListener itemClickListener;

    SharedPreferences sharedPreferences;

    public final int REQUEST_CODE = 123;

    public final int CODE_LOGIN = 12;

    public final String SUCCESS = "success";
    public final String FAIL = "fail";

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        rvPromotion = (RecyclerView) view.findViewById(R.id.rvUudai);
        rvCategory = (RecyclerView) view.findViewById(R.id.rvCategory);
        rvTour = (RecyclerView) view.findViewById(R.id.rvTour);
        bannerFlipper = (AdapterViewFlipper) view.findViewById(R.id.bannerAdapter);
        btnLogin = view.findViewById(R.id.btnLogin);

        sharedPreferences = this.getActivity().getSharedPreferences("userProfile", MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", -1);
        if (id == -1){
            btnLogin.setText("Đăng nhập");
            btnLogin.setOnClickListener(this::handleOpenLogin);
        }else{
            btnLogin.setText("Đăng xuất");
            btnLogin.setOnClickListener(this::handleLogout);
        }

        getAllBanner();
        getAllCategory();
        getAllTour();
        getAllPromotion();
        return view;
    }
    private void getAllBanner() {
        bannerList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,StringUtil.API_GET_ALL_BANNER, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i=0;i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String descriptions = jsonObject.getString("descriptions");
                                String url = jsonObject.getString("url");
                                String img = jsonObject.getString("images");
                                bannerList.add(new Banner(Integer.parseInt(id),name,descriptions,url,img));
                                //Toast.makeText(MainActivity.this,""+new Banner(Integer.parseInt(id),name,descriptions,url,img).toString(),Toast.LENGTH_LONG).show();
                            }
                            bannerAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(arrayRequest);
        bannerAdapter = new BannerAdapter(getActivity(),R.layout.banner_layout,bannerList);
        bannerFlipper.setAdapter(bannerAdapter);
        bannerFlipper.setFlipInterval(3000);
        bannerFlipper.setAutoStart(true);
    }
    private void getAllCategory() {
        listCategory = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.API_GET_ALL_CATEGORY, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listCategory.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Category category = new Category(jsonObject.getInt("id"), jsonObject.getString("categoryname"), jsonObject.getString("descriptions"), jsonObject.getString("images"));
                                //Toast.makeText(MainActivity.this, ""+category.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        categoryAdapter = new CategoryAdapter(getActivity(), R.layout.line_category, listCategory);
        rvCategory.setAdapter(categoryAdapter);
        rvCategory.setLayoutManager( new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
    }
    private void getAllTour() {
        tourList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.API_GET_ALL_TOUR, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tourList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Tour tour = new Tour(jsonObject.getInt("id"), jsonObject.getInt("categoryid"), jsonObject.getInt("promotionid"), jsonObject.getString("name"), jsonObject.getString("diemdi"), jsonObject.getString("diemden"), jsonObject.getString("timedi"), jsonObject.getString("timeve"), jsonObject.getString("descriptions"), jsonObject.getString("images"), (float) jsonObject.getDouble("price"));
                                //Toast.makeText(MainActivity.this, ""+tour.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        tourAdapter = new TourAdapter(getActivity(), R.layout.line_tour, tourList);
        rvTour.setAdapter(tourAdapter);
        rvTour.setLayoutManager( new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
    }
    private void getAllPromotion() {
        promotionList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.API_GET_ALL_TOUR, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        promotionList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Promotion promotion = new Promotion(jsonObject.getInt("id"), jsonObject.getInt("categoryid"), jsonObject.getInt("promotionid"), jsonObject.getString("name"), jsonObject.getString("diemdi"), jsonObject.getString("diemden"), jsonObject.getString("timedi"), jsonObject.getString("timeve"), jsonObject.getString("descriptions"), jsonObject.getString("images"), (float) jsonObject.getDouble("price"));
//                                Toast.makeText(getActivity(), "Promotion: "+promotion.toString(), Toast.LENGTH_LONG).show();
                                promotionList.add(promotion);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        promotionAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        promotionAdapter = new PromotionAdapter(getActivity(), R.layout.line_promotion, promotionList);
        rvPromotion.setAdapter(promotionAdapter);
        rvPromotion.setLayoutManager( new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
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
    }
}