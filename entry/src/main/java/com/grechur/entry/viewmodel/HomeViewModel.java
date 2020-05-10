package com.grechur.entry.viewmodel;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.model.MainModel;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: HomeViewModel
 * @Description: 首页的viewmodel
 * @Author: Grechur
 * @CreateDate: 2020/5/9 18:04
 */
public class HomeViewModel extends BaseViewModel {
    public MainModel mainModel;

    public HomeViewModel() {
        mainModel = new MainModel();
        mainModel.homeArticle(0);
    }

    @Override
    protected void create() {
        super.create();

//        mainModel.banner();



    }
}
