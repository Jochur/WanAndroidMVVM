package com.grechur.entry.net.impl;

import com.grechur.common.DefaultHttpService;
import com.grechur.common.base.BaseFragment;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.bean.HotBean;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.bean.RankPageInfo;
import com.grechur.entry.net.IMainService;
import com.grechur.net.BaseTransformer;
import com.grechur.net.Empty;

import java.util.List;

import io.reactivex.Observable;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: UserApi
 * @Description: 接口实现类
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:37
 */
public class MainApi {
    private static volatile MainApi mainApi;
    private IMainService mainService;

    private MainApi(){
        mainService = DefaultHttpService.createService(IMainService.class);
    }

    public static MainApi getInstance() {
        if(mainApi == null){
            synchronized (MainApi.class){
                if(mainApi == null){
                    mainApi = new MainApi();
                }
            }
        }
        return mainApi;
    }

    public Observable<HomePageInfo> homeArticle(int pageNum){
        return mainService.homeArticle(pageNum).compose(BaseTransformer.<HomePageInfo>applyTransform());
    }

    public Observable<List<BannerInfo>> banner(){
        return mainService.banner().compose(BaseTransformer.<List<BannerInfo>>applyTransform());
    }

    public Observable<List<ArticleInfo>> topArticle(){
        return mainService.topArticle().compose(BaseTransformer.<List<ArticleInfo>>applyTransform());
    }

    public Observable<List<Children>> systemTree(){
        return mainService.systemTree().compose(BaseTransformer.<List<Children>>applyTransform());
    }

    public Observable<HomePageInfo> systemArticle(int pageNum,int cid){
        return mainService.systemArticle(pageNum, cid).compose(BaseTransformer.<HomePageInfo>applyTransform());
    }

    public Observable<List<NavigationInfo>> navigation(){
        return mainService.navigation().compose(BaseTransformer.<List<NavigationInfo>>applyTransform());
    }

    public Observable<List<Children>> projectTree(){
        return mainService.projectTree().compose(BaseTransformer.<List<Children>>applyTransform());
    }

    public Observable<HomePageInfo> projectArticle(int pageNum,int cid){
        return mainService.projectArticle(pageNum, cid).compose(BaseTransformer.<HomePageInfo>applyTransform());
    }

    public Observable<Empty> collectArticle(int id){
        return mainService.collectArticle(id).compose(BaseTransformer.<Empty>applyTransform());
    }

    public Observable<List<HotBean>> hotKey(){
        return mainService.hotKey().compose(BaseTransformer.<List<HotBean>>applyTransform());
    }

    public Observable<HomePageInfo> queryArticle(int pageNum, String key){
        return mainService.queryArticle(pageNum, key).compose(BaseTransformer.<HomePageInfo>applyTransform());
    }

    public Observable<RankPageInfo> rank(int pageNum){
        return mainService.rank(pageNum).compose(BaseTransformer.<RankPageInfo>applyTransform());
    }
}
