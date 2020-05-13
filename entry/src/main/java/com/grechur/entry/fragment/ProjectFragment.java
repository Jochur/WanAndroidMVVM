package com.grechur.entry.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.grechur.common.base.BaseFragment;
import com.grechur.entry.R;
import com.grechur.entry.bean.Children;
import com.grechur.entry.databinding.EntryFragmentProjectBinding;
import com.grechur.entry.viewmodel.ProjectViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectFragment
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 11:23
 */
public class ProjectFragment extends BaseFragment<ProjectViewModel, EntryFragmentProjectBinding> implements TabLayout.OnTabSelectedListener{
    private List<Fragment> mFragments;

    private FragmentStateAdapter mAdapter;

    private List<Children> mData;
    @Override
    protected void initView(Bundle savedInstanceState) {
        mFragments = new ArrayList<>();
        mData = new ArrayList<>();
        mAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                Fragment fragment = mFragments.get(position);
                return fragment;
            }

            @Override
            public int getItemCount() {
                return mFragments.size();
            }
        };


        binding.projectViewPage.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.projectViewPage.setAdapter(mAdapter);
        binding.projectViewPage.setOffscreenPageLimit(1);

        binding.projectTabLayout.setTabTextColors(Color.WHITE,Color.WHITE);
        binding.projectTabLayout.addOnTabSelectedListener(this);

        new TabLayoutMediator(binding.projectTabLayout, binding.projectViewPage, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(mData!=null&&!mData.isEmpty()){
                    tab.setText(mData.get(position).getName());
                }
            }
        }).attach();

        viewModel.mTreeData.observe(this, new Observer<List<Children>>() {
            @Override
            public void onChanged(List<Children> children) {
                if(children!=null&&!children.isEmpty()){
                    mData.addAll(children);
                    for (Children mDatum : mData) {
                        ProjectArticleFragment fragment = new ProjectArticleFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("cid",mDatum.getId());
                        fragment.setArguments(bundle);
                        mFragments.add(fragment);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_project;
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
}
