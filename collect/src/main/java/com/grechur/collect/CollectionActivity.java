package com.grechur.collect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.grechur.collect.databinding.CollectActivityCollectionBinding;
import com.grechur.collect.fragment.CollectArticleFragment;
import com.grechur.collect.fragment.CollectWebFragment;
import com.grechur.collect.fragment.CollectWebFragment1;
import com.grechur.collect.paging.Concert;
import com.grechur.collect.paging.ConcertActivity;
import com.grechur.collect.paging.ConcertDao;
import com.grechur.collect.paging.ConcertDatabase;
import com.grechur.collect.viewmodel.CollectViewModel;
import com.grechur.common.ToolBar;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.contant.RouterSchame;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterSchame.COLLECT_ACTIVITY)
public class CollectionActivity extends BaseActivity<CollectViewModel, CollectActivityCollectionBinding> implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    private List<Fragment> mFragments;

    private FragmentStateAdapter mAdapter;

    private List<String> mData;

    @Override
    protected void initView() {
        setData();
        mData = new ArrayList<>();
        mData.add("文章");
        mData.add("网址");

        mFragments = new ArrayList<>();
        CollectArticleFragment articleFragment = new CollectArticleFragment();
        CollectWebFragment1 webFragment = new CollectWebFragment1();
        mFragments.add(articleFragment);
        mFragments.add(webFragment);

        mAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return mFragments.size();
            }
        };

        binding.collectViewPage.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.collectViewPage.setAdapter(mAdapter);
        binding.collectViewPage.setOffscreenPageLimit(1);

        binding.collectTabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
        binding.collectTabLayout.addOnTabSelectedListener(this);

        new TabLayoutMediator(binding.collectTabLayout, binding.collectViewPage, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(mData!=null&&!mData.isEmpty()){
                    tab.setText(mData.get(position));
                }
            }
        }).attach();

        ToolBar toolBar = new ToolBar();
        toolBar.setToolTitle("收藏");
        toolBar.setShowBack(true);
        toolBar.setShowRight(true);
        toolBar.setOnClick(this);
        binding.setTool(toolBar);
    }

    private void setData() {
        ConcertDao concertDao = ConcertDatabase.getInstance(this).concertDao();
        for (int i = 0; i < 10; i++) {
            Concert concert = new Concert();
            concert.setName("张三"+i);
            concert.setPhone("1590666666"+i);
            concert.setDate(System.currentTimeMillis());
            concertDao.insertDate(concert);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.collect_activity_collection;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.iv_back){
            onBackPressed();
        } else if (view.getId() == R.id.iv_right) {
            Intent intent = new Intent();
            intent.setClass(this, ConcertActivity.class);
            startActivity(intent);
        }
    }
}
