package com.grechur.entry.model;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.RankInfo;
import com.grechur.entry.bean.RankPageInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 20:06
 */
public class RankModel {
    MutableLiveData<List<RankInfo>> mData;
    MutableLiveData<ApiException> mError;
    MutableLiveData<Boolean> mCanLoad ;
    public RankModel(MutableLiveData<List<RankInfo>> mData, MutableLiveData<ApiException> mError, MutableLiveData<Boolean> mCanLoad) {
        this.mCanLoad = mCanLoad;
        this.mError = mError;
        this.mData = mData;
    }

    public void rank(int pageNum){
        MainApi.getInstance()
                .rank(pageNum)
                .subscribe(new BaseSubscriber<RankPageInfo>() {
                    @Override
                    public void onNext(RankPageInfo rankPageInfo) {
                        if(rankPageInfo!=null){
                            if(rankPageInfo.getCurPage()>=rankPageInfo.getPageCount()){
                                mCanLoad.setValue(false);
                            }
                            if(rankPageInfo.getDatas()!=null&&!rankPageInfo.getDatas().isEmpty()){
                                List<RankInfo> value = mData.getValue();
                                value.addAll(rankPageInfo.getDatas());
                                mData.setValue(value);
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }
}
