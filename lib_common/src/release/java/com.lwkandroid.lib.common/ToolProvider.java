package com.lwkandroid.lib.common;

import android.app.ActivityThread;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.lwkandroid.lib.common.app.ActivityLifecycleHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:辅助工具
 *
 * @author LWK
 * @date 2020/2/15
 */
public class ToolProvider extends ContentProvider
{
    @Override
    public boolean onCreate()
    {
        //Activity栈管理
        ActivityThread.currentApplication().registerActivityLifecycleCallbacks(ActivityLifecycleHelper.get());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder)
    {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values)
    {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs)
    {
        return 0;
    }
}