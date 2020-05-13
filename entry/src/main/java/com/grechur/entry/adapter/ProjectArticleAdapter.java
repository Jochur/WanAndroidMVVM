package com.grechur.entry.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.ArticleInfo;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectArticleAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 15:25
 */
public class ProjectArticleAdapter extends BaseAdapter<ArticleInfo, BaseViewHolder> {


    public ProjectArticleAdapter(Context mContext, List<ArticleInfo> mData) {
        super(mContext, mData);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.entry_item_project, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        ArticleInfo articleInfo = mData.get(position);
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.article,articleInfo);
        binding.setVariable(BR.adapter,this);
        binding.setVariable(BR.position,position);
    }

    @Override
    public int getItemCount() {
        return mData.isEmpty()?0:mData.size();
    }

    public void onItemClick(ArticleInfo articleInfo,int position){
//        Intent intent = new Intent();
//        intent.setClass(mContext, WebViewActivity.class);
//        intent.putExtra(Constants.INTENT_TITLE,articleInfo.getTitle());
//        intent.putExtra(Constants.INTENT_URL,articleInfo.getLink());
//        mContext.startActivity(intent);
        ARouter.getInstance()
                .build(RouterSchame.WEB_VIEW_ACTIVITY)
                .withString(Constants.INTENT_URL,articleInfo.getLink())
                .withString(Constants.INTENT_TITLE,articleInfo.getTitle())
                .navigation();
    }

    public void onLinkClick(ArticleInfo articleInfo,int position){
//        Intent intent = new Intent();
//        intent.setClass(mContext, WebViewActivity.class);
//        intent.putExtra(Constants.INTENT_TITLE,articleInfo.getTitle());
//        intent.putExtra(Constants.INTENT_URL,articleInfo.getProjectLink());
//        mContext.startActivity(intent);
        ARouter.getInstance()
                .build(RouterSchame.WEB_VIEW_ACTIVITY)
                .withString(Constants.INTENT_URL,articleInfo.getProjectLink())
                .withString(Constants.INTENT_TITLE,articleInfo.getTitle())
                .navigation();
    }
}
