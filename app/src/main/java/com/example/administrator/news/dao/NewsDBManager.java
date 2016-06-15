package com.example.administrator.news.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.news.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/1.
 * 管理数据库类
 */
public class NewsDBManager {

    //单列模式
    private static NewsDBManager dbManager;
    private DBOpenHelper mHelper;

    public NewsDBManager(Context context){
        mHelper=new DBOpenHelper(context);
    }
    //同步锁
    public static NewsDBManager getNewsDBManager(Context context){
        if (dbManager==null) {
            if (dbManager == null) {
                synchronized (NewsDBManager.class) {
                    dbManager = new NewsDBManager(context);
                }
            }
        }
        return dbManager;
    }

    /*
    添加
     */
//    public void insertNews(News news){
//        SQLiteDatabase db=mHelper.getWritableDatabase();
//        ContentValues values=new ContentValues();
//
//        values.put("nid",news.getNid());
//        values.put("title",news.getTitle());
//        values.put("summary",news.getSummary());
//        values.put("icon",news.getIcon());
//        values.put("link",news.getLink());
//        values.put("type",news.getType());
//        db.insert("news",null,values);
//        db.close();
//    }
    //数据数量
    public long getCount(){
        SQLiteDatabase db=mHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from news",null);
        long len=0;
        if (cursor.moveToFirst()){
            len=cursor.getLong(0);
        }
        cursor.close();
        db.close();
        return len;
    }
    //查询数据
    public ArrayList<NewsBean.DataBean> queryNews(int count,int offset){
        ArrayList<NewsBean.DataBean> newsList=new ArrayList<NewsBean.DataBean>();
        SQLiteDatabase db=mHelper.getWritableDatabase();
        String sql="select * from news order by _id desc limit "+count+"offset"+offset;
        Cursor cursor=db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do {
                int nid=cursor.getInt(cursor.getColumnIndex("nid"));
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String summary=cursor.getString(cursor.getColumnIndex("summary"));
                String icon=cursor.getString(cursor.getColumnIndex("icon"));
                String link=cursor.getString(cursor.getColumnIndex("link"));
                int type=cursor.getInt(cursor.getColumnIndex("type"));
                NewsBean.DataBean news=new NewsBean.DataBean();
                newsList.add(news);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return newsList;
    }
    public boolean saveLoveNews(NewsBean.DataBean news){
        try {
            SQLiteDatabase db=mHelper.getWritableDatabase();
            Cursor cursor=db.rawQuery("select * from lovenews where nid="+news.getNid(),null);
            if(cursor.moveToFirst()){
                cursor.close();
                return false;
            }
            cursor.close();
            ContentValues values=new ContentValues();
            values.put("type", news.getType());
            values.put("nid", news.getNid());
            values.put("icon", news.getIcon());
            values.put("title", news.getTitle());
            values.put("summary", news.getSummary());
            values.put("link", news.getLink());
            db.insert("lovenews", null, values);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<NewsBean.DataBean> queryLoveNews(){
        ArrayList<NewsBean.DataBean> newsList=new ArrayList<NewsBean.DataBean>();
        SQLiteDatabase db=mHelper.getReadableDatabase();
        String sql="select * from news order by _id desc";
        Cursor cursor=db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String stamp=cursor.getString(cursor.getColumnIndex("stamp"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                String link = cursor.getString(cursor.getColumnIndex("link"));
                NewsBean.DataBean news = new NewsBean.DataBean();
                newsList.add(news);
            } while (cursor.moveToNext());
            cursor.close();
            db.close();
        }
        return newsList;
    }
}
