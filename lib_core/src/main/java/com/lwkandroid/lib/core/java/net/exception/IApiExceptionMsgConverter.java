package com.lwkandroid.lib.core.java.net.exception;

/**
 * 定义ApiException错误描述文案的方法
 *
 * @author LWK
 */

public interface IApiExceptionMsgConverter
{
    String parserMessageByCode(int errorCode, String throwMessage);
}