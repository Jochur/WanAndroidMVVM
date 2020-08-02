package com.grechur.entry.net;


import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.BannerInfo;
import com.grechur.entry.bean.Children;
import com.grechur.entry.bean.HomePageInfo;
import com.grechur.entry.bean.HotBean;
import com.grechur.entry.bean.NavigationInfo;
import com.grechur.entry.bean.RankPageInfo;
import com.grechur.net.BaseResponse;
import com.grechur.net.Empty;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     * 项目分类
     */
    @GET("/project/tree/json")
    Observable<BaseResponse<List<Children>>> projectTree();

    /**
     * 项目分类
     */
    @GET("/project/list/{pageNum}/json")
    Observable<BaseResponse<HomePageInfo>> projectArticle(@Path("pageNum") int pageNum,@Query("cid") int cid);

    /**
     *
     */
    @POST("/lg/collect/{id}/json")
    Observable<BaseResponse<Empty>> collectArticle(@Path("id") int id);

    /**
     *搜索热词
     */
    @GET("/hotkey/json")
    Observable<BaseResponse<List<HotBean>>> hotKey();

    /**
     *  搜索
     * @param pageNum 页码
     * @param key 搜索词
     * @return
     */
    @FormUrlEncoded
    @POST("/article/query/{pageNum}/json")
    Observable<BaseResponse<HomePageInfo>> queryArticle(@Path("pageNum") int pageNum, @Field("k") String key);

    /**
     * 积分排行榜接口
     * @param pageNum
     * @return
     */
    @GET("/coin/rank/{pageNum}/json")
    Observable<BaseResponse<RankPageInfo>> rank(@Path("pageNum") int pageNum);
}
