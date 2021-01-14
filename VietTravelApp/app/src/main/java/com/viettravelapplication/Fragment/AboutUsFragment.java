package com.viettravelapplication.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.Model.AboutUs;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AboutUsFragment extends Fragment {
//    String url = "192.168.43.57:81/VietTravel/getAboutUs.php";
//    static AboutUs aboutUs;
    TextView tvDescription;
    TextView tvRule;
    TextView tvAddress;
    TextView tvContact;

    String description;
    String rule;
    String contact;
    String address;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aboutus,container,false);
        tvDescription = view.findViewById(R.id.tvDescription);
        tvRule = view.findViewById(R.id.tvRule);
        tvAddress = view.findViewById(R.id.tvAddress);
        tvContact = view.findViewById(R.id.tvContact);

        description = getResources().getString(R.string.description);
        address = getResources().getString(R.string.address);
        contact = getResources().getString(R.string.contact);
        rule = getResources().getString(R.string.rule);

        tvContact.setText(contact);
        tvRule.setText(rule);
        tvAddress.setText(address);
        String des = String.valueOf(Html.fromHtml("<![CDATA[<body style=\"text-align:justify\">"+description+"</body>]]>"));
        tvDescription.setText(Html.fromHtml(des));
//        getAboutUs();
        return view;
    }

//    private void getAboutUs() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url , null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            for (int i=0;i<response.length();i++){
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                String id = jsonObject.getString("id");
//                                String descriptions = jsonObject.getString("descriptions");
//                                String address= jsonObject.getString("address");
//                                String contact = jsonObject.getString("contact");
//                                String rule = jsonObject.getString("rule");
//                                aboutUs = new AboutUs(Integer.parseInt(id), descriptions, address, contact, rule);
//                                Toast.makeText(getActivity(),""+aboutUs.toString(),Toast.LENGTH_LONG).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getActivity(),""+error,Toast.LENGTH_LONG).show();
//            }
//        });
//        requestQueue.add(arrayRequest);
//        tvDescription.setText(aboutUs.getDescriptions());
//        tvAddress.setText(aboutUs.getAddress());
//        tvContact.setText(aboutUs.getContact());
//        tvRule.setText(aboutUs.getContact());
//    }
}
