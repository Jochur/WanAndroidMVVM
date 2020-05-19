package com.grechur.entry.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseFragment;
import com.grechur.common.base.BaseReFragment;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.adapter.ImageAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.databinding.EntryFragmentHomeBinding;
import com.grechur.entry.viewmodel.HomeViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseReFragment<HomeViewModel, EntryFragmentHomeBinding> {

    private HomeAdapter mAdapter;

    private ImageAdapter imageAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        imageAdapter = new ImageAdapter(getContext(), viewModel.getBanner());
        binding.banner.setAdapter(imageAdapter)
                .setIndicator(new CircleIndicator(getContext()))
                .setIndicatorNormalColorRes(R.color.indicator_color)
                .setIndicatorSelectedColorRes(R.color.all_bg);
        mAdapter = new HomeAdapter(getContext(),viewModel.getData());
        binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.homeRecyclerView.setAdapter(mAdapter);
        Log.e("BaseFragment","viewModel:"+viewModel.pageNum+" viewModel data:"+viewModel.getData().size());
        Log.e("BaseFragment","hasObservers:"+viewModel.mLiveData.hasObservers());
        if(!viewModel.mLiveData.hasObservers()) {
            viewModel.mLiveData.observe(this, new Observer<List<ArticleInfo>>() {
                @Override
                public void onChanged(List<ArticleInfo> articleInfos) {
                    if (articleInfos != null && !articleInfos.isEmpty()) {
//                        Log.e("BaseFragment", " articleInfos:" + articleInfos.size());
                        mAdapter.notifyDataSetChanged();
                    }
                    if (binding.smartRefreshLayout.getState() == RefreshState.Loading) {
                        binding.smartRefreshLayout.finishLoadMore();
                    }
                    if (binding.smartRefreshLayout.getState() == RefreshState.Refreshing) {
                        binding.smartRefreshLayout.finishRefresh();
                    }
                }
            });
        }

        viewModel.netError.observe(this, new Observer<ApiException>() {
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

        if(!viewModel.mLiveBanner.hasObservers()) {
            viewModel.mLiveBanner.observe(this, new Observer<List<BannerInfo>>() {
                @Override
                public void onChanged(List<BannerInfo> bannerInfos) {
                    if (bannerInfos != null && !bannerInfos.isEmpty()) {
                        imageAdapter.notifyDataSetChanged();
                    }

                }
            });
        }

        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum ++;
                viewModel.onLoad(viewModel.pageNum);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.pageNum = 0;
                viewModel.onRefresh();
            }
        });
        viewModel.onRefresh();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_home;
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.banner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.banner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
