package com.grechur.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: WarrpRecyclerView
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/10 15:44
 */
public class WarrpRecyclerView extends RecyclerView {
    private float beginY;
    private float moveY;
    private float lastY;

    private int mTouchSlop;

    public WarrpRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public WarrpRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WarrpRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = configuration.getScaledPagingTouchSlop();
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e("WarrpRecyclerView","overScrollBy deltaY:"+deltaY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
