package com.grechur.common.base;

import android.app.Application;
import android.content.Context;
import android.hardware.Camera;
import android.os.Build;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.BuildConfig;
import com.grechur.common.DefaultHttpService;
import com.grechur.common.callback.CustomCallback;
import com.grechur.common.callback.EmptyCallback;
import com.grechur.common.callback.ErrorCallback;
import com.grechur.common.callback.LoadingCallback;
import com.grechur.common.callback.TimeoutCallback;
import com.grechur.common.util.DevicesUtils;
import com.grechur.common.util.SSLSocketFactoryHelper;
import com.kingja.loadsir.core.LoadSir;


/**
 * @ProjectName: ToolsDemo
 * @ClassName: SelfApplication
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/4/24 16:46
 */
public class SelfApplication extends Application {
    private static Context context;

    public static Context getCurrent() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        //放在其他库初始化前
        //SSLSocketFactoryHelper.getSSLSocketFactory(this, SSLSocketFactoryHelper.CER_NAME), SSLSocketFactoryHelper.getHostVeritifer(),
        DefaultHttpService.initializeHttpService(this, AppConfig.baseUrl + "/", assembleUserAgent(), getVersionName(), "", "",
                getVersionCode(), "", SSLSocketFactoryHelper.getSSLSocketFactory(this, SSLSocketFactoryHelper.CER_NAME), SSLSocketFactoryHelper.getHostVeritifer(), DevicesUtils.getSupportedAbis());
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
    }

    private boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    private int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    private String assembleUserAgent() {
        StringBuilder sb = new StringBuilder();
        sb.append("os/").append("Android").append(" ");
        sb.append("model/").append(Build.MODEL).append(" ");
        sb.append("brand/").append(Build.BRAND).append(" ");
        sb.append("sdk/").append(Build.VERSION.SDK_INT).append(" ");
//        sb.append("applicationID/").append(BuildConfig.APPLICATION_ID).append(" ");
        return sb.toString();
    }

    private String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }

}
