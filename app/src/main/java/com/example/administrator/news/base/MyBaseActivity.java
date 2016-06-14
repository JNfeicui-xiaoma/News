package com.example.administrator.news.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.administrator.news.utils.LogUtil;

/**
 * Created by Administrator on 2016/5/31.
 * 父类activity用来调用和打印activity的生命周期和节目的进入和退出动画
 */
public class MyBaseActivity extends Activity{
    private int screen_w,screen_h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen_w=getWindowManager().getDefaultDisplay().getWidth();
        screen_h=getWindowManager().getDefaultDisplay().getHeight();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(LogUtil.TAG,getClass().getSimpleName()+"onDestroy()");
    }
    public void openActivity(Class<?> pClass, Bundle bundle, Uri uri){
        Intent intent=new Intent(this,pClass);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        if(uri!=null){
            intent.setData(uri);
        }
        startActivity(intent);
    }
    //通过class名字进行跳转只带bundle数据
    public void openActivity(Class<?> pClass,Bundle bundle){
        openActivity(pClass,bundle,null);
    }
    //通过Class名字进行跳转
    public void openActivity(Class<?> pClass){
        openActivity(pClass,null,null);
    }
    //通过action字符串进行跳转
    public void openActivity(String action){
        openActivity(action,null,null);
    }
    //通过字符串进行跳转只带Bundel类
    public void openActivity(String action,Bundle bundel){
        openActivity(action,bundel,null);
    }
    public void openActivity(String action,Bundle bundel,Uri uri){
        Intent intent=new Intent(action);
        if(bundel!=null){
            intent.putExtras(bundel);
        }
        if (uri!=null){
            intent.setData(uri);
        }
        startActivity(intent);
    }
    /*
    公共功能封装
     */
    private Toast toast;
    public void showToast(int resId){
        showToast(getString(resId));
    }
    public void showToast(String msg){
        if(toast==null){
            toast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        }
    }
}
