package com.viettravelapplication.Fragment;

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
import com.viettravelapplication.Adapter.BannerAdapter;
import com.viettravelapplication.Adapter.CategoryAdapter;
import com.viettravelapplication.Adapter.PromotionAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Model.Banner;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    AdapterViewFlipper bannerFlipper;
    RecyclerView rvCategory;
    RecyclerView rvPromotion;
    RecyclerView rvTour;
    Button btnLogin;
    EditText edtSearch;
    Button btnSearch;
    List<Tour> promotionList;
    List<Tour> tourList;
    List<Category> listCategory;
    List<Banner> bannerList;
    TourAdapter tourAdapter;
    CategoryAdapter categoryAdapter;
    PromotionAdapter promotionAdapter;
    BannerAdapter bannerAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        rvPromotion = (RecyclerView) view.findViewById(R.id.rvUudai);
        rvCategory = (RecyclerView) view.findViewById(R.id.rvCategory);
        rvTour = (RecyclerView) view.findViewById(R.id.rvTour);
        bannerFlipper = (AdapterViewFlipper) view.findViewById(R.id.bannerAdapter);
        getAllBanner();
        getAllCategory();
        getAllTour();
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
}