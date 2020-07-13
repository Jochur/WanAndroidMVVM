package com.grechur.collect.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.grechur.collect.bean.ArticleInfo;
import com.grechur.collect.model.CollectModel;
import com.grechur.common.base.BaseViewModel;
import com.grechur.net.ApiException;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectArticleViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:34
 */
public class CollectArticleViewModel extends BaseViewModel {

    public MutableLiveData<List<ArticleInfo>> mData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();
    public MutableLiveData<Boolean> canLoad = new MutableLiveData<>();

    CollectModel collectModel;

    public CollectArticleViewModel(@NonNull Application application) {
        super(application);
        collectModel = new CollectModel(mData,mError,null);
        collectModel.setCanLoad(canLoad);

    }

    public void getCollect(int pageNum){
        collectModel.collectArticle(pageNum);
    }
}
