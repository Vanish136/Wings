package com.lwkandroid.wingsdemo.project.rxhttp;

import android.graphics.Bitmap;

import com.lwkandroid.wings.net.bean.ApiResultCacheWrapper;
import com.lwkandroid.wings.net.bean.ApiException;
import com.lwkandroid.wings.net.bean.ProgressInfo;
import com.lwkandroid.wings.net.listener.OnProgressListener;
import com.lwkandroid.wings.net.manager.OkProgressManger;
import com.lwkandroid.wings.net.observer.ApiBaseObserver;
import com.lwkandroid.wingsdemo.bean.NonRestFulResult;
import com.lwkandroid.wingsdemo.bean.TabsBean;
import com.lwkandroid.wingsdemo.net.ApiURL;
import com.socks.library.KLog;

import java.io.File;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by LWK
 * TODO RxHttpDemo Presenter层
 */

public class RxHttpDemoPresenter extends RxHttpDemoConstract.Presenter
{

    @Override
    protected RxHttpDemoConstract.Model createModel()
    {
        return new RxHttpDemoModel();
    }

    @Override
    void requestData()
    {
        mModelImpl.requestData()
                .compose(this.<List<TabsBean>>applyIo2MainWithLifeCycle())
                .subscribe(new ApiBaseObserver<List<TabsBean>>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        super.onSubscribe(d);
                        mViewImpl.setWeatherHttpResultData(null);
                    }

                    @Override
                    public void _OnNext(List<TabsBean> dataList)
                    {
                        mViewImpl.setWeatherHttpResultData(dataList);
                    }

                    @Override
                    public void _OnError(ApiException e)
                    {
                        KLog.e("无法获取数据：" + e.toString());
                        mViewImpl.showHttpError(e);
                    }
                });
    }

    @Override
    void requestDataByService()
    {
        mModelImpl.requestDataByService()
                .compose(this.<List<TabsBean>>applyIo2MainWithLifeCycle())
                .subscribe(new ApiBaseObserver<List<TabsBean>>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        super.onSubscribe(d);
                        mViewImpl.setWeatherHttpResultData(null);
                    }

                    @Override
                    public void _OnNext(List<TabsBean> dataList)
                    {
                        mViewImpl.setWeatherHttpResultData(dataList);
                    }

                    @Override
                    public void _OnError(ApiException e)
                    {
                        mViewImpl.showHttpError(e);
                    }
                });
    }

    @Override
    void requestMovieData()
    {
        mModelImpl.requestMovieData()
                .compose(this.<File>applyIo2MainWithLifeCycle())
                .subscribe(new ApiBaseObserver<File>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        super.onSubscribe(d);
                        mViewImpl.setDownLoadEnable(false);
                    }

                    @Override
                    public void _OnNext(File file)
                    {
                        mViewImpl.showDownloadResult(file);
                        mViewImpl.setDownLoadEnable(true);
                    }

                    @Override
                    public void _OnError(ApiException e)
                    {
                        mViewImpl.showHttpError(e);
                        mViewImpl.setDownLoadEnable(true);
                    }
                });

        OkProgressManger.get().addDownloadListener(ApiURL.DOWNLOAD_TEST, new OnProgressListener()
        {
            @Override
            public void onProgress(ProgressInfo info)
            {
                mViewImpl.showDownloadProgress(info);
            }

            @Override
            public void onError(long id, Exception e)
            {
                KLog.e("监听下载时发生错误：" + e.toString());
            }
        });
    }

    @Override
    void requestNonRestFul()
    {
        mModelImpl.requestNonRestFulData()
                .compose(this.<ApiResultCacheWrapper<NonRestFulResult>>applyIo2MainWithLifeCycle())
                .subscribe(new ApiBaseObserver<ApiResultCacheWrapper<NonRestFulResult>>()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                        super.onSubscribe(d);
                        mViewImpl.showNonRestFulResult(null);
                    }

                    @Override
                    public void _OnNext(ApiResultCacheWrapper<NonRestFulResult> resultBean)
                    {
                        KLog.i("是否为缓存：" + resultBean.isCache());
                        mViewImpl.showNonRestFulResult(resultBean.getData());
                    }

                    @Override
                    public void _OnError(ApiException e)
                    {
                        mViewImpl.showHttpError(e);
                    }
                });
        //                .subscribe(new ApiBaseObserver<NonRestFulResult>()
        //                {
        //                    @Override
        //                    public void onSubscribe(Disposable d)
        //                    {
        //                        super.onSubscribe(d);
        //                        mViewImpl.showNonRestFulResult(null);
        //                    }
        //
        //                    @Override
        //                    public void _OnNext(NonRestFulResult nonRestFulResult)
        //                    {
        //                        KLog.e();
        //                        mViewImpl.showNonRestFulResult(nonRestFulResult);
        //                    }
        //
        //                    @Override
        //                    public void _OnError(ApiException e)
        //                    {
        //                        mViewImpl.showHttpError(e);
        //                    }
        //                });
    }

    @Override
    void requestBitmapData()
    {
        mModelImpl.requestBitmapData()
                .compose(this.<Bitmap>applyIo2MainWithLifeCycle())
                .subscribe(new ApiBaseObserver<Bitmap>()
                {
                    @Override
                    public void _OnNext(Bitmap bitmap)
                    {
                        mViewImpl.showImageBitmap(bitmap);
                    }

                    @Override
                    public void _OnError(ApiException e)
                    {
                        mViewImpl.showHttpError(e);
                    }
                });
    }

    @Override
    public void onDestory()
    {
        super.onDestory();
        OkProgressManger.get().removeDownloadListener(ApiURL.DOWNLOAD_TEST);
    }
}
