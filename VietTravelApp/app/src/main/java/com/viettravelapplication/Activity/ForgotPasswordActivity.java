package com.viettravelapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.viettravelapplication.R;
import com.viettravelapplication.Util.EmailUtil;

import org.json.JSONException;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText inputEmail;
    Button btnConfirmMail;

    boolean sending = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initMapping();
    }

    public void handleForgotPassword(View view) {
        String email = String.valueOf(inputEmail.getText());
        if (email.isEmpty()) {
            Toast.makeText(ForgotPasswordActivity.this, "Please input email", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!EmailUtil.isValid(email)) {
                Toast.makeText(ForgotPasswordActivity.this, "Email invalidate", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(ForgotPasswordActivity.this, "Sending", Toast.LENGTH_SHORT).show();
        if (!sending) {
            sending = true;
            RequestQueue requestQueue = Volley.newRequestQueue(ForgotPasswordActivity.this);
            String url = "https://viet-travel-development.herokuapp.com/api/user/forgot-password/?email=%s";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, String.format(url, email), null, response -> {
                sending = false;
                try {
                    Toast.makeText(ForgotPasswordActivity.this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    if (response.getBoolean("success")) {
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }, error -> {
                sending = false;
                error.printStackTrace();
                Toast.makeText(ForgotPasswordActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            });
            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(jsonRequest);
        }
    }

    private void initMapping() {
        inputEmail = findViewById(R.id.inputEmail);
        btnConfirmMail = findViewById(R.id.btnConfirmMail);
        btnConfirmMail.setOnClickListener(this::handleForgotPassword);
    }
}