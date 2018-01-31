package com.lwkandroid.wingsdemo.project.rxhttp;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lwkandroid.rtpermission.RTPermission;
import com.lwkandroid.rtpermission.listener.OnPermissionResultListener;
import com.lwkandroid.wings.net.bean.ApiException;
import com.lwkandroid.wings.net.bean.ProgressInfo;
import com.lwkandroid.wingsdemo.R;
import com.lwkandroid.wingsdemo.app.AppBaseActivity;
import com.lwkandroid.wingsdemo.bean.TabsBean;

import java.io.File;
import java.util.List;

/**
 * 网络请求框架使用示例
 * 数据来源：https://github.com/jokermonn/-Api/blob/master/Neihan.md#recommend
 */
public class RxHttpDemoActivity extends AppBaseActivity<RxHttpDemoPresenter> implements RxHttpDemoConstract.View
{
    private TextView mTextView;

    @Override
    public int getContentViewId()
    {
        return R.layout.activity_rxhttp_demo;
    }

    @Override
    protected void initUI(View contentView)
    {
        mTextView = find(R.id.tv_rxhttp_demo);
        addClick(R.id.btn_rxhttp_demo01, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.requestData();
            }
        });
        addClick(R.id.btn_rxhttp_demo02, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mPresenter.requestDataByService();
            }
        });
        addClick(R.id.btn_rxhttp_download, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new RTPermission.Builder()
                        .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .start(RxHttpDemoActivity.this, new OnPermissionResultListener()
                        {
                            @Override
                            public void onAllGranted(String[] allPermissions)
                            {
                                mPresenter.requestMovieData();
                            }

                            @Override
                            public void onDeined(String[] dinedPermissions)
                            {
                                showShortToast("不给权限我咋下载啊大兄弟");
                            }
                        });
            }
        });
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState)
    {
    }

    @Override
    protected void onClick(int id, View v)
    {
    }

    @Override
    protected RxHttpDemoPresenter createPresenter()
    {
        return new RxHttpDemoPresenter();
    }


    @Override
    public void setHttpResultData(List<TabsBean> dataList)
    {
        if (dataList == null)
            mTextView.setText(null);
        else
            mTextView.setText(dataList.toString());
    }

    @Override
    public void showDownloadResult(File file)
    {
        mTextView.setText("下载完成：" + file.getAbsolutePath());
    }

    @Override
    public void setDownLoadEnable(boolean enable)
    {
        find(R.id.btn_rxhttp_download).setEnabled(enable);
    }

    @Override
    public void showDownloadProgress(ProgressInfo info)
    {
        mTextView.setText("下载进度：" + info.getPercent() + "%");
    }

    @Override
    public void showHttpError(ApiException e)
    {
        mTextView.setText("请求错误：" + e.toString());
    }
}