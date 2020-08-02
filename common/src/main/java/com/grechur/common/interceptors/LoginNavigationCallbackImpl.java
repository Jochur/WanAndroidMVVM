package com.grechur.common.interceptors;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.contant.RouterSchame;

public class LoginNavigationCallbackImpl implements NavigationCallback {
    @Override
    public void onFound(Postcard postcard) {

    }

    @Override
    public void onLost(Postcard postcard) {

    }

    @Override
    public void onArrival(Postcard postcard) {

    }

    @Override
    public void onInterrupt(Postcard postcard) {
        String path = postcard.getPath();
        Bundle bundle = postcard.getExtras();
        // 拦截了
        ARouter.getInstance().build(RouterSchame.LOGIN_ACTIVITY)
                .with(bundle)
                .withString("path", path)
                .navigation();
    }
}
