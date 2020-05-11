package com.grechur.common.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: BaseViewHolder
 * @Description: 基础viewholder
 * @Author: Grechur
 * @CreateDate: 2020/5/11 10:37
 */
public class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B mBinding;

    public BaseViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public B getBinding() {
        return mBinding;
    }

}
