package com.grechur.collect.model.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PositionalDataSource;

import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.net.CollectApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: WebDataSource
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 16:32
 */
public class WebDataSource extends PageKeyedDataSource<Integer,CollectWebInfo> {
    protected CollectApi instance;
    public WebDataSource(){
        if(instance == null) {
            instance = CollectApi.getInstance();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback callback) {
        instance.collectWeb()
                .subscribe(new BaseSubscriber<List<CollectWebInfo>>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onNext(List<CollectWebInfo> collectWebInfos) {
                        callback.onResult(collectWebInfos,0,0);
                    }
                });
    }
}
