package com.example.administrator.news.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.news.R;
import com.example.administrator.news.entity.Comment;
import com.example.administrator.news.utils.LoadImage;


/**
 * Created by Administrator on 2016/6/15.
 */
public class CommentsAdapter extends MyBaseAdapter<Comment> {
    private ListView mListView;

    public CommentsAdapter(Context context,ListView listView) {
        super(context);
        this.mListView=listView;
    }

    @Override
    public View getMyView(int position, View convertView, ViewGroup parent) {
        HoldView holdView=null;
        if (convertView==null){
            convertView=mInflater.inflate(R.layout.item_list_comment,null);
            holdView=new HoldView(convertView);
            convertView.setTag(holdView);
        }else{
            holdView= (HoldView) convertView.getTag();
        }
        Comment comment=mList.get(position);
        holdView.tv_comment.setText(comment.getContent());
        holdView.tv_time.setText(comment.getStamp());
        holdView.tv_user.setText(comment.getUid());

        return convertView;
    }
    public class HoldView {
        public ImageView iv_list_image;
        public TextView tv_user;
        public TextView tv_time;
        public TextView tv_comment;

        public HoldView(View view) {
            iv_list_image = (ImageView) view.findViewById(R.id.imageView1);
            tv_user = (TextView) view.findViewById(R.id.textView2);
            tv_time = (TextView) view.findViewById(R.id.textView3);
            tv_comment = (TextView) view.findViewById(R.id.textView1);
        }
    }

    private LoadImage.ImageLoadListener listener = new LoadImage.ImageLoadListener() {

        @Override
        public void imageLoadOk(Bitmap bitmap, String url) {
            ImageView iv = (ImageView) mListView.findViewWithTag(url);
            if (iv != null)
                iv.setImageBitmap(bitmap);
        }
    };
}
