package com.grechur.entry.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.model.NavigationModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: NavigationViewModel
 * @Description: 导航vm
 * @Author: Grechur
 * @CreateDate: 2020/5/12 18:07
 */
public class NavigationViewModel extends BaseViewModel {
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();
    public MutableLiveData<List<NavigationInfo>> mData = new MutableLiveData<>();
    private NavigationModel navigationModel;

    public List<NavigationInfo> mNavData;

    public NavigationViewModel() {
        mNavData = new ArrayList<>();
        navigationModel = new NavigationModel(mData,mError);
        navigationModel.navigation();
    }
}
