package com.grechur.entry.viewmodel;

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
public class SearchViewModel extends BaseViewModel {
    public ObservableField<String> key = new ObservableField<>();

    public MutableLiveData<List<SearchBean>> mSearch = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    private SearchModel searchModel;

    public SearchViewModel() {
        mSearch.setValue(new ArrayList<SearchBean>());
        searchModel = new SearchModel(mSearch,mError);

        searchModel.getHistory();
        searchModel.getHotKey();
    }


    public List<SearchBean> getSearch(){
        return mSearch.getValue();
    }
}
