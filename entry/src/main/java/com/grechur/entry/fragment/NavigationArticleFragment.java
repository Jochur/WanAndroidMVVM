package com.grechur.entry.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.grechur.common.base.BaseFragment;
import com.grechur.entry.R;
import com.grechur.entry.adapter.HomeAdapter;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.databinding.EntryFragmentNavArticleBinding;
import com.grechur.entry.viewmodel.NavigationArticleViewModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationArticleFragment
 * @Description: 导航文章列表
 * @Author: Grechur
 * @CreateDate: 2020/5/13 9:42
 */
public class NavigationArticleFragment extends BaseFragment<NavigationArticleViewModel, EntryFragmentNavArticleBinding> {
    private HomeAdapter mAdapter;
    private List<ArticleInfo> mData;
    @Override
    protected void initView(Bundle savedInstanceState) {
        mData = new ArrayList<>();

        getData();

        mAdapter = new HomeAdapter(getContext(),mData);
        binding.navArtRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.navArtRecycleView.setAdapter(mAdapter);
    }

    private void getData() {
        Bundle arguments = getArguments();

        if (arguments != null) {
            String json = arguments.getString("articles");
            Gson gson = new Gson();
            Type type = new TypeToken<List<ArticleInfo>>() {
            }.getType();
            List<ArticleInfo> list = gson.fromJson(json,type);
            if(list!=null&&!list.isEmpty()) {
                mData.addAll(list);
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_nav_article;
    }
}
