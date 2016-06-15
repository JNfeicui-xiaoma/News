package com.example.administrator.news.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.CommentsAdapter;

public class CommentActivity extends Activity {
//新闻ID
    private int nid;
    private ListView mListView;
    private CommentsAdapter mAdapter;
    private ImageView iv_send,iv_back;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        nid=getIntent().getIntExtra("nid",-1);
        mListView= (ListView) findViewById(R.id.lv_comment);
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_send= (ImageView) findViewById(R.id.imageview_send);
        et_content= (EditText) findViewById(R.id.et_comment);
        mAdapter=new CommentsAdapter(this,mListView);
        mListView.setAdapter(mAdapter);

        iv_send.setOnClickListener(clickListener);
        iv_back.setOnClickListener(clickListener);
    }
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_back:
                    finish();
                    break;
                case R.id.imageview_send:
                    String content=et_content.getText().toString();
                    if (content==null||content.equals("")){
                        Toast.makeText(CommentActivity.this, "不写怎么发送啊！傻啊！", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    break;
            }
        }
    };
}
