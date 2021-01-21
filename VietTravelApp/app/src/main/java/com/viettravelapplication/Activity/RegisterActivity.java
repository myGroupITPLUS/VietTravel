package com.viettravelapplication.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.EmailUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    EditText inputUsername;
    EditText inputEmail;
    EditText inputPassword;
    EditText inputConfirmPassword;
    boolean sending = false;
    TextView alreadyHaveAccount;
    SharedPreferences sharedPreferences;

    public final int CODE_REGISTER = 23;

    public final String SUCCESS = "success";
    public final String FAIL = "fail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initMapping();
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMapping() {
        inputUsername = findViewById(R.id.inputUsername);
        inputEmail = findViewById(R.id.inputOldPassword);
        inputPassword = findViewById(R.id.inputNewPassword);
        inputConfirmPassword = findViewById(R.id.inputConfirmPassword);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        sharedPreferences = getSharedPreferences("userProfile", MODE_PRIVATE);
    }

    public void finishWithResult(boolean result) {
        Intent data = new Intent();
        if (result){
            data.setData(Uri.parse(SUCCESS));
        }else{
            data.setData(Uri.parse(FAIL));
        }
        setResult(CODE_REGISTER, data);
        finish();
    }


    public void handleClickChangeToLogin(View v) {
        finishWithResult(false);
    }

    public void handleClickRegister(View view) throws JSONException {
        String username = String.valueOf(inputUsername.getText());
        String email = String.valueOf(inputEmail.getText());
        String password = String.valueOf(inputPassword.getText());
        String confirmPassword = String.valueOf(inputConfirmPassword.getText());
        if (username.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please input email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please input email", Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (!EmailUtil.isValid(email)){
                Toast.makeText(RegisterActivity.this, "Email invalidate", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please input password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPassword.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please input confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!confirmPassword.equals(password)) {
            Toast.makeText(RegisterActivity.this, "Confirm password not match", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(RegisterActivity.this, "Sending", Toast.LENGTH_SHORT).show();
        if (!sending) {
            sending = true;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            String url = "http://54.169.31.141:8080/api/user/register/";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, response -> {
                sending = false;
                try {
                    boolean success = response.getBoolean("success");
                    if (success) {
                        Toast.makeText(RegisterActivity.this, "Register complete", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(RegisterActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                sending = false;
                error.printStackTrace();
                Toast.makeText(RegisterActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);
        }
    }
}