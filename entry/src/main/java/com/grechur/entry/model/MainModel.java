package com.grechur.entry.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MainModel
 * @Description: 主页面的model
 * @Author: Grechur
 * @CreateDate: 2020/5/9 17:56
 */
public class MainModel implements IMainModel{
    public MutableLiveData<List<ArticleInfo>> mData;
    public MutableLiveData<ApiException> netError;

    public MutableLiveData<List<BannerInfo>> mBanner;



    public MainModel(MutableLiveData<List<ArticleInfo>> mData, MutableLiveData<ApiException> netError, MutableLiveData<List<BannerInfo>> mBanner) {
        this.mData = mData;
        this.netError = netError;
        this.mBanner = mBanner;
    }

    @Override
    public void homeArticle(final int pageNum) {
        MainApi.getInstance().homeArticle(pageNum)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){

                            if(homePageInfo.getDatas()!=null&&!homePageInfo.getDatas().isEmpty()){
                                Log.e("BaseFragment","pageNum:"+pageNum);
//                                if(pageNum == 0){
//                                    mData.setValue(new ArrayList<ArticleInfo>());
//                                }
//                                mData.setValue(homePageInfo.getDatas());
                                List<ArticleInfo> value = mData.getValue();
                                value.addAll(homePageInfo.getDatas());
                                mData.setValue(value);
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        netError.setValue(e);
                    }
                });
    }

    @Override
    public void banner() {
        MainApi.getInstance().banner()
                .subscribe(new BaseSubscriber<List<BannerInfo>>() {
                    @Override
                    public void onNext(List<BannerInfo> bannerInfos) {
                        if(bannerInfos!=null&&!bannerInfos.isEmpty()){
//                            mBanner.setValue(bannerInfos);
                            List<BannerInfo> bannerInfoList = mBanner.getValue();
                            bannerInfoList.addAll(bannerInfos);
                            mBanner.setValue(bannerInfoList);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {

                    }
                });
    }

    @Override
    public void topArticle() {
        MainApi.getInstance().topArticle()
                .subscribe(new BaseSubscriber<List<ArticleInfo>>() {
                    @Override
                    public void onNext(List<ArticleInfo> articleInfos) {
                        if(articleInfos!=null&&!articleInfos.isEmpty()){
//                            mTopData.setValue(articleInfos);
                            List<ArticleInfo> value = mData.getValue();
                            value.addAll(0,articleInfos);
                            mData.setValue(value);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {

                    }
                });
    }


}
