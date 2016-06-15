package com.example.administrator.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    //泛型集合
//    protected ArrayList<T> list = new ArrayList<T>();
    //上下文
    protected Context mContext;
    //定义布局过滤器
    protected LayoutInflater mInflater;
    protected List<T> mList = new ArrayList<T>();

    public MyBaseAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    //清除所有数据
    public void clear() {
        mList.clear();
    }

    //查找所有数据
    public List<T> getAdapterData() {
        return mList;
    }

    //     封装添加一条记录方法
//     t 一条数据的对象
//     isClearOld 是否清除原数据
    public void appendData(T t, boolean isClearOld) {
        if (t == null) { //非空验证
            return;
        }
        if (isClearOld) {//如果 true 清空原数据
            mList.clear();
        }//添加一条新数据到最后
        mList.add(t);
    }

    //添加多条记录
    public void addendData(ArrayList<T> aList, boolean isClearOld) {
        if (aList == null) {
            return;
        }
        if (isClearOld) {
            mList.clear();
        }
        mList.addAll(aList);
    }

    //添加一条记录到第一条
    public void appendDataTop(T t, boolean isClearOld) {
        if (t == null) {//非空验证
            return;
        }
        if (isClearOld) {//如果ture清空元数据
            mList.clear();
        }
        //添加一条记录到底一条
        mList.add(0, t);
    }

    //添加多条记录到顶部
    public void addendDataTop(ArrayList<T> aList, boolean isClearOld) {
        if (aList == null) {
            return;
        }
        if (isClearOld) {
            mList.clear();
        }
        mList.addAll(0, aList);
    }

    public void updata() {
        //刷新适配器
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (mList == null) {
            return null;
        }
        //如果已经没有数据了返回空
        if (position > mList.size() - 1) {
            return null;
        }
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getMyView(position, convertView, parent);
    }

    //作为预留方法，定义为抽象方法，要求子类继承该基础类时，必须重写该方法
    public abstract View getMyView(int position, View convertView, ViewGroup parent);


}
