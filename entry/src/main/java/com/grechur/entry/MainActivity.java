package com.grechur.entry;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grechur.common.ToolBar;
import com.grechur.common.base.BaseActivity;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.databinding.EntryActivityMainBinding;
import com.grechur.entry.fragment.HomeFragment;
import com.grechur.entry.fragment.MineFragment;
import com.grechur.entry.fragment.NavigationFragment;
import com.grechur.entry.fragment.ProjectFragment;
import com.grechur.entry.fragment.SencondFragment;
import com.grechur.entry.fragment.SystemFragment;
import com.grechur.entry.viewmodel.MainViewModel;


public class MainActivity extends BaseActivity<MainViewModel,EntryActivityMainBinding> implements BottomNavigationBar.OnTabSelectedListener{



    ToolBar toolBar ;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.bottomBar.addItem(new BottomNavigationItem(R.drawable.main_home_unsel,"首页"))
//                .addItem(new BottomNavigationItem(R.drawable.main_system_unsel,"体系"))
//                .addItem(new BottomNavigationItem(R.drawable.main_navigation_unsel,"导航"))
//                .addItem(new BottomNavigationItem(R.drawable.main_project_unsel,"项目"))
//                .addItem(new BottomNavigationItem(R.drawable.main_mine_unsel,"我的"))
//                .setActiveColor(R.color.all_bg)
//                .setMode(BottomNavigationBar.MODE_FIXED)
//                .initialise();
//        homeFragment = new HomeFragment();
//        homeFragment1 = new HomeFragment();
//        homeFragment2 = new HomeFragment();
//        homeFragment3 = new HomeFragment();
//        homeFragment4 = new HomeFragment();
//        currentFragment = homeFragment;
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.container, homeFragment)
//                .show(homeFragment).commit();
//    }

    @Override
    protected void initView() {

        binding.bottomBar.addItem(new BottomNavigationItem(R.drawable.entry_home_unsel, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.entry_system_unsel, "体系"))
                .addItem(new BottomNavigationItem(R.drawable.entry_navigation_unsel, "导航"))
                .addItem(new BottomNavigationItem(R.drawable.entry_project_unsel, "项目"))
                .addItem(new BottomNavigationItem(R.drawable.entry_mine_unsel, "我的"))
                .setActiveColor(R.color.all_bg)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setFirstSelectedPosition(viewModel.position)
                .initialise();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (!viewModel.homeFragment.isAdded()){
            transaction.add(R.id.container, viewModel.homeFragment)
                    .show(viewModel.homeFragment).commit();
            viewModel.currentFragment = viewModel.homeFragment;
        }else {
            showFragment(viewModel.currentFragment);
        }
        binding.bottomBar.setTabSelectedListener(this);

        toolBar = new ToolBar();
        toolBar.setToolTitle("首页");
        toolBar.setShowBack(false);
        toolBar.setShowBar(true);
        toolBar.setShowRight(true);
        toolBar.setRightId(R.drawable.common_search);
        toolBar.setOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.show("去搜索");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        binding.setVariable(BR.tool,toolBar);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_activity_main;
    }


    @Override
    public void onTabSelected(int position) {
        switch (position){
            case 0:
//                viewModel.title.set("首页");
                toolBar.setToolTitle("首页");
                viewModel.position= 0;
                showFragment(viewModel.homeFragment);
                break;
            case 1:
//                viewModel.title.set("体系");
                toolBar.setToolTitle("体系");
                viewModel.position = 1;
                showFragment(viewModel.systemFragment);
                break;
            case 2:
//                viewModel.title.set("导航");
                toolBar.setToolTitle("导航");
                viewModel.position = 2;
                showFragment(viewModel.navigationFragment);
                break;
            case 3:
//                viewModel.title.set("项目");
                toolBar.setToolTitle("项目");
                viewModel.position = 3;
                showFragment(viewModel.projectFragment);
                break;
            case 4:
//                viewModel.title.set("我的");
                toolBar.setToolTitle("我的");
                viewModel.position = 4;
                showFragment(viewModel.mineFragment);
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    /**
     * 展示Fragment
     */
    private void showFragment(Fragment fragment) {
        if (viewModel.currentFragment != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(viewModel.currentFragment);
            viewModel.currentFragment = fragment;
            if (!fragment.isAdded()) {
                transaction.add(R.id.container, fragment).show(fragment).commit();
            } else {
                transaction.show(fragment).commit();
            }
        }
    }
}
//        StringBuffer time = new StringBuffer();
//        time.append("bootTime:"+DevicesTools.bootTime()+"\n");
//        time.append("timeZone:"+DevicesTools.timeZone()+"\n");
//        time.append("languages:"+DevicesTools.languages(this)+"\n");
//        time.append("brightness:"+DevicesTools.brightness(this)+"\n");
//        time.append("batteryStatus:"+DevicesTools.batteryStatus(this)+"\n");
//        time.append("batteryTemp:"+DevicesTools.batteryTemp(this)+"\n");
//        time.append("cpuType:"+DevicesTools.cpuType()+"\n");
//        time.append("basebandVersion:"+DevicesTools.basebandVersion()+"\n");
//        time.append("getLinuxCore:"+DevicesTools.getLinuxCore()+"\n");
//        time.append("getSsid:"+DevicesTools.getSsid(this)+"\n");
//        time.append("getBSsid:"+DevicesTools.getBSsid(this)+"\n");
//        time.append("wifiList:"+DevicesTools.wifiList(this)+"\n");
//        time.append("totalDiskSize:"+DevicesTools.totalDiskSize()+"\n");
//        time.append("availableDiskSize:"+DevicesTools.availableDiskSize()+"\n");
//        time.append("carrierName:"+DevicesTools.carrierName(this)+"\n");
//        time.append("isRooted:"+DevicesTools.isRooted()+"\n");
//        time.append("elapsedRealtime:"+DevicesTools.elapsedRealtime()+"\n");
//        time.append("modelName:"+DevicesTools.modelName()+"\n");
//        time.append("manufacturerName:"+DevicesTools.manufacturerName()+"\n");
//        time.append("systemtVersion:"+DevicesTools.systemtVersion()+"\n");
//        time.append("carrierIpAddress:"+DevicesTools.carrierIpAddress(this)+"\n");
//        time.append("isUsingProxyPort:"+DevicesTools.isUsingProxyPort(this)+"\n");
//        time.append("board:"+DevicesTools.board()+"\n");
//        time.append("batteryPercentage:"+DevicesTools.batteryPercentage(this)+"\n");
//        time.append("nearbyBaseStat:"+DevicesTools.nearbyBaseStat(this)+"\n");
//        time.append("phoneNumber:"+DevicesTools.phoneNumber(this)+"\n");
//        time.append("isSimulatorNew:"+DevicesTools.isSimulatorNew(this)+"\n");
//        time.append("picNum:"+DevicesTools.picNum(this)+"\n");
//        time.append("getRecords:"+DevicesTools.getRecords(getContentResolver())+"\n");
//        time.append("uptimeMillis:"+DevicesTools.uptimeMillis()+"\n");
//        time.append("systemActive:"+DevicesTools.systemActive()+"\n");
//        textView.setText(time.toString());