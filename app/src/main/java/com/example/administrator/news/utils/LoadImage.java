package com.example.administrator.news.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/3.
 */
public class LoadImage {
    private ImageView mImageView;
    private String mUrlTag;
    private ListView mListView;
    private Set<NewsAsyncTask> mTasks;
    //缓存机制
    private LruCache<String,Bitmap> mCache;
    private Context context;
    private ImageLoadListener listener;
    public LoadImage(ListView listView){
        this.mListView=listView;
        this.mTasks=new HashSet<NewsAsyncTask>();

        //设置缓存的大小
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheSize=maxMemory/4;
        mCache=new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return super.sizeOf(key, value);//返回图片的大小
            }
        };

    }
    public void LoadImage(int start,int end){
        for (int i = start; i <end ; i++) {
            mUrlTag= NewsAdapter.urls[i];
            //从缓存中取出对应的图片
            Bitmap bitMap=getBitmapFromCache(mUrlTag);
            if (bitMap==null){

                NewsAsyncTask task=new NewsAsyncTask(mUrlTag);
                task.execute(mUrlTag);
                mTasks.add(task);
            }else{
                ImageView imageView = (ImageView) mListView.findViewWithTag(mUrlTag);
                imageView.setImageBitmap(bitMap);
            }
        }
    }
    //加载Bitmap到Cache
    public void addBitmapToCache(String url,Bitmap bitmap){
        if (mCache.get(url)==null){
            mCache.put(url,bitmap);
        }
    }
    //从缓存中获取数据
    public Bitmap getBitmapFromCache(String url) {
        return mCache.get(url);
    }

    public Bitmap getBitmapFromURL(String url) {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    //    使用AsyncTask**************************************************************
    public void showImageByAsyncTask(ImageView imageView, String url) {
        //从缓存中取出对应的图片
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null) {          //没有去下载
            imageView.setImageResource(R.drawable.defaultpic);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
    //暂停异步任务
    public void cancelAllTasks() {
        if (mTasks != null){
            for (NewsAsyncTask task : mTasks){
                task.cancel(false);
            }
        }
    }

    public class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private String mUrl;

        public NewsAsyncTask(String url) {
            this.mUrl = url;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = getBitmapFromURL(mUrl);
            if (bitmap != null) {
                addBitmapToCache(mUrl, bitmap);
            }

            return bitmap;  //下载图片
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            ImageView imageView= (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            mTasks.remove(this);  //下载完成就移除了
        }
    }
    public interface ImageLoadListener{
        void imageLoadOk(Bitmap bitmap,String url);
    }
    public LoadImage(Context context,ImageLoadListener listener){
        this.context=context;
        this.listener=listener;
    }
}
