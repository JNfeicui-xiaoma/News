package com.example.administrator.news.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/31.
 */
public class HttpURLUtil {

    private static HttpURLConnection connection = null;
    private static int TimeOut = 10000;
    private static int MaxTotalConnections = 8;

    public static synchronized HttpURLConnection getHttpURLConnection(String url) {
        HttpURLConnection httpURLConnection = null;
        if (connection == null) {
            try {
                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                httpURLConnection.setConnectTimeout(TimeOut);
//                httpURLConnection.set  设置连接时的连接次数
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return httpURLConnection;
    }

    //从网上获取Json
    public static String getHttpJson(String url) {
        String jsonData = null;
        HttpURLConnection httpURLConnection = getHttpURLConnection(url);
        InputStream inputStream = null;
        ByteArrayOutputStream out = null;
        try {
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] by = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(by)) != -1) {
                    out.write(by, 0, length);
                }
                jsonData = out.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null && out != null) {
                    inputStream.close();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return jsonData;
    }
}
