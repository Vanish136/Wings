package com.lwkandroid.wings.net.bean;

/**
 * Created by LWK
 * TODO 网络请求结果接口
 */

public interface IApiResult<T>
{
    int getCode();

    String getMessage();

    T getData();
}