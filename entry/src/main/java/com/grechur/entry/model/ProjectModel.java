package com.grechur.entry.model;

import androidx.lifecycle.MutableLiveData;

import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.net.impl.MainApi;
import com.grechur.net.ApiException;
import com.grechur.net.BaseSubscriber;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ProjectModel
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/13 11:26
 */
public class ProjectModel {
    private MutableLiveData<List<Children>> mTreeData;
    private MutableLiveData<ApiException> mError;
    private MutableLiveData<List<ArticleInfo>> mProjectData;
    private MutableLiveData<Boolean> mTotalPage;

    public ProjectModel(MutableLiveData<List<Children>> mTreeData, MutableLiveData<ApiException> mError, MutableLiveData<List<ArticleInfo>> mProjectData) {
        this.mTreeData = mTreeData;
        this.mError = mError;
        this.mProjectData = mProjectData;
    }

    public void setTotalPage(MutableLiveData<Boolean> totalPage) {
        this.mTotalPage = totalPage;
    }

    public void projectTree(){
        MainApi.getInstance().projectTree()
                .subscribe(new BaseSubscriber<List<Children>>() {
                    @Override
                    public void onNext(List<Children> children) {
                        if(children!=null&&!children.isEmpty()){
                            mTreeData.setValue(children);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        if(e!=null){
                            mError.setValue(e);
                        }
                    }
                });
    }

    public void projectArticle(int pageNum,int cid){
        MainApi.getInstance().projectArticle(pageNum, cid)
                .subscribe(new BaseSubscriber<HomePageInfo>() {
                    @Override
                    public void onNext(HomePageInfo homePageInfo) {
                        if(homePageInfo!=null){
                            if(homePageInfo.getCurPage() == homePageInfo.getPageCount()){
                                mTotalPage.setValue(false);
                            }
                            if(homePageInfo.getDatas()!=null&&!homePageInfo.getDatas().isEmpty()){
                                List<ArticleInfo> value = mProjectData.getValue();
                                value.addAll(homePageInfo.getDatas());
                                mProjectData.setValue(value);
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        mError.setValue(e);
                    }
                });
    }


}
