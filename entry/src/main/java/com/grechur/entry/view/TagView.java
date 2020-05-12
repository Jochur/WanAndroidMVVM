package com.grechur.entry.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.Children;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: TagView
 * @Description: 标签自定义view
 * @Author: Grechur
 * @CreateDate: 2020/5/12 15:38
 */
public class TagView extends LinearLayout {
    private ViewDataBinding binding;
    public TagView(Context context) {
        this(context,null);
    }

    public TagView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
         binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.entry_tag_view, null, false);

         addView(binding.getRoot());
    }

    public void setData(Children children){
        binding.setVariable(BR.children,children);
    }

//    public void onClick(Children children){
//        ARouter.getInstance()
//                .build(RouterSchame.SYSTEM_ARTICLE_ACTIVITY)
//                .withInt("cid",children.getId())
//                .navigation();
//    }

}
