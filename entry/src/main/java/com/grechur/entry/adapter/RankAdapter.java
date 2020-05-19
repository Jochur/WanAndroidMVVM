package com.grechur.entry.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.entry.bean.RankInfo;

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
        return null;
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
