package com.grechur.collect.model;

import androidx.lifecycle.MutableLiveData;

import com.grechur.collect.bean.ArticleInfo;
import com.grechur.collect.bean.CollectPageInfo;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.net.CollectApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:38
 */
public class CollectModel {

    private MutableLiveData<List<ArticleInfo>> mArticleData;
    private MutableLiveData<List<CollectWebInfo>> mWebData;
    private MutableLiveData<ApiException> mError;
    private MutableLiveData<Boolean> canLoad;

    public CollectModel(MutableLiveData<List<ArticleInfo>> mArticleData, MutableLiveData<ApiException> mError, MutableLiveData<List<CollectWebInfo>> mWebData) {
        this.mArticleData = mArticleData;
        this.mError = mError;
        this.mWebData = mWebData;
    }

    public void setCanLoad(MutableLiveData<Boolean> canLoad) {
        this.canLoad = canLoad;
    }

    public void collectArticle(int pageNum){
        CollectApi.getInstance().collectArticle(pageNum)
                .subscribe(new BaseSubscriber<CollectPageInfo>() {
                    @Override
                    public void onNext(CollectPageInfo pageInfo) {
                        if(pageInfo!=null){
                            if(pageInfo.getCurPage()>=pageInfo.getPageCount()){
                                canLoad.setValue(false);
                            }
                            if(pageInfo.getDatas()!=null&&!pageInfo.getDatas().isEmpty()){
                                mArticleData.setValue(pageInfo.getDatas());
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }

    public void collectWeb(){
        CollectApi.getInstance().collectWeb()
                .subscribe(new BaseSubscriber<List<CollectWebInfo>>() {
                    @Override
                    public void onNext(List<CollectWebInfo> collectWebInfos) {
                        if(collectWebInfos!=null&&!collectWebInfos.isEmpty()) {
                            mWebData.setValue(collectWebInfos);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }

}
