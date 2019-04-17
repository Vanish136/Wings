package com.lwkandroid.wings.net;

import com.lwkandroid.wings.net.bean.ApiGlobalOptions;
import com.lwkandroid.wings.net.bean.ApiResult;
import com.lwkandroid.wings.net.constants.ApiConstants;
import com.lwkandroid.wings.net.cookie.CookieManager;
import com.lwkandroid.wings.net.error.UnknownErrorHandler;
import com.lwkandroid.wings.net.interceptor.OkProgressInterceptor;
import com.lwkandroid.wings.net.requst.ApiDeleteRequest;
import com.lwkandroid.wings.net.requst.ApiDownloadRequest;
import com.lwkandroid.wings.net.requst.ApiGetRequest;
import com.lwkandroid.wings.net.requst.ApiPatchRequest;
import com.lwkandroid.wings.net.requst.ApiPostRequest;
import com.lwkandroid.wings.net.requst.ApiPutRequest;
import com.lwkandroid.wings.net.requst.ApiUploadRequest;
import com.lwkandroid.wings.net.utils.RetrofitUtils;

import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by LWK
 * TODO 向外提供功能的入口
 */

public class RxHttp
{
    static
    {
        DEFAULT_GLOBAL_OPTIONS = new ApiGlobalOptions();
        RETROFIT = new RetrofitUtils();
    }

    private RxHttp()
    {
    }

    private static final ApiGlobalOptions DEFAULT_GLOBAL_OPTIONS;
    private static final RetrofitUtils RETROFIT;

    /**
     * 初始化公共配置
     *
     * @param baseUrl 网络请求域名，用来配置Retrofit，结尾必须是“/”
     * @return 公共配置对象
     */
    public static ApiGlobalOptions init(String baseUrl)
    {
        DEFAULT_GLOBAL_OPTIONS.setBaseUrl(baseUrl);
        DEFAULT_GLOBAL_OPTIONS.setApiResultType(ApiResult.class);
        DEFAULT_GLOBAL_OPTIONS.setCookieManager(new CookieManager());
        DEFAULT_GLOBAL_OPTIONS.addInterceptor(ApiConstants.TAG_PROGRESS_INTERCEPTOR, new OkProgressInterceptor());
        RxJavaPlugins.setErrorHandler(new UnknownErrorHandler());
        return DEFAULT_GLOBAL_OPTIONS;
    }

    /**
     * 获取公共配置
     */
    public static ApiGlobalOptions getGlobalOptions()
    {
        return DEFAULT_GLOBAL_OPTIONS;
    }

    /**
     * 发起Get请求
     */
    public static ApiGetRequest GET(String url)
    {
        return new ApiGetRequest(url);
    }

    /**
     * 发起Post请求
     */
    public static ApiPostRequest POST(String url)
    {
        return new ApiPostRequest(url);
    }

    /**
     * 发起Put请求
     */
    public static ApiPutRequest PUT(String url)
    {
        return new ApiPutRequest(url);
    }

    /**
     * 发起Delete请求
     */
    public static ApiDeleteRequest DELETE(String url)
    {
        return new ApiDeleteRequest(url);
    }

    /**
     * 发起Patch请求
     */
    public static ApiPatchRequest PATCH(String url)
    {
        return new ApiPatchRequest(url);
    }

    /**
     * 发起Download请求
     */
    public static ApiDownloadRequest DOWNLOAD(String url)
    {
        return new ApiDownloadRequest(url);
    }

    /**
     * 发起Upload请求
     */
    public static ApiUploadRequest UPLOAD(String url)
    {
        return new ApiUploadRequest(url);
    }

    /**
     * 获取RetrofitUtils对象，便于自定义接口
     */
    public static RetrofitUtils RETROFIT()
    {
        return RETROFIT;
    }
}
