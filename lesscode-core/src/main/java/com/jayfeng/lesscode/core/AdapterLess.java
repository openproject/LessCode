package com.jayfeng.lesscode.core;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 适配器简化相关的工具类
 * 常用于:ListView,ViewPager,RecyclerView
 */
public class AdapterLess {

    /**
     * 创建一个RecyclerView.Adapter
     * 主要是:
     * 1. 抽象出重复代码,默认实现一些常规代码
     * 2. 封装了RecyclerViewHolder
     * 3. 自动传递Model给getView
     * @param context
     * @param list model的列表
     * @param layoutId 布局xml的id
     * @param recycleCallBack 包含nBindViewHolder方法的回调
     * @param <T>
     * @return
     */
    public static <T> RecyclerView.Adapter<RecycleViewHolder> $recycle(final Context context,
                                                                       final List<T> list,
                                                                       final int layoutId,
                                                                       final RecycleCallBack recycleCallBack) {
        RecyclerView.Adapter<RecycleViewHolder> result = new RecyclerView.Adapter<RecycleViewHolder>() {
            @Override
            public RecycleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(context)
                        .inflate(layoutId, viewGroup, false);
                RecycleViewHolder recycleViewHolder = new RecycleViewHolder(view);
                return recycleViewHolder;
            }

            @Override
            public void onBindViewHolder(RecycleViewHolder viewHolder, int position) {
                T t = null;
                if (position < list.size()) {
                    t = list.get(position);
                }
                recycleCallBack.onBindViewHolder(position, viewHolder, t);
            }

            @Override
            public int getItemCount() {
                if (list != null) {
                    return list.size();
                }
                return 0;
            }
        };
        return result;
    }

    /**
     * 创建BaseAdapter
     * 1. 抽象出重复代码,默认实现一些常规代码
     * 2. 封装了ViewHolder
     * 3. 自动传递Model给getView
     * @param context
     * @param list model的列表
     * @param layoutId 布局xml的id
     * @param callBack 包含getView方法的回调
     * @param <T>
     * @return
     */
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
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                T t = null;
                if (position < list.size()) {
                    t = getItem(position);
                }
                return callBack.getView(position, convertView, holder, t);
            }

        };
        return result;
    }


    /**
     * 同上,也是创建了BaseAdapter
     * 支持多布局,也多增加了两个回调方法,便于自定义:
     * 1. getViewType
     * 2. isEnabled
     * @param context
     * @param list model列表
     * @param layoutIds 布局xml的id数组
     * @param fullCallBack 包含getView,getViewType,isEnabled方法的回调
     * @param <T>
     * @return
     */
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
                T t = null;
                if (position < list.size()) {
                    t = getItem(position);
                }
                return fullCallBack.getView(position, convertView, holders[i], t);
            }

        };
        return result;
    }

    /**
     * 创建PagerAdapter
     * 主要是:
     * 1. 抽象出重复代码,默认实现一些常规代码
     * 2. 封装了instantiateItem
     * 3. 自动传递Model给getView
     * @param context
     * @param list
     * @param layoutId
     * @param pageCallBack
     * @param <T>
     * @return
     */
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

    public static <T> FragmentPagerAdapter $pager(final FragmentManager fragmentManager,
                                                  final int count,
                                                  final FullFragmentPagerCallBack fullFragmentPagerCallBack) {
        FragmentPagerAdapter result = new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fullFragmentPagerCallBack.getItem(position);
            }

            @Override
            public int getCount() {
                return count;
            }

            @Override
            public String getPageTitle(int position) {
                return fullFragmentPagerCallBack.getPageTitle(position);
            }
        };
        return result;
    }

    public interface RecycleCallBack<T> {
        void onBindViewHolder(int position, RecycleViewHolder recycleViewHolder, T t);
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

    public interface FullFragmentPagerCallBack {
        Fragment getItem(int position);
        String getPageTitle(int position);
    }


    /**
     * ViewHolder类相当于一个享元模式的工厂类
     * 主要用了以下优化点:
     * 1. 缓存了findViewById的view,如果已经创建,则直接返回,提高了性能
     * 2. 用SparseArray代替HashMap优化性能
     */
    public static class ViewHolder {
        public SparseArray<View> views = new SparseArray<>();

        /**
         * 从缓存里获取viewId对应的View
         * @param convertView
         * @param viewId
         * @param <T>
         * @return
         */
        public <T extends View> T $view(View convertView, int viewId) {
            View v = views.get(viewId);
            if (null == v) {
                v = ViewLess.$(convertView, viewId);
                views.put(viewId, v);
            }
            return (T) v;
        }
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public RecycleViewHolder(View itemView) {
            super(itemView);
        }

        public <T extends View> T $view(int viewId) {
            return ViewLess.$(itemView, viewId);
        }
    }
}
