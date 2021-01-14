package com.viettravelapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.viettravelapplication.Adapter.LineListPromotionAdapter;
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

public class PromotionFragment extends Fragment {
    RecyclerView rcvDSUuDai;
    EditText edtSearch;
    Button btnSearch;
    List<Promotion> promotionList;
    LineListPromotionAdapter lineListPromotionAdapter;
    private ItemClickListener itemClickListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotionlist,container,false);
        rcvDSUuDai = (RecyclerView) view.findViewById(R.id.rcvDSUuDai);
        getAllPromotion();
        return view;
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
                                Promotion promotion = new Promotion(jsonObject.getInt("id"),
                                        jsonObject.getInt("categoryid"), jsonObject.getInt("promotionid"),
                                        jsonObject.getString("name"), jsonObject.getString("diemdi"),
                                        jsonObject.getString("diemden"), jsonObject.getString("timedi"),
                                        jsonObject.getString("timeve"), jsonObject.getString("descriptions"),
                                        jsonObject.getString("images"), (float) jsonObject.getDouble("price"));
//                                Toast.makeText(getActivity(), "Promotion: "+promotion.toString(), Toast.LENGTH_LONG).show();
                                promotionList.add(promotion);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lineListPromotionAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        lineListPromotionAdapter = new LineListPromotionAdapter(getActivity(), R.layout.lineof_list_promotion, promotionList);
        rcvDSUuDai.setAdapter(lineListPromotionAdapter);
        rcvDSUuDai.setLayoutManager( new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }
}
