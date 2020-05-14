package com.grechur.common;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ToolBar
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 11:40
 */
public class ToolBar extends BaseObservable {
    private String toolTitle;
    private boolean showBar = true;
    private boolean showBack;
    private boolean showRight = false;
    private int rightId;

    private View.OnClickListener onClick;

    @Bindable
    public String getToolTitle() {
        return toolTitle;
    }

    public void setToolTitle(String toolTitle) {
        this.toolTitle = toolTitle;
        notifyPropertyChanged(BR.toolTitle);
    }

    @Bindable
    public boolean isShowBar() {
        return showBar;
    }

    public void setShowBar(boolean showBar) {
        this.showBar = showBar;
        notifyPropertyChanged(BR.showBar);
    }

    @Bindable
    public boolean isShowBack() {
        return showBack;
    }

    public void setShowBack(boolean showBack) {
        this.showBack = showBack;
        notifyPropertyChanged(BR.showBack);
    }

    @Bindable
    public View.OnClickListener getOnClick() {
        return onClick;
    }

    public void setOnClick(View.OnClickListener onClick) {
        this.onClick = onClick;
        notifyPropertyChanged(BR.onClick);
    }

    @Bindable
    public boolean isShowRight() {
        return showRight;
    }

    public void setShowRight(boolean showRight) {
        this.showRight = showRight;
        notifyPropertyChanged(BR.showRight);
    }

    @Bindable
    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
        notifyPropertyChanged(BR.rightId);
    }

    @BindingAdapter("imgLoad")
    public static void imgLoad(ImageView imageView,int rightId){
        imageView.setBackgroundResource(rightId);
    }
}
