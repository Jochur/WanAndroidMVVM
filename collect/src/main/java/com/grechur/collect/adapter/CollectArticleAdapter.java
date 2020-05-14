package com.grechur.collect.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.collect.BR;
import com.grechur.collect.R;
import com.grechur.collect.bean.ArticleInfo;
import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: HomeAdapter
 * @Description: 适配器
 * @Author: Grechur
 * @CreateDate: 2020/5/9 18:31
 */
public class CollectArticleAdapter extends BaseAdapter<ArticleInfo,BaseViewHolder> {



    public CollectArticleAdapter(Context mContext, List<ArticleInfo> mData) {
        super(mContext, mData);
    }


    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {

        ViewDataBinding bind = DataBindingUtil.inflate(mInflater, R.layout.collect_home_item, parent, false);
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
        ARouter.getInstance()
                .build(RouterSchame.WEB_VIEW_ACTIVITY)
                .withString(Constants.INTENT_URL,articleInfo.getLink())
                .withString(Constants.INTENT_TITLE,articleInfo.getTitle())
                .navigation();
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
