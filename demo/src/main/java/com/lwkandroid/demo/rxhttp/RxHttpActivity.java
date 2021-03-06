package com.lwkandroid.demo.rxhttp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lwkandroid.demo.R;
import com.lwkandroid.lib.common.mvp.MvpBaseActivity;
import com.lwkandroid.lib.core.annotation.ClickViews;
import com.lwkandroid.lib.core.annotation.ViewInjector;

import androidx.annotation.Nullable;

/**
 * Description:View层
 *
 * @author
 * @date
 */
public class RxHttpActivity extends MvpBaseActivity<RxHttpPresenter> implements RxHttpContract.IView<RxHttpPresenter>
{

    @Override
    protected RxHttpPresenter createPresenter()
    {
        return new RxHttpPresenter(this, new RxHttpModel());
    }


    @Override
    protected void getIntentData(Intent intent, boolean newIntent)
    {

    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_rx_http;
    }

    @Override
    protected void initUI(View contentView)
    {
        ViewInjector.with(this);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState)
    {

    }

    @Override
    @ClickViews(values = {R.id.btn_rxhttp_01, R.id.btn_rxhttp_02})
    public void onClick(int id, View view)
    {
        switch (id)
        {
            case R.id.btn_rxhttp_01:
                getPresenter().test01();
                break;
            case R.id.btn_rxhttp_02:
                getPresenter().test02();
                break;
            default:
                break;
        }
    }

}
