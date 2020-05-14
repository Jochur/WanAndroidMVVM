package com.grechur.entry.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.OptionsInfo;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MineOptionsLayout
 * @Description: 我的自定义view
 * @Author: Grechur
 * @CreateDate: 2020/5/14 14:30
 */
public class MineOptionsLayout extends FrameLayout {
    ViewDataBinding bining;
    public MineOptionsLayout(Context context) {
        this(context, null);
    }

    public MineOptionsLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MineOptionsLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        bining = DataBindingUtil.inflate(inflater, R.layout.entry_mine_option_layout, null, false);
        addView(bining.getRoot());
    }

    public void setOptions(OptionsInfo options){
        if(options!=null) {
            bining.setVariable(BR.option, options);
        }
    }

}
