package com.example.administrator.news.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.base.MyBaseActivity;
import com.example.administrator.news.dao.NewsDBManager;
import com.example.administrator.news.entity.News;
import com.example.administrator.news.entity.ParserNews;
import com.example.administrator.news.utils.HttpURLUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/1.
 * 核心板新闻客户端
 */
public class ActivityMain extends MyBaseActivity{
    private ListView mListView;
    private NewsAdapter mAdapter;
    private NewsDBManager mDBManager;
    private int limit=10;//每页显示十行
    private int offset;//第几页
    private ParserNews newsParser;//解析数据Json

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_newslist);
//        mListView= (ListView) findViewById(R.id.listview);
//        mDBManager=NewsDBManager.getNewsDBManager(this);
//        //先加载缓存的新闻
//        mAdapter=new NewsAdapter(this,mListView);
//    }
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if (msg.what==100){
//                loadDataFromDB(limit, offset);
//            }else if(msg.what==200){
//                showToast("网络连接错误");
//            }
//
////            dialog.dismiss();
//        }
//    };
//    private void loadDataFromDB( int limit2,int offset2){
//        ArrayList<News> data = mDBManager.queryNews(limit2, offset2);
//        mAdapter.addendData(data, false);
//        mAdapter.updata();
//        this.offset=offset+data.size();
//    }
//    //异步加载数据
//    private void loadData(){
////        dialog = ProgressDialog.show(this, null, "加载中，请稍候。。。 ");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String path="http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&ni d=1&stamp=20140321&cnt=20";
//                newsParser=new ParserNews(ActivityMain.this);
//                //发出请求  得倒返回数据
//                String HttpRespones= HttpURLUtil.httpGet(path);
//            }
//        }).start();
//
//    }


}
