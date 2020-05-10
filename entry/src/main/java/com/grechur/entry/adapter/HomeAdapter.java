package com.grechur.entry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: HomeAdapter
 * @Description: 适配器
 * @Author: Grechur
 * @CreateDate: 2020/5/9 18:31
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ArticleInfo> mData;
    private List<BannerInfo> mBanner;
    ViewDataBinding bind;

    public HomeAdapter(Context context, List<ArticleInfo> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setmBanner(List<BannerInfo> mBanner) {
        this.mBanner = mBanner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bind = DataBindingUtil.inflate(inflater, R.layout.entry_home_item, parent, false);
        return new ArticleViewHolder(bind.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleInfo articleInfo = mData.get(position);
        bind.setVariable(BR.article,articleInfo);
    }

    @Override
    public int getItemCount() {
        return mData.size()>0?mData.size():0;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
