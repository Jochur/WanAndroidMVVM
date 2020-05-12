package com.grechur.entry.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.contant.Constants;
import com.grechur.common.ui.WebViewActivity;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.ArticleInfo;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: HomeAdapter
 * @Description: 适配器
 * @Author: Grechur
 * @CreateDate: 2020/5/9 18:31
 */
public class HomeAdapter extends BaseAdapter<ArticleInfo,BaseViewHolder> {



    public HomeAdapter(Context mContext, List<ArticleInfo> mData) {
        super(mContext, mData);
    }


    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {

        ViewDataBinding bind = DataBindingUtil.inflate(mInflater, R.layout.entry_home_item, parent, false);
        return new BaseViewHolder<>(bind);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        ViewDataBinding binding = holder.getBinding();
        ArticleInfo articleInfo = mData.get(position);
        binding.setVariable(BR.article,articleInfo);
        binding.setVariable(BR.adapter,this);
        binding.setVariable(BR.position,position);
    }

    public void itemClick(ArticleInfo articleInfo,int position){
//        ToastUtils.show(articleInfo.getShareUser());
        Intent intent = new Intent();
        intent.setClass(mContext, WebViewActivity.class);
        intent.putExtra(Constants.INTENT_URL,articleInfo.getLink());
        intent.putExtra(Constants.INTENT_TITLE,articleInfo.getTitle());
        mContext.startActivity(intent);
    }

    public void imgClick(ArticleInfo articleInfo,int position){
        articleInfo.setZan(1);
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData.size()>0?mData.size():0;
    }

}