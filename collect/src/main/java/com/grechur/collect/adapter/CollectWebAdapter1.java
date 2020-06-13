package com.grechur.collect.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.collect.BR;
import com.grechur.collect.R;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.databinding.CollectItemWebBinding;
import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
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
public class CollectWebAdapter1 extends PagedListAdapter<CollectWebInfo, BaseViewHolder> {


    public CollectWebAdapter1() {
        super(DIFF_CALLBACK);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.collect_item_web, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        CollectWebInfo webInfo = getItem(position);
        CollectItemWebBinding binding = (CollectItemWebBinding) holder.getBinding();
        binding.setVariable(BR.web,webInfo);
        binding.setVariable(BR.position,position);


    }

    private static DiffUtil.ItemCallback<CollectWebInfo> DIFF_CALLBACK = new DiffUtil.ItemCallback<CollectWebInfo>() {

        @Override
        public boolean areItemsTheSame(@NonNull CollectWebInfo oldItem, @NonNull CollectWebInfo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CollectWebInfo oldItem, @NonNull CollectWebInfo newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    };

}
