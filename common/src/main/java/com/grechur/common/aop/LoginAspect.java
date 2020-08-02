package com.grechur.common.aop;

import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.Applications;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.SpUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LoginAspect{

    @Pointcut("execution(@com.grechur.common.aop.LoginSection *  *(..))")
    public void methodAnnotationLoginSection(){
    }

    @Around("methodAnnotationLoginSection()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        String value = SpUtils.getString(Applications.getCurrent(),"userName","");
        if(TextUtils.isEmpty(value)){
            ARouter.getInstance()
                    .build(RouterSchame.LOGIN_ACTIVITY)
                    .navigation();
            return null;
        }
        if(joinPoint == null){
            return null;
        }
        return joinPoint.proceed();
    }
}
