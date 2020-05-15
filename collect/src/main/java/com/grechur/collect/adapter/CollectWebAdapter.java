package com.grechur.collect.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.collect.BR;
import com.grechur.collect.R;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.listener.OnItemClickListener;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectWebAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/15 10:24
 */
public class CollectWebAdapter extends BaseAdapter<CollectWebInfo, BaseViewHolder> {
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
        ViewDataBinding binding = holder.getBinding();
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
}
