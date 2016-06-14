package com.example.administrator.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.news.R;
import com.example.administrator.news.base.MyBaseActivity;

public class LogoActivity extends MyBaseActivity {
    private ImageView mImageView;
    private Animation mAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mImageView= (ImageView) findViewById(R.id.iv_show);
        mAnimation= AnimationUtils.loadAnimation(this,R.anim.alpha);
        mImageView.startAnimation(mAnimation);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LogoActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
