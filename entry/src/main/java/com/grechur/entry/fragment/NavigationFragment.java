package com.grechur.entry.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.base.BaseReFragment;
import com.grechur.common.util.GsonUtils;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.bean.FragmentBean;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.databinding.EntryFragmentNavigationBinding;
import com.grechur.entry.viewmodel.NavigationViewModel;
import com.grechur.net.ApiException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationFragment
 * @Description: 导航fragment
 * @Author: Grechur
 * @CreateDate: 2020/5/12 18:03
 */
public class NavigationFragment extends BaseReFragment<NavigationViewModel, EntryFragmentNavigationBinding> implements TabLayout.OnTabSelectedListener {

//    private List<Fragment> mFragments;

    private List<FragmentBean> mFragmentBeans;

    private FragmentStateAdapter mAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {

        binding.navigationTabLayout.addOnTabSelectedListener(this);

//        mFragments = new ArrayList<>();
        mFragmentBeans = new ArrayList<>();

        mAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
//                Fragment fragment = mFragments.get(position);
                Class fragmentClass = mFragmentBeans.get(position).fragmentClass;
                Fragment fragment = null;
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    fragment.setArguments(mFragmentBeans.get(position).bundle);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (java.lang.InstantiationException e) {
                    e.printStackTrace();
                }
                return fragment;
            }

            @Override
            public int getItemCount() {
                return mFragmentBeans.size();
            }
        };

        binding.naviViewPage.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.naviViewPage.setAdapter(mAdapter);
        binding.naviViewPage.setOffscreenPageLimit(1);



        binding.navigationTabLayout.setTabTextColors(Color.WHITE,Color.WHITE);

        new TabLayoutMediator(binding.navigationTabLayout, binding.naviViewPage, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(viewModel.mNavData!=null&&!viewModel.mNavData.isEmpty()){
                    tab.setText(viewModel.mNavData.get(position).getName());
                }
            }
        }).attach();

        if(!viewModel.mData.hasObservers()) {
            viewModel.mData.observe(this, new Observer<List<NavigationInfo>>() {
                @Override
                public void onChanged(List<NavigationInfo> navigationInfos) {
                    if (navigationInfos != null && !navigationInfos.isEmpty()) {
                        viewModel.mNavData.addAll(navigationInfos);
                        setData(viewModel.mNavData);
                    }
                }
            });
        }else{
            setData(viewModel.mNavData);
        }

        if(!viewModel.mError.hasObservers()) {

            viewModel.mError.observe(this, new Observer<ApiException>() {
                @Override
                public void onChanged(ApiException e) {
                    if (e != null) {
                        ToastUtils.show(e.getMessage());
                    }
                }
            });
        }
    }


    private void setData(List<NavigationInfo> data){
        for (NavigationInfo mNavDatum : data) {
//            NavigationArticleFragment fragment = new NavigationArticleFragment();
            if (mNavDatum.getArticles() != null && !mNavDatum.getArticles().isEmpty()) {
                String articles = GsonUtils.createArrayToString(mNavDatum.getArticles());
                Bundle bundle = new Bundle();
                bundle.putString("articles", articles);
//                fragment.setArguments(bundle);
//                mFragments.add(fragment);
                FragmentBean fragmentBean = new FragmentBean();
                fragmentBean.bundle = bundle;
                fragmentBean.fragmentClass = NavigationArticleFragment.class;
                mFragmentBeans.add(fragmentBean);
            }

        }
        mAdapter.notifyDataSetChanged();
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


    /**
     * 重设tab宽度
     */
    private void resetTabParams() {
        int mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        LinearLayout tabStrip = getTabStrip(binding.navigationTabLayout);
        if (tabStrip == null) {
            return;
        }

        if(tabStrip.getChildCount()<=4){
            return;
        }
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            LinearLayout tabView = (LinearLayout) tabStrip.getChildAt(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (mScreenWidth / 4), LinearLayout.LayoutParams
                    .WRAP_CONTENT);
            tabView.setLayoutParams(params);
            //tab中的图标可以超出父容器
            tabView.setClipChildren(false);
            tabView.setClipToPadding(false);
        }
    }

    public LinearLayout getTabStrip(TabLayout mTablayout) {
        Class<?> tabLayout = TabLayout.class;
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        if (null == tabStrip)
            return null;

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(mTablayout);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (null == llTab)
            return null;

        llTab.setClipChildren(false);
        return llTab;
    }
}
