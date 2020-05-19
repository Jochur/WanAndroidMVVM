package com.grechur.entry;

import com.grechur.common.base.BaseActivity;
import com.grechur.entry.databinding.EntryActivityRankBinding;
import com.grechur.entry.viewmodel.RankViewModel;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankActicity
 * @Description: 排行榜页面
 * @Author: Grechur
 * @CreateDate: 2020/5/19 19:48
 */
public class RankActicity extends BaseActivity<RankViewModel, EntryActivityRankBinding> {
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_rank;
    }
}
