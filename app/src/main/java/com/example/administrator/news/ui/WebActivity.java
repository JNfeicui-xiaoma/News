package com.example.administrator.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.example.administrator.news.dao.NewsDBManager;
import com.example.administrator.news.entity.News;

public class WebActivity extends Activity {

    private WebView mWebView;
    private ImageView image_menu;
    private PopupWindow popupWindow;
    News newsitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        mWebView= (WebView) findViewById(R.id.webView);
        WebSettings settings=mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(url);

        //缩放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSupportZoom(true);//设定支持缩放

        newsitem = (News) getIntent().getSerializableExtra("newsitem");
        image_menu= (ImageView) findViewById(R.id.imageView_menu);
        image_menu.setOnClickListener(clickListener);
        initPopupWindow();
    }
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.imageView_menu:
                    if (popupWindow != null && popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else if (popupWindow != null) {
                        popupWindow.showAsDropDown(image_menu, 0, 12);
                    }
                    break;
            }
        }
    };
    private void initPopupWindow() {
        View popview = getLayoutInflater().inflate(R.layout.item_pop_save, null);
        popupWindow = new PopupWindow(popview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        TextView tv_savelocal = (TextView) popview.findViewById(R.id.saveLocal);
        tv_savelocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                NewsDBManager manager = new NewsDBManager(WebActivity.this);
                //TODO
                if (manager.saveLoveNews(newsitem)) {
//                    showToast("收藏成功！\n在主界面侧滑菜单中查看");
                    Toast.makeText(WebActivity.this, "收藏成功！\n在主界面侧滑菜单中查看", Toast.LENGTH_SHORT).show();
                }
                else {
//                    showToast("已经收藏过这条新闻了！\n在主界面侧滑菜单中查看");
                    Toast.makeText(WebActivity.this, "已经收藏过这条新闻了！\n在主界面侧滑菜单中查看", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
