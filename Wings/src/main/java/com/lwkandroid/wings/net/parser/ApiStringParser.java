package com.lwkandroid.wings.net.parser;

import com.lwkandroid.wings.net.RxHttp;
import com.lwkandroid.wings.net.bean.ApiException;
import com.lwkandroid.wings.net.bean.IApiResult;
import com.lwkandroid.wings.net.constants.ApiExceptionCode;
import com.lwkandroid.wings.utils.StringUtils;
import com.lwkandroid.wings.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * 将String类型的网络请求结果转换为{@link com.lwkandroid.wings.net.bean.IApiResult}的实现类
 *
 * @author LWK
 */
public class ApiStringParser implements IApiStringParser
{
    @Override
    public <T> ObservableTransformer<String, T> parseAsObject(final Class<T> clazz)
    {
        return new ObservableTransformer<String, T>()
        {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<String> upstream)
            {
                return upstream.map(new Function<String, T>()
                {
                    @Override
                    public T apply(@NonNull String s) throws Exception
                    {
                        String dataJsonString = parseDataJsonString(s);
                        return StringUtils.isNotEmpty(dataJsonString) ?
                                JsonUtils.get().parseJsonObject(dataJsonString, clazz) : clazz.newInstance();
                    }
                });
            }
        };
    }

    @Override
    public <T> ObservableTransformer<String, List<T>> parseAsList(final Class<T> clazz)
    {
        return new ObservableTransformer<String, List<T>>()
        {
            @Override
            public ObservableSource<List<T>> apply(@NonNull Observable<String> upstream)
            {
                return upstream.map(new Function<String, List<T>>()
                {
                    @Override
                    public List<T> apply(@NonNull String s) throws Exception
                    {
                        String dataJsonString = parseDataJsonString(s);
                        return StringUtils.isNotEmpty(dataJsonString) ?
                                JsonUtils.get().parseJsonArray(dataJsonString, clazz) : new ArrayList<T>();
                    }
                });
            }
        };
    }

    /**
     * 将获取String请求结果中Data的Json字符串
     *
     * @param response 网络请求String结果
     * @return
     * @throws ApiException
     */
    private String parseDataJsonString(String response) throws ApiException
    {
        IApiResult<Object> result = JsonUtils.get().parseJsonObject(response, RxHttp.getGlobalOptions().getApiResultType());
        if (result == null)
        {
            throw new ApiException(ApiExceptionCode.RESPONSE_EMPTY, "Could not get any Response");
        }
        if (!result.isResultOK())
        {
            throw new ApiException(result.getCode(), result.getMessage());
        }
        Object resultData = result.getData();
        return resultData != null ? JsonUtils.get().toJson(result.getData()) : null;
    }
}
