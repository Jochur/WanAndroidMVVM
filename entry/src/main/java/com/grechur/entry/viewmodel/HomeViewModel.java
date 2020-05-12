package com.grechur.entry.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.model.MainModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: HomeViewModel
 * @Description: 首页的viewmodel
 * @Author: Grechur
 * @CreateDate: 2020/5/9 18:04
 */
public class HomeViewModel extends BaseViewModel {
    public MainModel mainModel;
    public MutableLiveData<List<ArticleInfo>> mLiveData = new MutableLiveData<>();
    public MutableLiveData<ApiException> netError = new MutableLiveData<>();

    public MutableLiveData<List<BannerInfo>> mLiveBanner = new MutableLiveData<>();

    public MutableLiveData<List<ArticleInfo>> mTopData = new MutableLiveData<>();

    public List<ArticleInfo> mData;

    public List<BannerInfo> mBanners;
    public int pageNum = 0;
    public HomeViewModel() {
        Log.e("BaseFragment","HomeViewModel()");
        if(mainModel == null) {
            mainModel = new MainModel(mLiveData, netError, mLiveBanner,mTopData);
            mainModel.banner();
            mainModel.homeArticle(0);
            mainModel.topArticle();
        }
        if(mData == null) {
            mData = new ArrayList<>();
        }else{
            Log.e("BaseFragment"," mData:"+mData.size());
        }
        if(mBanners == null) {
            mBanners = new ArrayList<>();
        }
    }

    @Override
    protected void create() {
        super.create();
        ToastUtils.show("create");

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mainModel.homeArticle(0);
    }

    @Override
    public void onLoad(int pageNum) {
        super.onLoad(pageNum);
        mainModel.homeArticle(pageNum);
    }
}
