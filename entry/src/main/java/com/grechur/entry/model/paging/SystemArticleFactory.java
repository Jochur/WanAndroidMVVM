package com.grechur.entry.model.paging;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemArticleFactory
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/15 15:06
 */
public class SystemArticleFactory extends DataSource.Factory {
    MutableLiveData<SystemArticleSource> source = new MutableLiveData<>();
    private int pageNum;
    private int cid;

    public SystemArticleFactory(int pageNum, int cid) {
        this.pageNum = pageNum;
        this.cid = cid;
    }

    @NonNull
    @Override
    public SystemArticleSource create() {
        if(source == null || source.getValue()==null || source.getValue().isInvalid()) {
            SystemArticleSource articleSource = new SystemArticleSource(pageNum, cid);
            source.postValue(articleSource);
            return articleSource;
        }else{
            return source.getValue();
        }
    }
}
