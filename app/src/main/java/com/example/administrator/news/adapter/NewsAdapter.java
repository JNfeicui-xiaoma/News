package com.example.administrator.news.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.bean.NewsBean;
import com.example.administrator.news.entity.News;
import com.example.administrator.news.utils.LoadImage;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 * 新闻数据适配器
 */
public class NewsAdapter extends MyBaseAdapter<NewsBean.DataBean> implements AbsListView.OnScrollListener {
    private LoadImage mLoadImage;
    private int start,end;
    private boolean isFistIn;
    public static String[] urls ;

    private Bitmap defaultBitmap;
    ListView listView;
    public NewsAdapter(Context context, List<NewsBean.DataBean> jsonList,ListView listView) {
        super(context);
        mList.clear();
        this.mList=jsonList;

        mLoadImage =new LoadImage(listView);
        urls=new String[mList.size()];

        for (int i = 0; i < mList.size(); i++) {
            NewsBean.DataBean bean = (NewsBean.DataBean) mList.get(i);
            urls[i] = bean.getIcon();
        }
        isFistIn = true;
        listView.setOnScrollListener(this);
    }



    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_list_news,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView1_item_list_news);
            viewHolder.title = (TextView) convertView.findViewById(R.id.textView1_item_list_news);
            viewHolder.summary = (TextView) convertView.findViewById(R.id.textView2_item_list_news);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        NewsBean.DataBean bean=mList.get(position);
        //开启线程加载图片
        String urlTag=bean.getIcon();
        viewHolder.imageView.setTag(urlTag);//给图片设置一个标记
        mLoadImage.showImageByAsyncTask(viewHolder.imageView,urlTag);

        viewHolder.title.setText(bean.getTitle());
        viewHolder.summary.setText(bean.getSummary());

        return convertView;
    }
    class ViewHolder {
        ImageView imageView;
        TextView title;
        TextView summary;

    }
//屏幕变化的时候调用监听
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {    //SCROLL_STATE_IDLE   滚动的闲置状态
            mLoadImage.LoadImage(start, end);
            Log.d("第一次","SCROLL_STATE_IDLE ");
        } else {
            mLoadImage.cancelAllTasks();
            Log.d("第一次","cancelAllTasks ");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        start = firstVisibleItem;
        end = firstVisibleItem + visibleItemCount;
        //第一次显示的时候调用
        if (isFistIn && visibleItemCount > 0) {
            mLoadImage.LoadImage(start, end);
            Log.d("第一次","jiazai ");
            isFistIn = false;
        }
    }

    public NewsAdapter(Context context, ListView listView) {
        super(context);
        defaultBitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.defaultpic);
        mLoadImage = new LoadImage(context,listener);
        this.listView = listView;
    }
private LoadImage.ImageLoadListener listener = new LoadImage.ImageLoadListener() {

    @Override
    public void imageLoadOk(Bitmap bitmap, String url) {
        ImageView iv = (ImageView) listView.findViewWithTag(url);
        if (iv != null)
            iv.setImageBitmap(bitmap);
    }
};
}
