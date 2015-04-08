package com.jayfeng.lesscode.core;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class AdapterLess {

    public static <T> BaseAdapter $base(final Context context,
                         final List<T> list,
                         final int layoutId,
                         final CallBack callBack) {

        BaseAdapter result = new BaseAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public T getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (null == convertView) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(layoutId, null);;
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                T t = getItem(position);
                return callBack.getView(position, convertView, holder, t);
            }

        };
        return result;
    }


    public static <T> BaseAdapter $base(final Context context,
                                        final List<T> list,
                                        final int layoutId,
                                        final FullCallBack fullCallBack) {

        BaseAdapter result = new BaseAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public T getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public int getItemViewType(int position) {
                return fullCallBack.getItemViewType(position);
            }

            @Override
            public int getViewTypeCount() {
                return fullCallBack.getViewTypeCount();
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (null == convertView) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(layoutId, null);;
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                T t = getItem(position);
                return fullCallBack.getView(position, convertView, holder, t);
            }

        };
        return result;
    }

    public interface CallBack<T> {
        public View getView(int position, View convertView, ViewHolder holder, T t);
    }

    public interface FullCallBack<T> {
        public View getView(int position, View convertView, ViewHolder holder, T t);
        public int getItemViewType(int position);
        public int getViewTypeCount();
    }


    public static class ViewHolder {
        public SparseArray<View> views = new SparseArray<View>();

        public <T extends View> T $view(View convertView, int viewId) {
            View v = views.get(viewId);
            if (null == v) {
                v = ViewLess.$(convertView, viewId);
                views.put(viewId, v);
            }
            return (T) v;
        }
    }
}
