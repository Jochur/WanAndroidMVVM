package com.grechur.collect.net;

import com.grechur.collect.bean.CollectPageInfo;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.net.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectService
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 17:39
 */
public interface CollectService {

    /**
     *收藏文章列表
     * @param pageNum
     * @return
     */
    @GET("/lg/collect/list/{pageNum}/json")
    Observable<BaseResponse<CollectPageInfo>> collectArticle(@Path("pageNum") int pageNum);

    /**
     * 收藏网站列表
     * @return
     */
    @GET("/lg/collect/usertools/json")
    Observable<BaseResponse<List<CollectWebInfo>>> collectWeb();
}
