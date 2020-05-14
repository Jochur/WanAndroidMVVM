package com.grechur.collect.fragment;

import android.os.Bundle;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.collect.R;
import com.grechur.collect.adapter.CollectArticleAdapter;
import com.grechur.collect.bean.ArticleInfo;
import com.grechur.collect.databinding.CollectFragmentArticleBinding;
import com.grechur.collect.viewmodel.CollectArticleViewModel;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectArticleFragment
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:33
 */
public class CollectArticleFragment extends BaseFragment<CollectArticleViewModel, CollectFragmentArticleBinding> {
    private int pageNum = 0;
    private List<ArticleInfo> mData;
    private CollectArticleAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mData = new ArrayList<>();
        mAdapter = new CollectArticleAdapter(getContext(),mData);

        binding.collectRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.collectRecycleView.setAdapter(mAdapter);

        binding.collectSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                viewModel.getCollect(pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                mData.clear();
                viewModel.getCollect(pageNum);
            }
        });

        viewModel.mData.observe(this, new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                if(articleInfos!=null&&!articleInfos.isEmpty()){
                    mData.addAll(articleInfos);
                    mAdapter.notifyDataSetChanged();
                }
                if(binding.collectSmartRefresh.getState()== RefreshState.Loading){
                    binding.collectSmartRefresh.finishLoadMore();
                }
                if(binding.collectSmartRefresh.getState()== RefreshState.Refreshing){
                    binding.collectSmartRefresh.finishRefresh();
                }
            }
        });

        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
                if(binding.collectSmartRefresh.getState()== RefreshState.Loading){
                    binding.collectSmartRefresh.finishLoadMore();
                }
                if(binding.collectSmartRefresh.getState()== RefreshState.Refreshing){
                    binding.collectSmartRefresh.finishRefresh();
                }
            }
        });

        viewModel.canLoad.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(binding.collectSmartRefresh.getState()== RefreshState.Loading){
                    binding.collectSmartRefresh.finishLoadMore();
                }
                binding.collectSmartRefresh.setNoMoreData(true);
            }
        });

        viewModel.getCollect(pageNum);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collect_fragment_article;
    }
}
