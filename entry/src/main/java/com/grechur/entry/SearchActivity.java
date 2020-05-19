package com.grechur.entry;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseActivity;
import com.grechur.common.listener.OnClickListener;
import com.grechur.common.util.GsonUtils;
import com.grechur.common.util.SpUtils;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.adapter.SearchAdapter;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.databinding.EntryActivitySearchBinding;
import com.grechur.entry.viewmodel.SearchViewModel;
import com.grechur.net.ApiException;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchActivity
 * @Description: 搜索activity
 * @Author: Grechur
 * @CreateDate: 2020/5/18 20:07
 */
public class SearchActivity extends BaseActivity<SearchViewModel, EntryActivitySearchBinding> implements OnClickListener {
    private SearchAdapter mAdapter;
    @Override
    protected void initView() {
        mAdapter = new SearchAdapter(this,viewModel.getSearch());
        binding.rvSearch.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSearch.setAdapter(mAdapter);

        viewModel.mSearch.observe(this, new Observer<List<SearchBean>>() {
            @Override
            public void onChanged(List<SearchBean> searchBeans) {
                mAdapter.notifyDataSetChanged();
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
        binding.setListener(this);
        binding.searchSmartRefresh.setOnRefreshLoadMoreListener(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_search;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_search_back) {
            onBackPressed();
        } else if (id == R.id.tv_search_start){
            String key = viewModel.key.get();
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
                Intent intent = new Intent();
                intent.setClass(this,SearchResultActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
            }else{
                ToastUtils.show("搜索词不能为空");
            }
        }
    }
}
