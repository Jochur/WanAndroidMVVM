package com.grechur.entry.model;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MainModel
 * @Description: 主页面的model
 * @Author: Grechur
 * @CreateDate: 2020/5/9 17:56
 */
public class MainModel implements IMainModel{
    public MutableLiveData<List<ArticleInfo>> mData = new MutableLiveData<>();
    public MutableLiveData<ApiException> netError = new MutableLiveData<>();

    public MutableLiveData<List<BannerInfo>> mBanner = new MutableLiveData<>();
    @Override
    public void homeArticle(final int pageNum) {
        MainApi.getInstance().homeArticle(pageNum)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){
                            if(homePageInfo.getDatas()!=null&&!homePageInfo.getDatas().isEmpty()){
                                mData.setValue(homePageInfo.getDatas());
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
                            mBanner.setValue(bannerInfos);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {

                    }
                });
    }
}
