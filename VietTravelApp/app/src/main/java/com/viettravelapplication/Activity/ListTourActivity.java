package com.viettravelapplication.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.Adapter.LineListTourAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListTourActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;
    ListView lvDSTour;
    List<Tour> tourList;
    LineListTourAdapter lineListTourAdapter;
    Toolbar tbTourList;
    String title = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tour);
        mapping();
        init();
    }

    private void getAllTourByCategoryId(int id) {
        tourList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(ListTourActivity.this);
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
                        lineListTourAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListTourActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        lineListTourAdapter = new LineListTourAdapter(ListTourActivity.this, R.layout.lineof_list_tours, tourList);
        lvDSTour.setAdapter((ListAdapter) lineListTourAdapter);
    }

    private void ActionToolBar(){
        setSupportActionBar(tbTourList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbTourList.setNavigationIcon(android.R.drawable.ic_menu_revert);
        tbTourList.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("home","home");
                startActivity(intent);
            }
        });
    }
    @SuppressLint("WrongViewCast")
    private void mapping() {
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.edtSearch);
        tbTourList = findViewById(R.id.tbTourList);
        lvDSTour = findViewById(R.id.lvDSTour);
    }
    private void init() {
        Intent intent = getIntent();
        Category cate = (Category) intent.getSerializableExtra("id");
        title = cate.getCategoryname();
        getAllTourByCategoryId(cate.getId());
        ActionToolBar();
    }

}

