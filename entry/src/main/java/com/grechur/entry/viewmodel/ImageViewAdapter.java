package com.grechur.entry.viewmodel;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.grechur.entry.R;

public class ImageViewAdapter {

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, Bitmap bitmap) {
        view.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:src")
    public static void setSrc(ImageView view, int resId) {
        view.setImageResource(resId);
    }



    @BindingAdapter("android:src")
    public static void setSrc(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.drawable.defult_img)
                .into(imageView);
    }


    @BindingAdapter("imgSrc")
    public static void imgSrc(ImageView imageView,String imageUrl){
        if(!TextUtils.isEmpty(imageUrl)) {
            Glide.with(imageView.getContext()).load(imageUrl)
                    .into(imageView);
        }
    }

    @BindingAdapter("drawableId")
    public static void drawableId(ImageView imageView,int drawableId){
        imageView.setImageResource(drawableId);
    }

}
