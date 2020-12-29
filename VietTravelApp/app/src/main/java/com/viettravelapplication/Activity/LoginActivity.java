package com.viettravelapplication.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.http.HttpMethod;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;
    boolean sending = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initMapping();
    }

    private void initMapping() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
    }


    public void handleClickChangeToRegister(View v) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void handleClickLogin(View view) throws JSONException {
        String email = String.valueOf(inputEmail.getText());
        String password = String.valueOf(inputPassword.getText());
        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please input email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please input password", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(LoginActivity.this, "Sending", Toast.LENGTH_SHORT).show();
        if (!sending) {
            sending = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            String url = "https://viet-travel-development.herokuapp.com/api/user/login/";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
                sending = false;
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        Toast.makeText(LoginActivity.this, "Login complete", Toast.LENGTH_SHORT).show();
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        JSONObject user = response.getJSONObject("user");
                        editor.putInt("id", user.getInt("id"));
                        editor.putString("username", user.getString("username"));
                        editor.putString("phone", user.getString("phone"));
                        editor.putString("address", user.getString("address"));
                        editor.putString("email", user.getString("email"));
                        editor.apply();
                    } else {
                        Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);
        }
    }
}