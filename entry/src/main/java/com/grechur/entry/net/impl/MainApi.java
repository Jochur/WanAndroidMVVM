package com.grechur.entry.net.impl;

import com.grechur.common.DefaultHttpService;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.net.IMainService;
import com.grechur.net.BaseTransformer;

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
}
