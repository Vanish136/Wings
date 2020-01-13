package com.lwkandroid.lib.core.java.net.parser;


import com.lwkandroid.lib.core.java.log.KLog;
import com.lwkandroid.lib.core.java.net.bean.ApiException;
import com.lwkandroid.lib.core.java.net.constants.ApiExceptionCode;
import com.lwkandroid.lib.core.java.utils.FileIOUtils;
import com.lwkandroid.lib.core.java.utils.PathUtils;
import com.lwkandroid.lib.core.java.utils.StringUtils;

import java.io.File;
import java.io.InputStream;

import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

/**
 *  将InputStream类型的网络请求结果转换为File并写入存储的实现类
 *
 * @author LWK
 */

public class ApiIS2FileParser implements IApiInputStreamParser.FileParser
{
    private String mSavePath;
    private String mFileName;

    public ApiIS2FileParser(String savePath, String fileName)
    {
        this.mSavePath = savePath;
        this.mFileName = fileName;
    }

    @Override
    public ObservableTransformer<InputStream, File> parseAsFile()
    {
        return upstream -> upstream.map((Function<InputStream, File>) this::writeIntoStorage);
    }

    /**
     * 将数据写入存储
     */
    private File writeIntoStorage(InputStream inputStream) throws ApiException
    {
        if (StringUtils.isEmpty(mFileName))
        {
            mFileName = String.valueOf(System.currentTimeMillis());
        }
        if (StringUtils.isEmpty(mSavePath))
        {
            mSavePath = PathUtils.getExternalAppFilesPath();
        }
        mSavePath = mSavePath.replaceAll("//", "/");

        try
        {
            File file = new File(mSavePath, mFileName);
            KLog.i("ApiIS2FileParser create absolutely path = " + file.getAbsolutePath());
            if (FileIOUtils.writeFileFromIS(file, inputStream))
            {
                return file;
            } else
            {
                throw new ApiException(ApiExceptionCode.IO_EXCEPTION, null);
            }
        } catch (Exception e)
        {
            KLog.e("ApiIS2FileParser writeIntoStorage exception:" + e.toString());
            throw new ApiException(ApiExceptionCode.IO_EXCEPTION, e.toString());
        }
    }

}
