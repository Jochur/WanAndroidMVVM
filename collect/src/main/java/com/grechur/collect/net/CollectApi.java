package com.grechur.collect.net;

import com.grechur.collect.bean.CollectPageInfo;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.common.DefaultHttpService;
import com.grechur.net.BaseTransformer;
import com.grechur.net.HttpService;

import java.util.List;

import io.reactivex.Observable;


/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectApi
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:43
 */
public class CollectApi {

    private static volatile CollectApi collectApi;
    private CollectService mService;

    public CollectApi() {
        mService = DefaultHttpService.createService(CollectService.class);
    }

    public static CollectApi getInstance(){
        if(collectApi == null){
            synchronized (CollectApi.class){
                if(collectApi == null){
                    collectApi = new CollectApi();
                }
            }
        }
        return collectApi;
    }

    public Observable<CollectPageInfo> collectArticle(int pageNum){
        return mService.collectArticle(pageNum).compose(BaseTransformer.<CollectPageInfo>applyTransform());
    }

    public Observable<List<CollectWebInfo>> collectWeb(){
        return mService.collectWeb().compose(BaseTransformer.<List<CollectWebInfo>>applyTransform());
    }
}
