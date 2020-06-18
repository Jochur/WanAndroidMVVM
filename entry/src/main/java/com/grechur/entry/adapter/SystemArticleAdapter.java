package com.grechur.entry.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.listener.OnItemClickListener;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.ArticleInfo;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemArticleAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/15 14:43
 */
public class SystemArticleAdapter extends PagedListAdapter<ArticleInfo, BaseViewHolder> {

    private OnItemClickListener itemClick;

    public SystemArticleAdapter() {
        super(diffCallback);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.entry_system_article_item, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        ArticleInfo item = getItem(position);
        Log.e("SystemArticleViewModel","size:"+position+" "+getCurrentList().size());
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.article, item);
        binding.setVariable(BR.itemClick, itemClick);
        binding.setVariable(BR.position, position);
    }

    public void setOnItemClick(OnItemClickListener onItemClick){
        this.itemClick = onItemClick;
    }

    static DiffUtil.ItemCallback diffCallback = new DiffUtil.ItemCallback<ArticleInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull ArticleInfo oldItem, @NonNull ArticleInfo newItem) {
            return oldItem.getLink().equals(newItem.getLink());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticleInfo oldItem, @NonNull ArticleInfo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };
}
