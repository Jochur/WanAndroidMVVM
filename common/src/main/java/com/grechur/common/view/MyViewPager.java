package com.grechur.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MyViewPager
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/11 17:09
 */
public class MyViewPager extends ViewGroup {
    private int mLastX;

    public MyViewPager(Context context) {
        this(context,null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View childAt = getChildAt(i);
            childAt.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                childAt.layout(i*getWidth(),t,(i+1)*getWidth(),b);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = mLastX - x;
                int oldScrollX = getScrollX();
                int preScrollX = oldScrollX +dx;
                if(preScrollX>(getChildCount()-1)*getWidth()){
                    preScrollX = (getChildCount() - 1) * getWidth();
                }
                if(preScrollX < 0){
                    preScrollX = 0;
                }
                scrollTo(preScrollX,getScrollY());
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
                Log.e("MyViewPager","getScrollX():"+getScrollX());
                Log.e("MyViewPager","getWidth():"+getWidth());
                Log.e("MyViewPager","move:"+(mLastX - x));
                int index = (getScrollX() + getWidth()*2 / 3) / getWidth();
                Log.e("MyViewPager","index:"+index);
                scrollTo(index * getWidth(),getChildCount());
                mLastX = x;
                break;
        }
        return true;
    }
}
