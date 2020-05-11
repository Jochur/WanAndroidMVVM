package com.grechur.common.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: BaseAdapter
 * @Description: 基础Adapter
 * @Author: Grechur
 * @CreateDate: 2020/5/11 10:57
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;

    public BaseAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindVH(holder,position);
    }

    protected abstract VH onCreateVH(ViewGroup parent, int viewType);

    protected abstract void onBindVH(VH holder, int position);
}
