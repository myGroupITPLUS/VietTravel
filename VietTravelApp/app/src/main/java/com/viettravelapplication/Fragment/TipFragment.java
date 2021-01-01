package com.viettravelapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.viettravelapplication.Adapter.LineListPromotionAdapter;
import com.viettravelapplication.Adapter.TipAdapter;
import com.viettravelapplication.Interface.ItemClickListener;
import com.viettravelapplication.Model.Promotion;
import com.viettravelapplication.Model.Tip;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TipFragment extends Fragment {
    RecyclerView rcvDSTip;
    List<Tip> tipList;
    TipAdapter tipAdapter;
    private ItemClickListener itemClickListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiplist,container,false);
        rcvDSTip = (RecyclerView) view.findViewById(R.id.rcvDSTip);
        getAllTip();
        return view;
    }
    private void getAllTip() {
        tipList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, StringUtil.API_GET_ALL_TIP,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tipList.clear();
                        for (int i = 0; i < response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Tip tip = new Tip(jsonObject.getInt("id"), jsonObject.getString("title"), jsonObject.getString("content"));
//                                Toast.makeText(getActivity(), "Promotion: "+promotion.toString(), Toast.LENGTH_LONG).show();
                                tipList.add(tip);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        tipAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), ""+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(arrayRequest);
        tipAdapter = new TipAdapter(getActivity(), R.layout.line_tip, tipList);
        rcvDSTip.setAdapter(tipAdapter);
        rcvDSTip.setLayoutManager( new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
    }
}
