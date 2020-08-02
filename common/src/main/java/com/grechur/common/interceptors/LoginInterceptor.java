package com.grechur.common.interceptors;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.SpUtils;

@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {
    private Context context;
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        if(TextUtils.isEmpty(SpUtils.getString(context,"userName",""))){
            switch (path){
                case RouterSchame.LOGIN_ACTIVITY:
                    callback.onContinue(postcard);
                    break;
                default:
                    callback.onInterrupt(null);
                    break;
            }
        }else{
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        this.context = context.getApplicationContext();
    }
}
