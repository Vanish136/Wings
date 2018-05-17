package com.lwkandroid.wings.net.requst;

import com.lwkandroid.wings.net.ApiService;
import com.lwkandroid.wings.net.RxHttp;
import com.lwkandroid.wings.net.constants.ApiRequestType;
import com.lwkandroid.wings.net.parser.ApiResponseConvert;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by LWK
 * TODO Get请求
 */

public class ApiGetRequest extends ApiBaseRequest<ApiGetRequest> implements IApiStringResponse
{

    public ApiGetRequest(String url)
    {
        super(url, ApiRequestType.GET);
    }

    @Override
    protected Observable<ResponseBody> buildResponse(Map<String, String> headersMap,
                                                     Map<String, String> formDatasMap,
                                                     Object objectRequestBody,
                                                     RequestBody okHttp3RequestBody,
                                                     String jsonRequestBody,
                                                     ApiService service)
    {
        return service.get(getUrl(), headersMap, formDatasMap);
    }

    @Override
    public <T> Observable<T> parseAsObject(Class<T> tOfClass)
    {
        return invokeRequest()
                .compose(ApiResponseConvert.responseToString())
                .compose(RxHttp.getGlobalOptions().getApiStringParser().parseDataAsObject(tOfClass));
    }

    @Override
    public <T> Observable<List<T>> parseAsList(Class<T> tOfClass)
    {
        return invokeRequest()
                .compose(ApiResponseConvert.responseToString())
                .compose(RxHttp.getGlobalOptions().getApiStringParser().parseDataAsList(tOfClass));
    }
}
