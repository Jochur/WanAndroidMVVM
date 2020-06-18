package com.grechur.collect.paging;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.net.CollectApi;
import com.grechur.common.Applications;
import com.grechur.common.base.BaseViewModel;

import java.util.List;
import java.util.Observer;

import io.reactivex.Observable;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConcertViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/12 18:18
 */
public class ConcertViewModel extends BaseViewModel {
    private ConcertDao concertDao;
    public final LiveData<PagedList<Concert>> concertList;
    public ConcertViewModel() {
        this.concertDao = ConcertDatabase.getInstance(Applications.getCurrent()).concertDao();
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .build();
        concertList = new LivePagedListBuilder<>(
                concertDao.concertsByDate(), config)
                .build();
    }
}
