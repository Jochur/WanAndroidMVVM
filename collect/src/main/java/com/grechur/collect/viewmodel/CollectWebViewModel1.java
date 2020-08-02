package com.grechur.collect.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.model.paging.WebFactory;
import com.grechur.common.base.BaseViewModel;


/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:20
 */
public class CollectWebViewModel1 extends BaseViewModel {

    private final LiveData<PagedList<CollectWebInfo>> webList;
    private DataSource<Integer, CollectWebInfo> webDataSource;

    public CollectWebViewModel1(@NonNull Application application) {
        super(application);
        WebFactory webFactory = new WebFactory();
        webDataSource = webFactory.create();
        webList = new LivePagedListBuilder<>(webFactory,10).build();
    }

    public LiveData<PagedList<CollectWebInfo>> getWebList() {
        return webList;
    }
}
