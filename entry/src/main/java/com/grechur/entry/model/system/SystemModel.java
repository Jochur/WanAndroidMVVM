package com.grechur.entry.model.system;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.model.IMainModel;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemModel
 * @Description: 体系model
 * @Author: Grechur
 * @CreateDate: 2020/5/12 14:39
 */
public class SystemModel{
    private MutableLiveData<List<Children>> mData;
    private MutableLiveData<ApiException> mError;
    private MutableLiveData<List<ArticleInfo>> mArticleData;
    private MutableLiveData<Boolean> totalPage;

    public SystemModel(MutableLiveData<List<Children>> mData, MutableLiveData<ApiException> mError,MutableLiveData<List<ArticleInfo>> mArticleData) {
        this.mData = mData;
        this.mError = mError;
        this.mArticleData = mArticleData;
    }

    public void setTotalPage(MutableLiveData<Boolean> totalPage) {
        this.totalPage = totalPage;
    }

    public void systemTree() {
        MainApi.getInstance().systemTree()
        .subscribe(new BaseSubscriber<List<Children>>() {
            @Override
            public void onNext(List<Children> children) {
                if(children!=null&&!children.isEmpty()){
                    mData.setValue(children);
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

    public void systemArticle(int pageNum,int cid){
        MainApi.getInstance().systemArticle(pageNum, cid)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null) {
                            if(homePageInfo.getPageCount() == homePageInfo.getCurPage()){
                                totalPage.setValue(false);
                            }
                            if (homePageInfo.getDatas() != null && !homePageInfo.getDatas().isEmpty()) {
                                mArticleData.setValue(homePageInfo.getDatas());
                            }
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
