package com.grechur.entry.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import com.grechur.common.arouter.RouteServiceManager;
import com.grechur.common.arouter.news.IHomeService;
import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.HomeService;
import com.grechur.entry.fragment.HomeFragment;
import com.grechur.entry.fragment.MineFragment;
import com.grechur.entry.fragment.NavigationFragment;
import com.grechur.entry.fragment.ProjectFragment;
import com.grechur.entry.fragment.SencondFragment;
import com.grechur.entry.fragment.SystemFragment;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: MainViewModel
 * @Description: 主页面的viewmodel
 * @Author: Grechur
 * @CreateDate: 2020/5/8 15:34
 */
public class MainViewModel extends BaseViewModel {
    public Fragment currentFragment;
    public HomeFragment homeFragment;
    public SystemFragment systemFragment;
    public NavigationFragment navigationFragment;
    public ProjectFragment projectFragment;
    public MineFragment mineFragment;
    public IHomeService service;
    public int position = 0;

    public MainViewModel() {

        homeFragment = new HomeFragment();
        systemFragment = new SystemFragment();
        navigationFragment = new NavigationFragment();
        projectFragment = new ProjectFragment();
        mineFragment = new MineFragment();
        currentFragment = homeFragment;
    }

    @Override
    protected void create() {
        super.create();

    }
}
