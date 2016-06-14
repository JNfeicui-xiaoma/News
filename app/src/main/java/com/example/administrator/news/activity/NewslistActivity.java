package com.example.administrator.news.activity;


import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.news.R;
import com.example.administrator.news.base.MyBaseActivity;

public class NewslistActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);
    }
}
