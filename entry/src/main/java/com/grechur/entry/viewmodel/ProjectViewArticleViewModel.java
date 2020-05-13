package com.grechur.entry.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.grechur.common.base.BaseViewModel;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.model.ProjectModel;
import com.grechur.net.ApiException;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectViewModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 11:21
 */
public class ProjectViewArticleViewModel extends BaseViewModel {

    ProjectModel projectModel;

    public MutableLiveData<List<ArticleInfo>> mProjectData = new MutableLiveData<>();
    public MutableLiveData<ApiException> mError = new MutableLiveData<>();

    public MutableLiveData<Boolean> totalPage = new MutableLiveData<>();

    public ProjectViewArticleViewModel() {
        projectModel = new ProjectModel(null,mError,mProjectData);
        projectModel.setTotalPage(totalPage);
    }

    public void projectArticle(int pageNum,int cid){
        projectModel.projectArticle(pageNum, cid);
    }
}
