package com.lwkandroid.wings.net.constants;

/**
 * Created by LWK
 * TODO 这个类定义了所有请求结果的结果码
 */

public class ApiExceptionCode
{
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1003;

    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1004;

    /**
     * 类转换错误
     */
    public static final int CAST_ERROR = 1005;
    /**
     * 未知主机错误
     */
    public static final int UNKNOWNHOST_ERROR = 1006;
    /**
     * 空指针错误
     */
    public static final int NULLPOINTER_EXCEPTION = 1007;
    /**
     * IO错误
     */
    public static final int IO_EXCEPTION = 1008;
}