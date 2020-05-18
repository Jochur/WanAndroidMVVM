package com.grechur.entry.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.base.BaseReFragment;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.SpUtils;
import com.grechur.entry.R;
import com.grechur.entry.bean.OptionsInfo;
import com.grechur.entry.databinding.EntryFragmentMineBinding;
import com.grechur.entry.viewmodel.MineViewModel;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MineFragment
 * @Description: 我的页面
 * @Author: Grechur
 * @CreateDate: 2020/5/14 14:22
 */
public class MineFragment extends BaseReFragment<MineViewModel, EntryFragmentMineBinding> {

    private OptionsInfo userOption;

    @Override
    protected void initView(Bundle savedInstanceState) {
        userOption = getUserOption();
        binding.entryUser.setOptions(userOption);
        binding.entryCollect.setOptions(getCollectOption());
        binding.entrySet.setOptions(getSetOption());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(SpUtils.getString(getContext(),"userName",""))) {
            userOption.setText(SpUtils.getString(getContext(), "userName", ""));
        }
    }

    private OptionsInfo getUserOption(){
        OptionsInfo optionsInfo = new OptionsInfo();
        if(!TextUtils.isEmpty(SpUtils.getString(getContext(),"userImg",""))) {
            optionsInfo.setImgUrl("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg");
        }else{
            optionsInfo.setDrawableId(R.drawable.defult_img);
        }
        if(!TextUtils.isEmpty(SpUtils.getString(getContext(),"userName",""))){
            optionsInfo.setText(SpUtils.getString(getContext(),"userName",""));
        }else{
            optionsInfo.setText("请登录");
        }
        optionsInfo.setShowRight(false);
        optionsInfo.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(SpUtils.getString(getContext(),"userName",""))){
                    ARouter.getInstance()
                            .build(RouterSchame.LOGIN_ACTIVITY)
                            .navigation();
                }else{

                }
            }
        });
        return optionsInfo;
    }

    private OptionsInfo getCollectOption(){
        OptionsInfo optionsInfo = new OptionsInfo();
        optionsInfo.setDrawableId(R.drawable.entry_collect);
        optionsInfo.setText("收藏");
        optionsInfo.setShowRight(true);
        optionsInfo.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance()
                        .build(RouterSchame.COLLECT_ACTIVITY)
                        .navigation();
            }
        });
        return optionsInfo;
    }
    private OptionsInfo getSetOption(){
        OptionsInfo optionsInfo = new OptionsInfo();
        optionsInfo.setDrawableId(R.drawable.entry_setting);
        optionsInfo.setText("设置");
        optionsInfo.setShowRight(true);
        optionsInfo.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return optionsInfo;
    }
}
