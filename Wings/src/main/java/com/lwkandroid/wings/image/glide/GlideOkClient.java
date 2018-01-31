package com.lwkandroid.wings.image.glide;

import com.lwkandroid.wings.net.interceptor.OkProgressInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by LWK
 * TODO Glide专用的OkHttpClient
 */

public class GlideOkClient
{
    private static final long DEFAULT_TIMEOUT = 30000;

    private GlideOkClient()
    {
        mBuilder = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(new OkProgressInterceptor());
    }

    private static final class Holder
    {
        public static final GlideOkClient instance = new GlideOkClient();
    }

    public static GlideOkClient get()
    {
        return Holder.instance;
    }

    private OkHttpClient.Builder mBuilder;
    private OkHttpClient mClient;

    public OkHttpClient.Builder getBuilder()
    {
        return mBuilder;
    }

    public OkHttpClient getClient()
    {
        if (mClient == null)
            mClient = mBuilder.build();
        return mClient;
    }
}