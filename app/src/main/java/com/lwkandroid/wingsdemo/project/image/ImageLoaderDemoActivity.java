package com.lwkandroid.wingsdemo.project.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.lwkandroid.wings.image.ImageLoader;
import com.lwkandroid.wings.mvp.BasePresenter;
import com.lwkandroid.wings.net.bean.ProgressInfo;
import com.lwkandroid.wings.net.listener.OnProgressListener;
import com.lwkandroid.wings.net.manager.OkProgressManger;
import com.lwkandroid.wings.widget.RoundDiffImageView;
import com.lwkandroid.wings.widget.shapeimage.ShapeImageView;
import com.lwkandroid.wingsdemo.R;
import com.lwkandroid.wingsdemo.app.AppBaseActivity;
import com.socks.library.KLog;

/**
 * ImageLoaderDemoActivity
 */
public class ImageLoaderDemoActivity extends AppBaseActivity
{
    private static final String IMG_URL01 = "http://oi5vnsj5b.bkt.clouddn.com/pano_01.jpg";
    private static final String IMG_URL02 = "http://oi5vnsj5b.bkt.clouddn.com/pano_02.jpg";
    private static final String IMG_URL03 = "http://oi5vnsj5b.bkt.clouddn.com/pano_03.jpg";
    private static final String IMG_URL04 = "http://oi5vnsj5b.bkt.clouddn.com/pano_04.jpg";
    private static final String GIF_URL = "http://7d9qxm.com1.z0.glb.clouddn.com/gif01.gif";

    private ImageView mImageView00;
    private ShapeImageView mImageView01;
    private ShapeImageView mImageView02;
    private RoundDiffImageView mImageView03;
    private ImageView mImageView04;

    @Override
    public int getContentViewId()
    {
        return R.layout.activity_imageloader_demo;
    }

    @Override
    protected void initUI(View contentView)
    {
        super.initUI(contentView);
        mImageView00 = find(R.id.img_imageloader_demo00);
        mImageView01 = find(R.id.img_imageloader_demo01);
        mImageView02 = find(R.id.img_imageloader_demo02);
        mImageView03 = find(R.id.img_imageloader_demo03);
        mImageView04 = find(R.id.img_imageloader_demo04);

        ImageLoader.getDefaultLoader().load(IMG_URL01)
                .setCrossFade(true)
                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
                .setErrorHolder(R.drawable.img_placeholder_square_fail)
                .show(this, mImageView00);

        ImageLoader.getDefaultLoader().load(IMG_URL02)
                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
                .setErrorHolder(R.drawable.img_placeholder_square_fail)
                .setCrossFade(true)
                .show(this, mImageView01);

        ImageLoader.getDefaultLoader().load(IMG_URL03)
                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
                .setErrorHolder(R.drawable.img_placeholder_square_fail)
                .setCrossFade(true)
                .show(this, mImageView02);

        //RoundDiffImageView不能调用.setCrossFade(true) !!!
        //        ImageLoader.getDefaultLoader().load(IMG_URL04)
        //                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
        //                .setErrorHolder(R.drawable.img_placeholder_square_fail)
        //                .show(this, mImageView03);
        ImageLoader.getGlideLoader().load(IMG_URL04)
                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
                .setErrorHolder(R.drawable.img_placeholder_square_fail)
                .setTransformation(new BlurTransformation(25, 6))
                .show(this, mImageView03);

        ImageLoader.getDefaultLoader().load(GIF_URL)
                .setPlaceHolder(R.drawable.img_placeholder_square_loading)
                .setErrorHolder(R.drawable.img_placeholder_square_fail)
                .setAsGif(true)
                .show(this, mImageView04);

        OkProgressManger.get().addDownloadListener(GIF_URL, new OnProgressListener()
        {
            @Override
            public void onProgress(ProgressInfo info)
            {
                KLog.i("gif加载进度:" + info.getPercent());
            }

            @Override
            public void onError(long id, Exception e)
            {

            }
        });

        addClick(R.id.btn_imageloader_demo_clear, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ImageLoader.getDefaultLoader().clearCache(ImageLoaderDemoActivity.this);
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
    protected BasePresenter createPresenter()
    {
        return null;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        ImageLoader.getDefaultLoader().pause(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        ImageLoader.getDefaultLoader().resume(this);
    }

    @Override
    protected void onDestroy()
    {
        OkProgressManger.get().removeDownloadListener(GIF_URL);
        super.onDestroy();
    }
}