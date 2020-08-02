package com.grechur.entry.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.model.SearchModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/18 20:08
 */
public class SearchResultViewModel extends BaseViewModel {
    public ObservableField<String> key = new ObservableField<>();

    public MutableLiveData<List<ArticleInfo>> mResult = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    public MutableLiveData<Boolean> canLoad = new MutableLiveData<>();

    private SearchModel searchModel;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
        mResult.setValue(new ArrayList<ArticleInfo>());
        searchModel = new SearchModel(null,mError);
        searchModel.setResult(mResult);
        searchModel.setCanLoad(canLoad);
    }

    public void getSearchResult(int num,String key){
        if(num == 0){
            List<ArticleInfo> value = mResult.getValue();
            value.clear();
            mResult.setValue(value);
        }
        searchModel.getSearchResult(num, key);
    }


    public List<ArticleInfo> getSearch(){
        return mResult.getValue();
    }
}
