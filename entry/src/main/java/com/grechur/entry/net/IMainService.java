package com.grechur.entry.net;


import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.net.BaseResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @ProjectName: ToolsDemo
 * @ClassName: IUserService
 * @Description: 用户相关的网络接口
 * @Author: Grechur
 * @CreateDate: 2020/5/6 17:23
 */
public interface IMainService {

    /**
     * 首页文章列表
     * @param pageNum
     * @return
     */
    @GET("/article/list/{pageNum}/json")
    Observable<BaseResponse<HomePageInfo>> homeArticle(@Path("pageNum") int pageNum);

    /**
     * 首页banner
     * @return
     */
    @GET("/banner/json")
    Observable<BaseResponse<List<BannerInfo>>> banner();

    /**
     *置顶文章
     */
    @GET("/article/top/json")
    Observable<BaseResponse<List<ArticleInfo>>> topArticle();

    /**
     * 体系数据
     */
    @GET("/tree/json")
    Observable<BaseResponse<List<Children>>> systemTree();

    /**
     *知识体系下的文章
     */
    @GET("/article/list/{pageNum}/json")
    Observable<BaseResponse<HomePageInfo>> systemArticle(@Path("pageNum") int pageNum,@Query("cid") int cid);

    /**
     * 导航数据
     */
    @GET("/navi/json")
    Observable<BaseResponse<List<NavigationInfo>>> navigation();
}
