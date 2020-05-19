package com.grechur.entry.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.entry.R;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.databinding.EntryItemSearchBinding;
import com.grechur.entry.view.SearchItemView;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 14:00
 */
public class SearchAdapter extends BaseAdapter<SearchBean,BaseViewHolder> {


    public SearchAdapter(Context mContext, List<SearchBean> mData) {
        super(mContext, mData);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding bind = DataBindingUtil.inflate(mInflater, R.layout.entry_item_search, parent, false);
        return new BaseViewHolder(bind);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {

        SearchBean searchBean = mData.get(position);

        EntryItemSearchBinding binding = (EntryItemSearchBinding) holder.getBinding();
        binding.searchView.setData(searchBean);

    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }
}
