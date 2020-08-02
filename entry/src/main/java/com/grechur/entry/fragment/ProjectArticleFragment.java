package com.grechur.entry.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseFragment;
import com.grechur.common.callback.EmptyCallback;
import com.grechur.common.callback.ErrorCallback;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.adapter.ProjectArticleAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.databinding.EntryFragmentProjectArticleBinding;
import com.grechur.entry.viewmodel.ProjectViewArticleViewModel;
import com.grechur.net.ApiException;
import com.grechur.net.Empty;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectArticleFragment
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 15:43
 */
public class ProjectArticleFragment extends BaseFragment<ProjectViewArticleViewModel, EntryFragmentProjectArticleBinding> {

    private ProjectArticleAdapter mAdapter;
    private int pageNum = 1;
    private int cid;
    @Override
    protected void initView(Bundle savedInstanceState) {

        getIntentData();

        mAdapter = new ProjectArticleAdapter(getContext(),viewModel.getProjects());
        binding.projectRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.projectRecycleView.setAdapter(mAdapter);

        binding.projectSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                viewModel.projectArticle(pageNum,cid);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                viewModel.projectArticle(pageNum,cid);
            }
        });

        viewModel.mProjectData.observe(this, new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                loadService.showSuccess();
                if(articleInfos!=null&&!articleInfos.isEmpty()){
                    mAdapter.notifyDataSetChanged();
                }else{
                    loadService.showCallback(EmptyCallback.class);
                }
                if(binding.projectSmartRefresh.getState()== RefreshState.Refreshing) {
                    binding.projectSmartRefresh.finishRefresh();
                }
                if(binding.projectSmartRefresh.getState()== RefreshState.Loading) {
                    binding.projectSmartRefresh.finishLoadMore();
                }
            }
        });
        viewModel.totalPage.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean canLoad) {
                if(!canLoad){
                    if(binding.projectSmartRefresh.getState()== RefreshState.Loading) {
                        binding.projectSmartRefresh.finishLoadMore();
                    }
                    binding.projectSmartRefresh.setNoMoreData(true);
                }
            }
        });
        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
                loadService.showCallback(ErrorCallback.class);
            }
        });
        viewModel.projectArticle(pageNum,cid);
    }

    private void getIntentData() {
        Bundle bundle = getArguments();
        if(bundle!=null){
            cid = bundle.getInt("cid");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_project_article;
    }
}
