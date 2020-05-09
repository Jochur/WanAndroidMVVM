package com.grechur.entry.viewmodel;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.grechur.common.base.BaseViewModel;

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
    @Override
    protected void create() {
        super.create();
        showEmptyView.set(true);
        showReturnView.set(false);
        title.set("首页");
    }
}
