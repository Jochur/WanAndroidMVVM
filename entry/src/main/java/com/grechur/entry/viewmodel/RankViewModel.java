package com.grechur.entry.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.RankInfo;
import com.grechur.entry.model.RankModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: RankViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 19:57
 */
public class RankViewModel extends BaseViewModel {

    public MutableLiveData<List<RankInfo>> mData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();
    public MutableLiveData<Boolean> mCanLoad = new MutableLiveData<>();

    private RankModel rankModel;

    public RankViewModel() {
        mData.setValue(new ArrayList<RankInfo>());
        rankModel = new RankModel(mData,mError,mCanLoad);
    }

    public void rank(int pageNum){
        if(pageNum == 1){
            List<RankInfo> value = mData.getValue();
            value.clear();
            mData.setValue(value);
        }
        rankModel.rank(pageNum);
    }

    public List<RankInfo> getRank(){
        return mData.getValue();
    }
}
