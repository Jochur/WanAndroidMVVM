package com.grechur.entry.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.entry.bean.BannerInfo;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ImageAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/11 11:40
 */
public class ImageAdapter extends BannerAdapter<BannerInfo, ImageAdapter.BannerViewHolder> {
    private Context context;

    public ImageAdapter(Context context, List<BannerInfo> datas) {
        super(datas);
        this.context = context;
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, final BannerInfo data, int position, int size) {
        if(!TextUtils.isEmpty(data.getImagePath())){
            Glide.with(context).load(data.getImagePath()).into(holder.imageView);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(context, WebViewActivity.class);
//                intent.putExtra(Constants.INTENT_URL,data.getUrl());
//                intent.putExtra(Constants.INTENT_TITLE,data.getTitle());
//                context.startActivity(intent);
                ARouter.getInstance()
                        .build(RouterSchame.WEB_VIEW_ACTIVITY)
                        .withString(Constants.INTENT_URL,data.getUrl())
                        .withString(Constants.INTENT_TITLE,data.getTitle())
                        .navigation();
            }
        });
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
