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
import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.ToolBar;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.listener.OnItemClickListener;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.adapter.SystemArticleAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.databinding.EntryActivitySystemArticleBinding;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.entry.viewmodel.SystemArticleViewModel;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;
import com.grechur.net.Empty;
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

        cid = getIntent().getIntExtra("cid", 0);
        system_title = getIntent().getStringExtra("system_title");

        systemArticleAdapter = new SystemArticleAdapter();

        binding.systemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.systemRecyclerView.setAdapter(systemArticleAdapter);

        viewModel.getData(pageNum, cid);

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

        systemArticleAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int id = view.getId();
                if (id == R.id.entry_item_zan) {
                    PagedList<ArticleInfo> currentList = systemArticleAdapter.getCurrentList();
                    if (currentList != null) {
                        final ArticleInfo articleInfo = currentList.get(position);
                        MainApi.getInstance()
                                .collectArticle(articleInfo.getId())
                                .subscribe(new BaseSubscriber<Empty>() {
                                    @Override
                                    public void onNext(Empty empty) {
                                        articleInfo.setZan(1);
                                    }

                                    @Override
                                    public void onError(ApiException e) {
                                        if (e != null) {
                                            if (e.getCode() == 0) {
                                                articleInfo.setZan(1);
                                            } else {
                                                ToastUtils.show(e.getMessage());
                                            }
                                        }
                                    }
                                });
                    }
                } else if (id == R.id.rl_system_art_item) {
                    PagedList<ArticleInfo> currentList = systemArticleAdapter.getCurrentList();
                    if (currentList != null) {
                        final ArticleInfo articleInfo = currentList.get(position);
                        ARouter.getInstance()
                                .build(RouterSchame.WEB_VIEW_ACTIVITY)
                                .withString(Constants.INTENT_URL, articleInfo.getLink())
                                .withString(Constants.INTENT_TITLE, articleInfo.getTitle())
                                .navigation();
                    }
                }
            }
        });
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
            Log.e("SystemArticleViewModel", "isDetached:" + articleInfos.isDetached() + " isImmutable:" + articleInfos.isImmutable());
            Log.e("SystemArticleViewModel", "size:" + systemArticleAdapter.getCurrentList().size() + " articleInfos:" + articleInfos.size());
            if (binding.systemSmartRefresh != null && binding.systemSmartRefresh.getState() == RefreshState.Refreshing) {
                binding.systemSmartRefresh.finishRefresh();
            }
        }
    };
}
