package com.grechur.entry.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.common.listener.OnItemClickListener;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.RankInfo;
import com.grechur.entry.databinding.EntryItemRankBinding;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankAdapter
 * @Description: 排行榜
 * @Author: Grechur
 * @CreateDate: 2020/5/19 20:15
 */
public class RankAdapter extends BaseAdapter<RankInfo, BaseViewHolder> {

    public RankAdapter(Context mContext, List<RankInfo> mData) {
        super(mContext, mData);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        EntryItemRankBinding binding = DataBindingUtil.inflate(mInflater, R.layout.entry_item_rank, parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        EntryItemRankBinding binding = (EntryItemRankBinding) holder.getBinding();
        binding.setVariable(BR.rank,mData.get(position));
        if(position == 0){
            binding.tvRank.setBackgroundResource(R.drawable.entry_rank_bg_one);
        }else if(position == 1){
            binding.tvRank.setBackgroundResource(R.drawable.entry_rank_bg_two);
        }else if(position == 2){
            binding.tvRank.setBackgroundResource(R.drawable.entry_rank_bg_three);
        }else{
            binding.tvRank.setBackgroundResource(R.drawable.entry_rank_bg_common);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


}
