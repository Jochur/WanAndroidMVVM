package com.grechur.entry.model;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/12 18:14
 */
public class NavigationModel {
    private MutableLiveData<List<NavigationInfo>> mData;
    private MutableLiveData<ApiException> mError;

    public NavigationModel(MutableLiveData<List<NavigationInfo>> mData, MutableLiveData<ApiException> mError) {
        this.mData = mData;
        this.mError = mError;
    }

    public void navigation(){
        MainApi.getInstance().navigation()
            .subscribe(new BaseSubscriber<List<NavigationInfo>>() {
                @Override
                public void onNext(List<NavigationInfo> navigationInfos) {
                    if(navigationInfos!=null&&!navigationInfos.isEmpty()){
                        mData.setValue(navigationInfos);
                    }
                }

                @Override
                public void onError(ApiException e) {
                    if(e!=null){
                        mError.setValue(e);
                    }
                }
            });
    }
}
