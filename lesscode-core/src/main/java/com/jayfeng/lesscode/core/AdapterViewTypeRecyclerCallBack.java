package com.jayfeng.lesscode.core;

/**
 * 进一步分发多种类型的FullRecyclerCallBack
 * @param <T>
 */
public abstract class AdapterViewTypeRecyclerCallBack<T> implements AdapterLess.FullRecyclerCallBack<T> {

    @Override
    public void onBindViewHolder(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {
        onViewCommon(position, recyclerViewHolder, t);

        if (getItemViewType(position) == 0) {
            onViewType0(position, recyclerViewHolder, t);
        } else if (getItemViewType(position) == 1) {
            onViewType1(position, recyclerViewHolder, t);
        } else if (getItemViewType(position) == 2) {
            onViewType2(position, recyclerViewHolder, t);
        } else if (getItemViewType(position) == 3) {
            onViewType3(position, recyclerViewHolder, t);
        } else if (getItemViewType(position) == 4) {
            onViewType4(position, recyclerViewHolder, t);
        } else if (getItemViewType(position) == 5) {
            onViewType5(position, recyclerViewHolder, t);
        }
    }

    public void onViewCommon(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType0(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType1(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType2(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType3(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType4(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    public void onViewType5(int position, AdapterLess.RecyclerViewHolder recyclerViewHolder, T t) {

    }

    @Override
    public abstract int getItemViewType(int position);
}
