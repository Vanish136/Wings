package com.lwkandroid.wings.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.lwkandroid.wings.rx.utils.RxSchedulers;
import com.lwkandroid.wings.utils.StringUtils;

import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by LWK
 * TODO 二维码工具类
 */

public final class QRCodeUtils
{
    /**
     * 跳转扫码界面
     */
    public static void startScanQRCode(Activity activity, int requestCode)
    {
        QRCodeScanActivity.start(activity, requestCode);
    }

    /**
     * 跳转扫码界面
     */
    public static void startScanQRCode(android.app.Fragment fragment, int requestCode)
    {
        QRCodeScanActivity.start(fragment.getActivity(), requestCode);
    }

    /**
     * 跳转扫码界面
     */
    public static void startScanQRCode(android.support.v4.app.Fragment fragment, int requestCode)
    {
        QRCodeScanActivity.start(fragment.getActivity(), requestCode);
    }

    /**
     * 解析成功扫码后的结果【用于发起跳转界面的onActivityResult()】
     *
     * @return 扫码结果
     */
    public static String parseScanResult(int resultCode, Intent data)
    {
        if (resultCode == Activity.RESULT_OK && data != null)
            return data.getStringExtra(QRCodeScanActivity.KEY_RESULT);
        else
            return null;
    }

    /**
     * 解析本地某张图片内二维码内容
     * 【同步方法】
     *
     * @param picPath 图片绝对路径
     * @return 二维码内容
     */
    public static String decodeQRCode(String picPath)
    {
        return QRCodeDecoder.syncDecodeQRCode(picPath);
    }

    /**
     * 解析某张位图内二维码内容
     * 【同步方法】
     *
     * @param bitmap 位图
     * @return 二维码内容
     */
    public static String decodeQRCode(Bitmap bitmap)
    {
        return QRCodeDecoder.syncDecodeQRCode(bitmap);
    }

    /**
     * 配合RxJava解析本地某张图片内二维码内容
     * 【IO线程异步解析，订阅在主线程】
     *
     * @param picPath 图片绝对路径
     * @return 二维码内容
     */
    public static Observable<String> decodeQRCodeByRxJava(final String picPath)
    {
        return Observable.create(new ObservableOnSubscribe<String>()
        {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception
            {
                String content = decodeQRCode(picPath);
                if (!e.isDisposed())
                {
                    if (StringUtils.isNotEmpty(content))
                    {
                        e.onNext(content);
                        e.onComplete();
                    } else
                    {
                        e.onError(new NullPointerException());
                    }
                }
            }
        }).compose(RxSchedulers.<String>applyIo2Main());
    }

    /**
     * 配合RxJava解析某张位图内二维码内容
     * 【IO线程异步解析，订阅在主线程】
     *
     * @param bitmap 位图
     * @return 二维码内容
     */
    public static Observable<String> decodeQRCodeByRxJava(final Bitmap bitmap)
    {
        return Observable.create(new ObservableOnSubscribe<String>()
        {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception
            {
                String content = decodeQRCode(bitmap);
                if (!e.isDisposed())
                {
                    if (StringUtils.isNotEmpty(content))
                    {
                        e.onNext(content);
                        e.onComplete();
                    } else
                    {
                        e.onError(new NullPointerException());
                    }
                }
            }
        }).compose(RxSchedulers.<String>applyIo2Main());
    }

    /**
     * 创建一张二维码位图
     * 【同步方法】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @return 二维码位图
     */
    public static Bitmap encodeQRCode(String content, int size)
    {
        return QRCodeEncoder.syncEncodeQRCode(content, size, Color.BLACK, Color.WHITE, null);
    }

    /**
     * 创建一张二维码位图
     * 【同步方法】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param fColor  前景色，默认黑色
     * @param bColor  背景色，默认白色
     * @return 二维码位图
     */

    public static Bitmap encodeQRCode(String content, int size, int fColor, int bColor)
    {
        return QRCodeEncoder.syncEncodeQRCode(content, size, fColor, bColor, null);
    }

    /**
     * 创建一张二维码位图
     * 【同步方法】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param logo    中间logo
     * @return 二维码位图
     */
    public static Bitmap encodeQRCode(String content, int size, Bitmap logo)
    {
        return QRCodeEncoder.syncEncodeQRCode(content, size, Color.BLACK, Color.WHITE, logo);
    }

    /**
     * 创建一张二维码位图
     * 【同步方法】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param fColor  前景色，默认黑色
     * @param bColor  背景色，默认白色
     * @param logo    中间logo
     * @return 二维码位图
     */
    public static Bitmap encodeQRCode(String content, int size, int fColor, int bColor, Bitmap logo)
    {
        return QRCodeEncoder.syncEncodeQRCode(content, size, fColor, bColor, logo);
    }

    /**
     * 配合RxJava创建一张二维码位图
     * 【IO线程异步生成，订阅在主线程】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @return 二维码位图
     */
    public static Observable<Bitmap> encodeQRCodeByRxJava(final String content,
                                                          final int size)
    {
        return encodeQRCodeByRxJava(content, size, Color.BLACK, Color.WHITE, null);
    }

    /**
     * 配合RxJava创建一张二维码位图
     * 【IO线程异步生成，订阅在主线程】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param fColor  前景色，默认黑色
     * @param bColor  背景色，默认白色
     * @return 二维码位图
     */
    public static Observable<Bitmap> encodeQRCodeByRxJava(final String content,
                                                          final int size,
                                                          final int fColor,
                                                          final int bColor)
    {
        return encodeQRCodeByRxJava(content, size, fColor, bColor, null);
    }

    /**
     * 配合RxJava创建一张二维码位图
     * 【IO线程异步生成，订阅在主线程】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param logo    中间logo
     * @return 二维码位图
     */
    public static Observable<Bitmap> encodeQRCodeByRxJava(final String content,
                                                          final int size,
                                                          final Bitmap logo)
    {
        return encodeQRCodeByRxJava(content, size, Color.BLACK, Color.WHITE, logo);
    }

    /**
     * 配合RxJava创建一张二维码位图
     * 【IO线程异步生成，订阅在主线程】
     *
     * @param content 二维码内容
     * @param size    图片大小，单位px
     * @param fColor  前景色，默认黑色
     * @param bColor  背景色，默认白色
     * @param logo    中间logo
     * @return 二维码位图
     */
    public static Observable<Bitmap> encodeQRCodeByRxJava(final String content,
                                                          final int size,
                                                          final int fColor,
                                                          final int bColor,
                                                          final Bitmap logo)
    {
        return Observable.create(new ObservableOnSubscribe<Bitmap>()
        {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception
            {
                Bitmap bitmap = encodeQRCode(content, size, fColor, bColor, logo);
                if (!e.isDisposed())
                {
                    if (bitmap != null)
                    {
                        e.onNext(bitmap);
                        e.onComplete();
                    } else
                    {
                        e.onError(new NullPointerException());
                    }
                }
            }
        }).compose(RxSchedulers.<Bitmap>applyIo2Main());
    }
}