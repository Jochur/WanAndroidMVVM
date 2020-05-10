package com.grechur.entry.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseFragment;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.databinding.EntryFragmentHomeBinding;
import com.grechur.entry.viewmodel.HomeViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<HomeViewModel, EntryFragmentHomeBinding> {

    private HomeAdapter mAdapter;
    private List<ArticleInfo> mData;

    private int pageNum = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mData = new ArrayList<>();
        mAdapter  = new HomeAdapter(getContext(),mData);
        binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeRecyclerView.setAdapter(mAdapter);

        viewModel.mainModel.mData.observe(this, new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                if(articleInfos!=null&&!articleInfos.isEmpty()) {
                    mData.addAll(articleInfos);
                    mAdapter.notifyDataSetChanged();
                }
                if(binding.smartRefreshLayout.getState() == RefreshState.Loading){
                    binding.smartRefreshLayout.finishLoadMore();
                }
                if(binding.smartRefreshLayout.getState() == RefreshState.Refreshing){
                    binding.smartRefreshLayout.finishRefresh();
                }
            }
        });
        viewModel.mainModel.netError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                ToastUtils.show(e.getMessage());
                if(binding.smartRefreshLayout.getState() == RefreshState.Loading){
                    binding.smartRefreshLayout.finishLoadMore();
                }
                if(binding.smartRefreshLayout.getState() == RefreshState.Refreshing){
                    binding.smartRefreshLayout.finishRefresh();
                }
            }
        });
        viewModel.mainModel.mBanner.observe(this, new Observer<List<BannerInfo>>() {
            @Override
            public void onChanged(List<BannerInfo> bannerInfos) {
                if(bannerInfos!=null&&!bannerInfos.isEmpty()) {
                    mAdapter.setmBanner(bannerInfos);
                }

            }
        });
        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                viewModel.mainModel.homeArticle(pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mData.clear();
                pageNum = 0;
                viewModel.mainModel.homeArticle(pageNum);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_home;
    }
}
