package com.example.administrator.news.test;

import com.example.administrator.news.utils.HttpURLUtil;

import junit.framework.TestCase;

/**
 * Created by Administrator on 2016/5/31.
 */
public class NewsTestCase extends TestCase{
//解析新闻数据 测试的是parseNews方法
    public void testParseNews(){
        String path ="http://118.244.212.82:9092/newsClient/DoNewsStartList?size=10&typeId= 1";
        //发送请求，得倒返回数据
//        String json= HttpURLUtil.httpGet(path);
    }

}
