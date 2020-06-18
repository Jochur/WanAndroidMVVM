package com.grechur.entry.model.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemArticleSource
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/15 15:07
 */
public class SystemArticleSource extends PageKeyedDataSource<Integer, ArticleInfo> {

    private MainApi api;
    private int pageNum;
    private int cid;

    public SystemArticleSource(int pageNum, int cid) {
        this.pageNum = pageNum;
        this.cid = cid;
        if(api == null){
            api = MainApi.getInstance();
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, ArticleInfo> callback) {
        Log.e("SystemArticleViewModel","loadInitial pageNum:"+pageNum);
        Log.e("SystemArticleViewModel","loadInitial params:"+params.requestedLoadSize+" "+params.placeholdersEnabled);
        api.systemArticle(pageNum,cid)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){
                            if(homePageInfo.getDatas()!=null){
                                callback.onResult(homePageInfo.getDatas(),pageNum,pageNum+1);
                            }
                        }

                    }

                    @Override
                    public void onError(ApiException e) {
                        if(e!=null)
                        ToastUtils.show(e.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ArticleInfo> callback) {
        Log.e("SystemArticleViewModel","loadBefore pageNum:"+pageNum);
        Log.e("SystemArticleViewModel","loadBefore params:"+params.requestedLoadSize+" "+params.key);
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, ArticleInfo> callback) {
        Log.e("SystemArticleViewModel","loadAfter pageNum:"+pageNum);
        Log.e("SystemArticleViewModel","loadAfter params:"+params.requestedLoadSize+" "+params.key);
        api.systemArticle(params.key,cid)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){
                            if(homePageInfo.getDatas()!=null){
                                callback.onResult(homePageInfo.getDatas(),params.key+1);
                            }
                        }

                    }

                    @Override
                    public void onError(ApiException e) {
                        if(e!=null)
                            ToastUtils.show(e.getMessage());
                    }
                });
    }
}
