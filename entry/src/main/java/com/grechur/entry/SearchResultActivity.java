package com.grechur.entry;

import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseActivity;
import com.grechur.common.listener.OnClickListener;
import com.grechur.common.util.GsonUtils;
import com.grechur.common.util.SpUtils;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.adapter.SearchAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.databinding.EntryActivitySearchBinding;
import com.grechur.entry.databinding.EntryActivitySearchResultBinding;
import com.grechur.entry.viewmodel.SearchResultViewModel;
import com.grechur.entry.viewmodel.SearchViewModel;
import com.grechur.net.ApiException;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchActivity
 * @Description: 搜索activity
 * @Author: Grechur
 * @CreateDate: 2020/5/18 20:07
 */
public class SearchResultActivity extends BaseActivity<SearchResultViewModel, EntryActivitySearchResultBinding> implements OnClickListener {
    private HomeAdapter mAdapter;
    private String key;
    private int pageNum = 0;
    @Override
    protected void initView() {
        key = getIntent().getStringExtra("key");
        if(!TextUtils.isEmpty(key)) {
            viewModel.key.set(key);
        }
        mAdapter = new HomeAdapter(this,viewModel.getSearch());
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSearch.setAdapter(mAdapter);

        viewModel.mResult.observe(this, new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> searchBeans) {
                mAdapter.notifyDataSetChanged();
                finishStatus();
            }
        });
        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
                finishStatus();
            }
        });
        viewModel.canLoad.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(binding.searchSmartRefresh.getState() == RefreshState.Loading){
                    binding.searchSmartRefresh.finishLoadMore();
                }
                binding.searchSmartRefresh.setNoMoreData(true);
            }
        });
        binding.searchSmartRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                viewModel.getSearchResult(pageNum,key);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                viewModel.getSearchResult(pageNum,key);
            }
        });
        viewModel.getSearchResult(pageNum,key);
        binding.setListener(this);
    }

    public void finishStatus(){
        if(binding.searchSmartRefresh.getState() == RefreshState.Refreshing){
            binding.searchSmartRefresh.finishRefresh();
        }
        if(binding.searchSmartRefresh.getState() == RefreshState.Loading){
            binding.searchSmartRefresh.finishLoadMore();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_search_result;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_search_back) {
            onBackPressed();
        } else if (id == R.id.tv_search_start){
            key = viewModel.key.get();
            if(!TextUtils.isEmpty(key)){
                String  json = SpUtils.getString(this, "search_his", "");
                if(!TextUtils.isEmpty(json)) {
                    List<String> history = GsonUtils.changeGsonToList(json);
                    if (history != null && !history.isEmpty()) {
                        if(!history.contains(key)){
                            history.add(0,key);
                            SpUtils.saveString(this,"search_his",GsonUtils.createArrayToString(history));
                        }
                    }
                }
                pageNum =0;
                viewModel.getSearchResult(pageNum,key);
            }else{
                ToastUtils.show("搜索词不能为空");
            }
        }
    }
}
