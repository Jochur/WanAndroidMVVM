package com.grechur.entry;

import com.grechur.common.base.BaseActivity;
import com.grechur.entry.databinding.EntryActivitySearchBinding;
import com.grechur.entry.viewmodel.SearchViewModel;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchActivity
 * @Description: 搜索activity
 * @Author: Grechur
 * @CreateDate: 2020/5/18 20:07
 */
public class SearchActivity extends BaseActivity<SearchViewModel, EntryActivitySearchBinding> {
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_search;
    }
}
