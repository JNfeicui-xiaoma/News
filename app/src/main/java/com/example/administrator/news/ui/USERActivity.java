package com.example.administrator.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.news.R;

public class USERActivity extends Activity {

    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mImageView= (ImageView) findViewById(R.id.iv_user);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(USERActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
