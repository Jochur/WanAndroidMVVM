package com.grechur.entry.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.fragment.HomeFragment;
import com.grechur.entry.fragment.SencondFragment;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: MainViewModel
 * @Description: 主页面的viewmodel
 * @Author: Grechur
 * @CreateDate: 2020/5/8 15:34
 */
public class MainViewModel extends BaseViewModel {
    public ObservableBoolean showEmptyView = new ObservableBoolean();
    public ObservableBoolean showReturnView = new ObservableBoolean();
    public ObservableField<String> title = new ObservableField();

    public Fragment currentFragment;
    public HomeFragment homeFragment;
    public SencondFragment homeFragment1;
    public SencondFragment homeFragment2;
    public SencondFragment homeFragment3;
    public SencondFragment homeFragment4;

    public MainViewModel() {
        homeFragment = new HomeFragment();
        homeFragment1 = new SencondFragment();
        homeFragment2 = new SencondFragment();
        homeFragment3 = new SencondFragment();
        homeFragment4 = new SencondFragment();
        currentFragment = homeFragment;
    }

    @Override
    protected void create() {
        super.create();
        showEmptyView.set(true);
        showReturnView.set(false);
        title.set("首页");
    }
}
