package com.grechur.entry;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseActivity;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.RankAdapter;
import com.grechur.entry.bean.RankInfo;
import com.grechur.entry.databinding.EntryActivityRankBinding;
import com.grechur.entry.viewmodel.RankViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankActicity
 * @Description: 排行榜页面
 * @Author: Grechur
 * @CreateDate: 2020/5/19 19:48
 */
public class RankActivity extends BaseActivity<RankViewModel, EntryActivityRankBinding> {
    private RankAdapter mAdapter;
    private int pageNum=1;
    @Override
    protected void initView() {

        mAdapter = new RankAdapter(this,viewModel.getRank());
        binding.rvRank.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRank.setAdapter(mAdapter);

        viewModel.mData.observe(this, new Observer<List<RankInfo>>() {
            @Override
            public void onChanged(List<RankInfo> rankInfos) {
                mAdapter.notifyDataSetChanged();
                resetStatus();
            }
        });

        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null)
                    ToastUtils.show(e.getMessage());
                resetStatus();
            }
        });

        viewModel.mCanLoad.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(binding.srlRefresh.getState() == RefreshState.Loading){
                    binding.srlRefresh.finishLoadMore();
                }
                binding.srlRefresh.setNoMoreData(true);
            }
        });

        binding.srlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                viewModel.rank(pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum=0;
                viewModel.rank(pageNum);
            }
        });

        viewModel.rank(pageNum);

    }

    private void resetStatus(){
        if(binding.srlRefresh.getState() == RefreshState.Refreshing){
            binding.srlRefresh.finishRefresh();
        }
        if(binding.srlRefresh.getState()==RefreshState.Loading){
            binding.srlRefresh.finishLoadMore();
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_rank;
    }
}
