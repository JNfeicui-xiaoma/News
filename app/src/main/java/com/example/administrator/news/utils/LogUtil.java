package com.example.administrator.news.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/5/31.
 */
public class LogUtil {

    public static final String TAG="新闻随意看";
    public static boolean isDebug=true;//调试日志的开关

    public static void  d(String tag,String msg) {
        if (isDebug){
            Log.d(tag, msg);
        }
    }
    public static void d(String msg){
        if (isDebug){
            Log.d(LogUtil.TAG, msg);
        }
    }

}
