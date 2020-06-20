package com.grechur.common.view.recycle;

import android.view.View;

import java.util.Stack;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: Recycler
 * @Description:回收池
 * @Author: Grechur
 * @CreateDate: 2020/6/19 14:15
 */
public class Recycler {
    //回收池的容器  存储所有的的回收了的View
    private Stack<View>[] views;

    public Recycler(int viewTypeCount){
        //根据类型种类数量来创建数组
        views = new Stack[viewTypeCount];
        for (int i = 0; i < viewTypeCount; i++) {
            //为每个类型创建一个栈
            views[i] = new Stack<>();
        }
    }

    /**
     * 根据viewtype放入view
     */
    public void put(View itemView,int viewType){
        views[viewType].push(itemView);
    }

    public View get(int viewType){
        try {
            return views[viewType].pop();
        }catch (Exception e){
            return null;
        }
    }
}
