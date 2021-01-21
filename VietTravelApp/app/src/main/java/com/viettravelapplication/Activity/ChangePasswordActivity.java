package com.viettravelapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.EmailUtil;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText inputOldPassword;
    EditText inputNewPassword;
    EditText inputConfirmPassword;
    boolean sending = false;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initMapping();
    }

    private void initMapping() {
        inputOldPassword = findViewById(R.id.inputOldPassword);
        inputNewPassword = findViewById(R.id.inputNewPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
    }
    
    public void handleClickChangePassword(View view) throws JSONException {
        String oldPassword = String.valueOf(inputOldPassword.getText());
        String newPassword = String.valueOf(inputNewPassword.getText());
        String confirmPassword = String.valueOf(inputConfirmPassword.getText());
        if (oldPassword.isEmpty()) {
            Toast.makeText(ChangePasswordActivity.this, "Please input old password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (newPassword.isEmpty()) {
            Toast.makeText(ChangePasswordActivity.this, "Please input new password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPassword.isEmpty()) {
            Toast.makeText(ChangePasswordActivity.this, "Please input confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirmPassword.equals(newPassword)) {
            Toast.makeText(ChangePasswordActivity.this, "Confirm password not match", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(ChangePasswordActivity.this, "Sending", Toast.LENGTH_SHORT).show();
        if (!sending) {
            sending = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", sharedPreferences.getInt("id", -1));
            jsonObject.put("oldPassword", oldPassword);
            jsonObject.put("newPassword", newPassword);
            RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
            //String url = "https://viet-travel-development.herokuapp.com/api/user/change-password/";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, StringUtil.API_CHANGE_PASS, jsonObject, response -> {
                sending = false;
                try {
                    Toast.makeText(ChangePasswordActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    if (response.getBoolean("success")){
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                sending = false;
                error.printStackTrace();
                Toast.makeText(ChangePasswordActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);
        }
    }
}