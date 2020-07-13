package com.grechur.collect.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.model.CollectModel;
import com.grechur.common.base.BaseViewModel;
import com.grechur.net.ApiException;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:20
 */
public class CollectWebViewModel extends BaseViewModel {
    CollectModel collectModel;

    public MutableLiveData<List<CollectWebInfo>> mWebData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    public MutableLiveData<CollectWebInfo> mWebInfo = new MutableLiveData<>();

    public MutableLiveData<Boolean> delete = new MutableLiveData<>();

    public CollectWebViewModel(@NonNull Application application) {
        super(application);
        collectModel = new CollectModel(null,mError,mWebData);
        collectModel.setWebInfo(mWebInfo);
        collectModel.setDelete(delete);
        collectModel.collectWeb();
    }

    public void updateWeb(Map<String,Object> map){
        collectModel.updateWeb(map);
    }

    public void deletWeb(int id){
        collectModel.deleteWeb(id);
    }
}
