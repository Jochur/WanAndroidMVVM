package com.grechur.entry.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.Children;
import com.grechur.entry.model.system.SystemModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/12 11:43
 */
public class SystemViewModel extends BaseViewModel {
    private SystemModel systemModel;

    public MutableLiveData<List<Children>> mData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    public List<Children> mLeftData;

    public SystemViewModel() {
        systemModel = new SystemModel(mData,mError,null);
        systemModel.systemTree();

        mLeftData = new ArrayList<>();
    }

    @Override
    protected void create() {
        super.create();

    }
}
