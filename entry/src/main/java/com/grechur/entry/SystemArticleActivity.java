package com.grechur.entry;


import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.grechur.common.ToolBar;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.adapter.SystemArticleAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.databinding.EntryActivitySystemArticleBinding;
import com.grechur.entry.viewmodel.SystemArticleViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterSchame.SYSTEM_ARTICLE_ACTIVITY)
public class SystemArticleActivity extends BaseActivity<SystemArticleViewModel, EntryActivitySystemArticleBinding> implements View.OnClickListener {

    @Autowired
    int cid;
    @Autowired
    String system_title;

    private int pageNum = 0;

    private SystemArticleAdapter systemArticleAdapter;


    @Override
    protected void initView() {

        cid = getIntent().getIntExtra("cid",0);
        system_title = getIntent().getStringExtra("system_title");

        systemArticleAdapter = new SystemArticleAdapter();

        binding.systemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.systemRecyclerView.setAdapter(systemArticleAdapter);

        viewModel.getData(pageNum,cid);

        viewModel.getPageData().observe(this, observer);

        binding.systemSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                viewModel.getPageData().getValue().getDataSource().invalidate();
            }
        });

        ToolBar toolBar = new ToolBar();
        toolBar.setToolTitle(system_title);
        toolBar.setShowBack(true);
        toolBar.setShowBar(true);
        toolBar.setOnClick(this);

        binding.setTool(toolBar);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_system_article;
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }

    Observer<PagedList<ArticleInfo>> observer = new Observer<PagedList<ArticleInfo>>() {
        @Override
        public void onChanged(PagedList<ArticleInfo> articleInfos) {
            systemArticleAdapter.submitList(articleInfos);
            Log.e("SystemArticleViewModel","isDetached:"+articleInfos.isDetached()+" isImmutable:"+articleInfos.isImmutable());
            Log.e("SystemArticleViewModel","size:"+systemArticleAdapter.getCurrentList().size()+" articleInfos:"+articleInfos.size());
            if(binding.systemSmartRefresh!=null&&binding.systemSmartRefresh.getState() == RefreshState.Refreshing){
                binding.systemSmartRefresh.finishRefresh();
            }
        }
    };
}
