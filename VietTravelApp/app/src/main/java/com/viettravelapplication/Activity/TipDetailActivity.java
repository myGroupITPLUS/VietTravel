package com.viettravelapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.viettravelapplication.Model.Tip;
import com.viettravelapplication.R;

public class TipDetailActivity extends AppCompatActivity {
    String title = "";
    String content = " ";

    TextView tvTitle;
    TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tip);
        mapping();
        init();
    }


    private void init() {
        Intent intent1 = getIntent();
        Tip tip = (Tip) intent1.getSerializableExtra("tipDetail");
        System.out.println(tip.toString());
        title = tip.getTitle();
        content = tip.getContent();

       tvTitle.setText(title);
       tvContent.setText(content);
    }

    private void mapping() {
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
    }
}
