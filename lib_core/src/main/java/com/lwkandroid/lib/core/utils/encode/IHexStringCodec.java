package com.lwkandroid.lib.core.utils.encode;

import java.nio.charset.Charset;

/**
 * Description:16进制编解码方法
 *
 * @author LWK
 * @date 2019/5/28
 */
public interface IHexStringCodec
{
    String encode(byte[] data);

    String encode(byte[] data, String spiltStr);

    String encode(String data);

    String encode(String data, String spiltStr);

    String encode(String data, Charset charset);

    String encode(String data, String spiltStr, Charset charset);

    String decodeToString(String hexString);

    String decodeToString(String hexString, String spiltStr);

    byte[] decodeToBytes(String hexString);

    byte[] decodeToBytes(String hexString, String spiltStr);

    String decodeToString(String hexString, Charset charset);

    String decodeToString(String hexString, String spiltStr, Charset charset);

    byte[] decodeToBytes(String hexString, Charset charset);

    byte[] decodeToBytes(String hexString, String spiltStr, Charset charset);
}
