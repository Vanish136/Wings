package com.lwkandroid.wings.net.requst;

import android.text.TextUtils;

import com.lwkandroid.wings.net.ApiService;
import com.lwkandroid.wings.net.constants.ApiRequestType;
import com.lwkandroid.wings.net.parser.ApiFileParser;
import com.lwkandroid.wings.net.parser.ApiResponseConvert;
import com.socks.library.KLog;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by LWK
 * TODO Download请求
 */

public class ApiDownloadRequest extends ApiBaseRequest<ApiDownloadRequest> implements IApiInputSreamResponse
{
    /*存储文件夹*/
    private String mSaveFolder;
    /*存储名称*/
    private String mFileName;

    public ApiDownloadRequest(String url)
    {
        super(url, ApiRequestType.DOWNLOAD);
    }

    public ApiDownloadRequest setSaveFloderPath(String saveFolderPath)
    {
        this.mSaveFolder = saveFolderPath;
        return this;
    }

    public ApiDownloadRequest setFileName(String fileName)
    {
        this.mFileName = fileName;
        return this;
    }

    public String getSaveFloderPath()
    {
        return mSaveFolder;
    }

    public String getFileName()
    {
        return mFileName;
    }

    @Override
    protected Observable<ResponseBody> buildResponse(Map<String, String> headersMap,
                                                     Map<String, String> formDatasMap,
                                                     Object objectRequestBody,
                                                     RequestBody okHttp3RequestBody,
                                                     String jsonBody,
                                                     ApiService service)
    {
        if (objectRequestBody != null)
        {
            KLog.w("RxHttp method DOWNLOAD must not have a request body：\n" + objectRequestBody.toString());
        } else if (okHttp3RequestBody != null)
        {
            KLog.w("RxHttp method DOWNLOAD must not have a request body：\n" + okHttp3RequestBody.toString());
        } else if (!TextUtils.isEmpty(jsonBody))
        {
            KLog.w("RxHttp method DOWNLOAD must not have a request body：\n" + jsonBody);
        }
        return service.downloadFile(getUrl(), headersMap, formDatasMap);
    }

    @Override
    public Observable<File> parseAsFile()
    {
        return invokeRequest().compose(ApiResponseConvert.responseToInputStream())
                .compose(new ApiFileParser(mSaveFolder, mFileName).parseDataAsFile());
    }
}
