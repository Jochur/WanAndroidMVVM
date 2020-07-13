package com.grechur.entry.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.model.ProjectModel;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 11:21
 */
public class ProjectViewModel extends BaseViewModel {

    ProjectModel projectModel;
    public List<Children> mData;

    public MutableLiveData<List<Children>> mTreeData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        mData = new ArrayList<>();
        projectModel = new ProjectModel(mTreeData,mError,null);
        projectModel.projectTree();
    }
}
