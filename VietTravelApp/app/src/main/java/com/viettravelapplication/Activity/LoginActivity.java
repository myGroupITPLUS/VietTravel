package com.viettravelapplication.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;
    boolean sending = false;
    SharedPreferences sharedPreferences;

    public final int REQUEST_CODE = 234;

    public final int CODE_LOGIN = 12;
    public final int CODE_REGISTER = 23;

    public final String SUCCESS = "success";
    public final String FAIL = "fail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initMapping();
    }

    private void initMapping() {
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputNewPassword);
        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
    }


    public void handleClickChangeToRegister(View v) {
        startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), REQUEST_CODE); ;
    }

    public void handleClickChangeToForgotPassword(View v) {
        startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)); ;
    }


    public void finishWithResult (boolean result) {
        Intent data = new Intent();
        if (result){
            data.setData(Uri.parse(SUCCESS));
        }else{
            data.setData(Uri.parse(FAIL));
        }
        setResult(CODE_LOGIN, data);
        finish();
    }

    public void handleClickLogin(View view) throws JSONException {
        String email = String.valueOf(inputEmail.getText());
        String password = String.valueOf(inputPassword.getText());
        if (email.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(LoginActivity.this, "Sending", Toast.LENGTH_SHORT).show();
        if (!sending) {
            sending = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            //String url = "http://54.169.31.141:8080/api/user/login/";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, StringUtil.API_LOGIN, jsonObject, response -> {
                sending = false;
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        JSONObject user = response.getJSONObject("user");
                        editor.putInt("id", user.getInt("id"));
                        editor.putString("username", user.getString("username"));
                        editor.putString("phone", user.getString("phone"));
                        editor.putString("address", user.getString("address"));
                        editor.putString("email", user.getString("email"));
                        editor.apply();
                        finishWithResult(true);
                    } else {
                        Toast.makeText(LoginActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                sending = false;
                error.printStackTrace();
                Toast.makeText(LoginActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == CODE_REGISTER) {
                String returnedResult = data.getData().toString();
                if (returnedResult.equals(SUCCESS)) {
                    finishWithResult(true);
                }
            }
        }
    }
}
