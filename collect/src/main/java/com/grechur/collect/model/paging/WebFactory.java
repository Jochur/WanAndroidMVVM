package com.grechur.collect.model.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.grechur.collect.bean.CollectWebInfo;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConcertFactory
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 16:35
 */
public class WebFactory extends DataSource.Factory<Integer, CollectWebInfo> {
    private MutableLiveData<WebDataSource> webLiveData =  new MutableLiveData<>();
    @NonNull
    @Override
    public DataSource<Integer, CollectWebInfo> create() {
        WebDataSource webDataSource = new WebDataSource();
        webLiveData.postValue(webDataSource);
        return webDataSource;
    }
}
