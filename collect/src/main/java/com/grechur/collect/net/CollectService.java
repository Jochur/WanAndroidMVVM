package com.grechur.collect.net;

import com.grechur.collect.bean.CollectPageInfo;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.common.bean.Empty;
import com.grechur.net.BaseResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    /**
     *编辑收藏网站
     */
    @FormUrlEncoded
    @POST("/lg/collect/updatetool/json")
    Observable<BaseResponse<CollectWebInfo>> updateWeb(@FieldMap Map<String,Object> map);

    /**
     * 删除收藏网站
     */
    @FormUrlEncoded
    @POST("/lg/collect/deletetool/json")
    Observable<BaseResponse<Empty>> deleteWeb(@Field("id") int id);
}
