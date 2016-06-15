package com.example.administrator.news.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.news.R;
import com.example.administrator.news.adapter.NewsAdapter;
import com.example.administrator.news.bean.NewsBean;
import com.example.administrator.news.utils.HttpURLUtil;
import com.example.administrator.news.utils.NewsParse;
import com.example.administrator.news.utils.RefreshableView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity implements AdapterView.OnItemClickListener {
    private FragmentMenu fragmentMenu;//左菜单栏
    private FragmentMenuRight fragmentMenuRight;//右菜单栏
    private FragmentType fragmentType;
    private DengluFragment dengluFragment;
    private FragmentFavorite mFavorite;
    public static com.example.administrator.news.view.slidingmenu.SlidingMenu slidingMenu;
    private ImageView iv_set, iv_user;
    private TextView textView_title;
    private List<NewsBean.DataBean> mData;
    private NewsAdapter mAdapter;
    private ListView mListView;
    private static int mWhat = 1;
    ArrayAdapter<String> adapter;
    RefreshableView refreshableView;
    String json11 = "http://118.244.212.82:9092/newsClient/news_list?" +
            "ver=4&subid=1&dir=1&nid=2&stamp=20150601&cnt=20";
    //news的新闻集合
    private ArrayList<NewsBean.DataBean> jsonList = new ArrayList<NewsBean.DataBean>();
    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mAdapter = new NewsAdapter(HomeActivity.this, jsonList, mListView);
                    mListView.setAdapter(mAdapter);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        initView();


    }

    //初始化控件 关联ID 设置监听
    private void initView() {
        iv_set = (ImageView) findViewById(R.id.imageView_set);
        iv_user = (ImageView) findViewById(R.id.imageView_user);
        textView_title = (TextView) findViewById(R.id.textView1_title);
        mListView = (ListView) findViewById(R.id.listview_home);
        iv_set.setOnClickListener(onClickListener);
        iv_user.setOnClickListener(onClickListener);
        mListView.setOnItemClickListener(this);

        refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);
        initSlidingMenu();
        //解析数据
        new Thread() {
            @Override
            public void run() {

                String json = HttpURLUtil.getHttpJson(json11);
                jsonList = NewsParse.parseNewsJson(json);
                mHandler.sendEmptyMessage(mWhat);
            }
        }.start();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageView_set:
                    if (slidingMenu != null && slidingMenu.isMenuShowing()) {
                        slidingMenu.showContent();
                    } else if (slidingMenu != null) {
                        slidingMenu.showMenu();
                    }
                    break;
                case R.id.imageView_user:
                    if (slidingMenu != null && slidingMenu.isMenuShowing()) {
                        slidingMenu.showContent();
                    } else if (slidingMenu != null) {
                        slidingMenu.showSecondaryMenu();
                    }
                    break;
            }
        }
    };

    /**
     * 初始化侧滑菜单
     **/
    public void initSlidingMenu() {
        fragmentMenu = new FragmentMenu();
        fragmentMenuRight = new FragmentMenuRight();
        slidingMenu = new com.example.administrator.news.view.slidingmenu.SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

        slidingMenu.setMenu(R.layout.layout_menu);
        slidingMenu.setSecondaryMenu(R.layout.layout_menu_right);
        getFragmentManager().beginTransaction()
                .replace(R.id.layout_menu, fragmentMenu).commit();
        getFragmentManager().beginTransaction()
                .replace(R.id.layout_menu_right, fragmentMenuRight).commit();


    }


    @Override
    public void onBackPressed() {
        if (slidingMenu.isMenuShowing()) {
            slidingMenu.showContent();
        } else {
            exitTwice();
        }
    }

    //两次退出
    private boolean isFirstExit = true;

    private void exitTwice() {
        if (isFirstExit) {
            Toast.makeText(this, "再按一次退出！", Toast.LENGTH_SHORT).show();
            isFirstExit = false;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(3000);
                        isFirstExit = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                ;
            }.start();
        } else {
            finish();
        }
    }

    /*
    显示新闻更多fenlei
     */
    private void showFragmentType() {
        setTitle("分类");
        slidingMenu.showContent();
        if (fragmentType == null) {
            fragmentType = new FragmentType();
            getFragmentManager().beginTransaction().replace(R.id.rl_main, fragmentType).commit();
        }
    }

    public void showDengluFragment() {
        setTitle("用户登录");
        slidingMenu.showContent();
        if (dengluFragment == null)
            dengluFragment = new DengluFragment();
        getFragmentManager().beginTransaction()
                .replace(R.id.rl_main, dengluFragment).commit();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(HomeActivity.this, WebActivity.class);
        intent.putExtra("url", mAdapter.getItem(position).getLink());
        startActivity(intent);
    }
    public void showFragmentFavorite() {
        setTitle("收藏新闻");
        slidingMenu.showContent();
        if(mFavorite==null)
            mFavorite=new FragmentFavorite();
        getFragmentManager().beginTransaction()
                .replace(R.id.rl_main, mFavorite).commit();
    }
}
