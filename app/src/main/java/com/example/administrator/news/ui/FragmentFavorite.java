package com.example.administrator.news.ui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.bean.NewsBean;
import com.example.administrator.news.dao.NewsDBManager;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFavorite extends Fragment {
    private View view;
    private ListView listView;
    private NewsAdapter adapter;

    public FragmentFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_favorite, container, false);
        listView=(ListView) view.findViewById(R.id.listview_favorite);
        adapter=new NewsAdapter(getActivity(), listView);
        listView.setAdapter(adapter);//空指针异常
        listView.setOnItemClickListener(itemListener);
        loadLoveNews();
        return view;
    }
        //从数据库加载出来收藏的网页
    private void loadLoveNews() {
        ArrayList<NewsBean.DataBean> aList=new NewsDBManager(getActivity()).queryLoveNews();
        adapter.addendData(aList,true);
    }
    private AdapterView.OnItemClickListener itemListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            // 打开显示当前选中的新闻
            NewsBean.DataBean news = (NewsBean.DataBean) parent.getItemAtPosition(position);
            Intent intent=new Intent(getActivity(), WebActivity.class);
            intent.putExtra("newsitem", (Parcelable) news);
            getActivity().startActivity(intent);
        }
    };
}
