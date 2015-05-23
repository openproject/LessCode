package com.jayfeng.lesscode.core;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;
import java.util.Objects;

public class AdapterLess {

    public static <T> BaseAdapter $base(final Context context,
                                        final List<T> list,
                                        final int layoutId,
                                        final CallBack callBack) {

        BaseAdapter result = new BaseAdapter() {

            @Override
            public int getCount() {
                if (list != null) {
                    return list.size();
                }
                return 0;
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
                    convertView = LayoutInflater.from(context).inflate(layoutId, null);
                    ;
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
                                        final int[] layoutIds,
                                        final FullCallBack fullCallBack) {

        BaseAdapter result = new BaseAdapter() {

            @Override
            public int getCount() {
                if (list != null) {
                    return list.size();
                }
                return 0;
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
                return layoutIds.length;
            }

            @Override
            public boolean isEnabled(int position) {
                return fullCallBack.isEnabled(position);
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder[] holders = new ViewHolder[layoutIds.length];
                int i = getItemViewType(position);
                if (null == convertView) {
                    holders[i] = new ViewHolder();
                    convertView = LayoutInflater.from(context).inflate(layoutIds[i], null);
                    convertView.setTag(holders[i]);
                } else {
                    holders[i] = (ViewHolder) convertView.getTag();
                }
                T t = getItem(position);
                return fullCallBack.getView(position, convertView, holders[i], t);
            }

        };
        return result;
    }

    public static <T> PagerAdapter $pager(final Context context,
                                          final List<T> list,
                                          final int layoutId,
                                          final PageCallBack pageCallBack) {
        PagerAdapter result = new PagerAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(context).inflate(layoutId, null);
                container.addView(view);
                pageCallBack.instantiateItem(position, view, list.get(position));
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
        return result;
    }

    public static <T> FragmentPagerAdapter $pager(FragmentManager fragmentManager,
                                                  final int count,
                                                  final FragmentPagerCallBack fragmentPagerCallBack) {
        FragmentPagerAdapter result = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentPagerCallBack.getItem(position);
            }

            @Override
            public int getCount() {
                return count;
            }
        };
        return result;
    }

    public interface CallBack<T> {
        View getView(int position, View convertView, ViewHolder holder, T t);
    }

    public interface FullCallBack<T> {
        View getView(int position, View convertView, ViewHolder holder, T t);

        int getItemViewType(int position);

        boolean isEnabled(int position);
    }

    public interface PageCallBack<T> {
        void instantiateItem(int position, View view, T t);
    }

    public interface FragmentPagerCallBack {
        Fragment getItem(int position);
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
