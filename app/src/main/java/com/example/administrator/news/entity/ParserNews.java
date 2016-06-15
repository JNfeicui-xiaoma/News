package com.example.administrator.news.entity;

import android.content.Context;

import com.example.administrator.news.bean.NewsBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/31.
 */
public class ParserNews {
    private Context mContext;
    public ParserNews(Context context){
        this.mContext=context;
    }
    //解析新闻数据
    public ArrayList<NewsBean.DataBean> parse(JSONObject jsonObject)throws Exception{
        ArrayList<NewsBean.DataBean> newsList=new ArrayList<NewsBean.DataBean>();
        //根据数据块名称获取一个数据
        JSONArray jsonArray=jsonObject.getJSONArray("data");
        //循环遍历这个数组
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object=jsonArray.getJSONObject(i);
            int nid=object.getInt("nid");
            String title=object.getString("title");
            String summary=object.getString("summary");
            String icon=object.getString("icon");
            String link=object.getString("link");
            int type=object.getInt("type");
            NewsBean.DataBean news=new NewsBean.DataBean();
            newsList.add(news);
        }
        return newsList;
    }

}
