package com.example.administrator.news.ui;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.dao.NewsDBManager;
import com.example.administrator.news.entity.News;

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
        listView=(ListView) view.findViewById(R.id.listview);
        adapter=new NewsAdapter(getActivity(), listView);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(itemListener);
        return view;
    }

//    private void loadLoveNews() {
//        ArrayList<News> data=new NewsDBManager(getActivity()).queryLoveNews();
//        adapter.addendData(data, true);
//    }
//
//    private AdapterView.OnItemClickListener itemListener=new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//            // 打开显示当前选中的新闻
//            News news = (News) parent.getItemAtPosition(position);
//            Intent intent=new Intent(getActivity(), WebActivity.class);
//            intent.putExtra("newsitem", news);
//            getActivity().startActivity(intent);
//        }
//    };
}
