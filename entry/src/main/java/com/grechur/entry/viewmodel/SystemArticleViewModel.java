package com.grechur.entry.viewmodel;

import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.model.system.SystemModel;
import com.grechur.net.ApiException;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemArticleViewModel
 * @Description: 体系文章
 * @Author: Grechur
 * @CreateDate: 2020/5/12 16:53
 */
public class SystemArticleViewModel extends BaseViewModel {

    private SystemModel systemModel;

    public MutableLiveData<ApiException> mError = new MutableLiveData<>();
    public MutableLiveData<List<ArticleInfo>> mArticleData = new MutableLiveData<>();

    public MutableLiveData<Boolean> totalPage = new MutableLiveData<>();

    public SystemArticleViewModel() {
        systemModel = new SystemModel(null,mError,mArticleData);
        systemModel.setTotalPage(totalPage);
    }

    @Override
    protected void create() {
        super.create();
    }

    public void getSystemArticle(int pageNum, int cid){
        systemModel.systemArticle(pageNum,cid);
    }


}
