package com.grechur.entry.bean;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.grechur.entry.BR;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: OptionsInfo
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/14 14:34
 */
public class OptionsInfo extends BaseObservable{

    private int drawableId;
    private String imgUrl;
    private String text;
    private boolean showRight;

    private View.OnClickListener click;

    @Bindable
    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
        notifyPropertyChanged(BR.drawableId);
    }

    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        notifyPropertyChanged(BR.imgUrl);
    }

    @Bindable
    public String getText() {
        return text;

    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
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
    public View.OnClickListener getClick() {
        return click;
    }

    public void setClick(View.OnClickListener click) {
        this.click = click;
        notifyPropertyChanged(BR.click);
    }
}
