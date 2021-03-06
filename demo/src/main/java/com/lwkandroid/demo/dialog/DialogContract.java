package com.lwkandroid.demo.dialog;

import com.lwkandroid.lib.common.mvp.IMvpBaseModel;
import com.lwkandroid.lib.common.mvp.IMvpBasePresenter;
import com.lwkandroid.lib.common.mvp.IMvpBaseView;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * Description:契约层
 *
 * @author
 * @date
 */
interface DialogContract
{
    interface IView<P extends LifecycleObserver> extends IMvpBaseView<P>
    {

    }

    interface IModel extends IMvpBaseModel
    {

    }

    interface IPresenter<V extends LifecycleOwner, M> extends IMvpBasePresenter<V, M>
    {

    }
}
