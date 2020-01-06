package com.lwkandroid.lib.core.java.utils.encode;

/**
 * Description: 二进制编解码方法
 *
 * @author LWK
 * @date 2019/5/27
 */
public interface IBinCodec
{
    String encode(String data);

    String encode(String data, String splitChar);

    String decode(String data);

    String decode(String data, String splitChar);
}