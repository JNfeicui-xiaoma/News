package com.example.administrator.news.adapter;

import android.app.Application;
import android.content.res.Configuration;

import com.example.administrator.news.utils.LogUtil;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/31.
 * 全局存储容器
 */
public class MyApplication extends Application{
    //用来保存整个应用程序的数据
    private HashMap<String,Object> allData=new HashMap<String,Object>();
    //存数据
    public void addAllDta(String key,Object value){
        allData.put(key,value);
    }
    //取数据
    public Object getAllData(String key){
        if(allData.containsKey(key)){
            return allData.get(key);
        }
        return null;
    }
    //  删除一条数据
    public void delAllData(String key){
        if (allData.containsKey(key)){
            allData.remove(key);
        }
    }
    //删除所有数据
    public void delAllData(){
        allData.clear();
    }
    //单列模式
    private static MyApplication application;

    public static MyApplication getInstance(){
        LogUtil.d(LogUtil.TAG,"MyApplication onCreate");
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        LogUtil.d(LogUtil.TAG,"application oncreate");
    }
    //内存不足的时候
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.d(LogUtil.TAG,"application onLowMemory");
    }
    //结束的时候
    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.d(LogUtil.TAG,"application onTerminate");
    }
    //配置改变的时候

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.d(LogUtil.TAG,"application onConfigurationChanged");
    }
}
