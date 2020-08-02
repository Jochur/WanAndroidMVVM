package com.grechur.entry.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.base.BaseReFragment;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.interceptors.LoginNavigationCallbackImpl;
import com.grechur.common.util.SpUtils;
import com.grechur.entry.FilterActivity;
import com.grechur.entry.R;
import com.grechur.entry.RankActivity;
import com.grechur.entry.bean.OptionsInfo;
import com.grechur.entry.databinding.EntryFragmentMineBinding;
import com.grechur.entry.viewmodel.MineViewModel;

import static android.app.Activity.RESULT_OK;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: MineFragment
 * @Description: 我的页面
 * @Author: Grechur
 * @CreateDate: 2020/5/14 14:22
 */
public class MineFragment extends BaseReFragment<MineViewModel, EntryFragmentMineBinding> {

    private OptionsInfo userOption;
    private String userName;
    private static final int REQUEST_CODE_IMAGE = 100;
    @Override
    protected void initView(Bundle savedInstanceState) {
        userName = SpUtils.getString(getContext(),"userName","");
        userOption = getUserOption();
        binding.entryUser.setOptions(userOption);
        binding.entryCollect.setOptions(getCollectOption());
        binding.entrySet.setOptions(getSetOption());
        binding.entryRank.setOptions(getRankOption());
        binding.entryCollect.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadService.showSuccess();
            }
        },100);
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
                    if(TextUtils.isEmpty(SpUtils.getString(getContext(),"userImg",""))){
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        );
                        startActivityForResult(intent,REQUEST_CODE_IMAGE);
                    }
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
//                if(TextUtils.isEmpty(userName)){
//                    ARouter.getInstance()
//                            .build(RouterSchame.LOGIN_ACTIVITY)
//                            .navigation();
//                } else {
//                    ARouter.getInstance()
//                            .build(RouterSchame.COLLECT_ACTIVITY)
//                            .navigation();
//                }
                gotoCollect();
            }
        });
        return optionsInfo;
    }

    private void gotoCollect() {
        ARouter.getInstance()
                .build(RouterSchame.COLLECT_ACTIVITY)
                .navigation(getContext(),new LoginNavigationCallbackImpl());
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

    private OptionsInfo getRankOption(){
        OptionsInfo optionsInfo = new OptionsInfo();
        optionsInfo.setDrawableId(R.drawable.entry_rank);
        optionsInfo.setText("排行榜");
        optionsInfo.setShowRight(true);
        optionsInfo.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), RankActivity.class);
                startActivity(intent);
            }
        });
        return optionsInfo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE_IMAGE){
                handleImageRequestResult(data);
            }
        }
    }

    private void handleImageRequestResult(Intent data) {
        Uri imageUri = null;
        if(data!=null) {
            if (Build.VERSION.SDK_INT >= 16) {
                if(data.getClipData()!=null){
                    imageUri = data.getClipData().getItemAt(0).getUri();
                }else if(data.getData()!=null){
                    imageUri = data.getData();
                }
            }else{
                if(data.getData()!=null){
                    imageUri = data.getData();
                }
            }
        }
        if(imageUri == null){
            return;
        }
        Log.e("mine","imageUri:"+imageUri.toString());
        Intent filterIntent = new Intent(getContext(), FilterActivity.class);
        filterIntent.putExtra("KEY_IMAGE_URI",imageUri.toString());
        startActivity(filterIntent);
    }
}
