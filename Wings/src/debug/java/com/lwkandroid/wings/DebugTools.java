package com.lwkandroid.wings;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.lwkandroid.wings.image.glide.GlideOkClient;
import com.lwkandroid.wings.net.RxHttp;
import com.squareup.leakcanary.AndroidExcludedRefs;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.ExcludedRefs;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by LWK
 * TODO Debug工具
 * 2017/5/22
 */

public class DebugTools
{
    public static final boolean DEBUG = true;

    public static void init(Application context)
    {
        //Debug环境下可以初始化debug工具
        //Facebook的Stetho
        Stetho.initialize(Stetho.newInitializerBuilder(context.getApplicationContext())
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(context.getApplicationContext()))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context.getApplicationContext()))
                .build());
        RxHttp.getGlobalOptions().addNetInterceptor("Stetho", new StethoInterceptor());
        GlideOkClient.get().getBuilder().addNetworkInterceptor(new StethoInterceptor());
        //Square的LeakCanary
        if (LeakCanary.isInAnalyzerProcess(context.getApplicationContext()))
            return;
        ExcludedRefs excludedRefs = AndroidExcludedRefs.createAppDefaults()
                .instanceField("android.view.inputmethod.InputMethodManager", "sInstance")
                .instanceField("android.view.inputmethod.InputMethodManager", "mLastSrvView")
                .instanceField("com.android.internal.policy.PhoneWindow$DecorView", "mContext")
                .instanceField("android.support.v7.widget.SearchView$SearchAutoComplete", "mContext")
                .build();
        LeakCanary
                .refWatcher(context)
                .listenerServiceClass(DisplayLeakService.class)
                .excludedRefs(excludedRefs)
                .buildAndInstall();
    }
}