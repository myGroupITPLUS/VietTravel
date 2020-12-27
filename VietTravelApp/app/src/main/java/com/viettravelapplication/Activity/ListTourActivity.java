package com.viettravelapplication.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.viettravelapplication.Adapter.LineListTourAdapter;
import com.viettravelapplication.Adapter.TourAdapter;
import com.viettravelapplication.Model.Category;
import com.viettravelapplication.Model.Tour;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListTourActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;
    ListView lvDSTour;

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
        List<Tour> tourList = new ArrayList<>();
        lineListTourAdapter = new LineListTourAdapter(ListTourActivity.this, R.layout.lineof_list_tours, tourList);
        lvDSTour.setAdapter((ListAdapter) lineListTourAdapter);
        lvDSTour.setHasTransientState(true);
//        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.URL_GET_TOUR_BY_CATEGORY_ID + "?categoryid=" + id, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            JSONObject obj;
//                            for (int i = 0; i < response.length(); i++) {
//                                obj = response.getJSONObject(i);
//                                Tour tour = new Tour(obj.getInt("id"), obj.getString("nametour"),
//                                        obj.getString("diemdi"), obj.getString("diemden"),
//                                        obj.getString("timedi"), obj.getString("timeve"));
//                                tourList.add(tour);
//                            }
//                            lineListTourAdapter.notifyDataSetChanged();
//                        }catch (Exception ex){
//                        }
//                    }
//                });
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

