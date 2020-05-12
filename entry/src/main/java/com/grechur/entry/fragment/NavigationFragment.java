package com.grechur.entry.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.databinding.EntryFragmentNavigationBinding;
import com.grechur.entry.viewmodel.NavigationViewModel;
import com.grechur.net.ApiException;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationFragment
 * @Description: 导航fragment
 * @Author: Grechur
 * @CreateDate: 2020/5/12 18:03
 */
public class NavigationFragment extends BaseFragment<NavigationViewModel, EntryFragmentNavigationBinding> implements TabLayout.OnTabSelectedListener {

    private List<Fragment> mFragments;


    @Override
    protected void initView(Bundle savedInstanceState) {

        binding.navigationTabLayout.addOnTabSelectedListener(this);

        binding.naviViewPage.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.naviViewPage.setAdapter(new FragmentStateAdapter(this) {
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
        });
        binding.naviViewPage.setOffscreenPageLimit(1);
        new TabLayoutMediator(binding.navigationTabLayout, binding.naviViewPage, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            }
        });


        viewModel.mData.observe(this, new Observer<List<NavigationInfo>>() {
            @Override
            public void onChanged(List<NavigationInfo> navigationInfos) {

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
        return R.layout.entry_fragment_navigation;
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
