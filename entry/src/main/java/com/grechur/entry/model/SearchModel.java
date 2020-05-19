package com.grechur.entry.model;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.reflect.TypeToken;
import com.grechur.common.Applications;
import com.grechur.common.util.GsonUtils;
import com.grechur.common.util.SpUtils;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.bean.HotBean;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 11:54
 */
public class SearchModel {

    private MutableLiveData<List<SearchBean>> mSearch;
    private MutableLiveData<ApiException> mError;
    private MutableLiveData<List<ArticleInfo>> mResult;
    private MutableLiveData<Boolean> canLoad;

    public SearchModel(MutableLiveData<List<SearchBean>> mSearch, MutableLiveData<ApiException> mError) {
        this.mSearch = mSearch;
        this.mError = mError;
    }

    public void setResult(MutableLiveData<List<ArticleInfo>> mResult) {
        this.mResult = mResult;
    }

    public void getHistory(){
        String  json = SpUtils.getString(Applications.getCurrent(), "search_his", "");
        if(!TextUtils.isEmpty(json)) {
            List<String> history = GsonUtils.changeGsonToList(json);
            if (history != null && !history.isEmpty()) {
                List<SearchBean> searchValue = mSearch.getValue();
                SearchBean searchBean = new SearchBean();
                searchBean.setTitle("历史记录");
                SearchBean.SearchItem item = new SearchBean.SearchItem();
                item.setHistory(history);
                searchBean.setItem(item);
                searchValue.add(0,searchBean);

                mSearch.setValue(searchValue);
            }
        }
    }

    public void getHotKey(){
        MainApi.getInstance()
                .hotKey()
                .subscribe(new BaseSubscriber<List<HotBean>>() {
                    @Override
                    public void onNext(List<HotBean> articleInfos) {
                        if ( articleInfos!=null && !articleInfos.isEmpty() ) {
                            List<SearchBean> searchValue = mSearch.getValue();
                            SearchBean searchBean = new SearchBean();
                            searchBean.setTitle("热门搜索");
                            SearchBean.SearchItem item = new SearchBean.SearchItem();
                            item.setHot(articleInfos);
                            searchBean.setItem(item);
                            searchValue.add(searchBean);

                            mSearch.setValue(searchValue);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }

    public void getSearchResult(int pageNum,String key){
        MainApi.getInstance()
                .queryArticle(pageNum, key)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){
                            if(homePageInfo.getCurPage()>=homePageInfo.getPageCount()){
                                canLoad.setValue(false);
                            }
                            List<ArticleInfo> articleInfos = homePageInfo.getDatas();
                            if(articleInfos!=null&&!articleInfos.isEmpty()){
                                List<ArticleInfo> value = mResult.getValue();
                                value.addAll(articleInfos);
                                mResult.setValue(value);
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }

    public void setCanLoad(MutableLiveData<Boolean> canLoad) {
        this.canLoad = canLoad;
    }
}
