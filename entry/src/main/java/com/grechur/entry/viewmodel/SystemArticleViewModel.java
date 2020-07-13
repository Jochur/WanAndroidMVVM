package com.grechur.entry.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.model.paging.SystemArticleFactory;
import com.grechur.entry.model.paging.SystemArticleSource;
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

    private LiveData<PagedList<ArticleInfo>> mData;
    private SystemArticleFactory systemArticleFactory;

    public SystemArticleViewModel(@NonNull Application application) {
        super(application);
    }


    public void getData(int pageNum,int cid){
        if(pageNum == 0) {
            Log.e("SystemArticleViewModel", "pageNum:" + pageNum);
            systemArticleFactory = new SystemArticleFactory(pageNum, cid);
            PagedList.Config config = new PagedList.Config.Builder()
                    .setPageSize(20)  //分页大小
                    .setInitialLoadSizeHint(20)  //首次加载大小
                    .setPrefetchDistance(5)  //预加载距离：还剩10个就要滑到底了，就进行预加载
                    .build();
            mData = new LivePagedListBuilder<>(systemArticleFactory, config).build();
        }
    }


    public LiveData<PagedList<ArticleInfo>> getPageData() {
        return mData;
    }

}
