package com.grechur.collect.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.collect.BR;
import com.grechur.collect.R;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.databinding.CollectItemWebBinding;
import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.itemtouchhelper.ItemTouchActionCallback;
import com.grechur.common.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectWebAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/15 10:24
 */
public class CollectWebAdapter extends BaseAdapter<CollectWebInfo, BaseViewHolder> implements ItemTouchActionCallback {
    public CollectWebAdapter(Context mContext, List<CollectWebInfo> mData) {
        super(mContext, mData);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.collect_item_web, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        CollectWebInfo webInfo = mData.get(position);
        CollectItemWebBinding binding = (CollectItemWebBinding) holder.getBinding();
        binding.setVariable(BR.web,webInfo);
        binding.setVariable(BR.listener,itemClickListener);
        binding.setVariable(BR.position,position);


    }

    @Override
    public int getItemCount() {
        return mData.isEmpty()?0:mData.size();
    }

    private OnItemClickListener itemClickListener;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public View getContentView(RecyclerView.ViewHolder holder) {
        BaseViewHolder messageHolder = (BaseViewHolder) holder;
        CollectItemWebBinding binding = (CollectItemWebBinding) messageHolder.getBinding();
        return binding.rlWeb;
    }

    @Override
    public int getMenuWidth(RecyclerView.ViewHolder holder) {
        BaseViewHolder messageHolder = (BaseViewHolder) holder;
        CollectItemWebBinding binding = (CollectItemWebBinding) messageHolder.getBinding();
        return binding.more.getWidth() + binding.delete.getWidth();
    }

    @Override
    public void onMove(int fromPos, int toPos) {
        Collections.swap(mData, fromPos, toPos);
        notifyItemMoved(fromPos, toPos);
    }

    @Override
    public void onMoved(int fromPos, int toPos) {
        //move action finished
    }
}
