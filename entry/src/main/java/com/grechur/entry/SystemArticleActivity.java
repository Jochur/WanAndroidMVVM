package com.grechur.entry;


import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.databinding.EntryActivitySystemArticleBinding;
import com.grechur.entry.viewmodel.SystemArticleViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterSchame.SYSTEM_ARTICLE_ACTIVITY)
public class SystemArticleActivity extends BaseActivity<SystemArticleViewModel, EntryActivitySystemArticleBinding> {

    @Autowired
    int cid;

    private int pageNum = 0;

    private HomeAdapter homeAdapter;
    private List<ArticleInfo> mData;

    private int totalPage;

    @Override
    protected void initView() {

        cid = getIntent().getIntExtra("cid",0);


        viewModel.getSystemArticle(pageNum,cid);

        mData = new ArrayList<>();
        homeAdapter = new HomeAdapter(this,mData);

        binding.systemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.systemRecyclerView.setAdapter(homeAdapter);

        viewModel.totalPage.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                totalPage = integer -1;
                if(pageNum >= totalPage){
                    binding.systemSmartRefresh.resetNoMoreData();
                }
            }
        });

        viewModel.mArticleData.observe(this, new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                if(articleInfos!=null&&!articleInfos.isEmpty()){
                    mData.addAll(articleInfos);
                    homeAdapter.notifyDataSetChanged();
                    if(binding.systemSmartRefresh.getState() == RefreshState.Loading){
                        binding.systemSmartRefresh.finishLoadMore();
                    }
                    if(binding.systemSmartRefresh.getState() == RefreshState.Refreshing){
                        binding.systemSmartRefresh.finishRefresh();
                    }
                }
            }
        });

        binding.systemSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                if(pageNum<=totalPage){
                    viewModel.getSystemArticle(pageNum,cid);
                }else{
                    binding.systemSmartRefresh.finishLoadMore();
                    binding.systemSmartRefresh.autoLoadMore();
                }

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                mData.clear();
                viewModel.getSystemArticle(pageNum,cid);
            }
        });

        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_system_article;
    }
}