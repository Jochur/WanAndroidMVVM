package com.grechur.common.arouter.news;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IHomeService extends IProvider {
    String HOME_ROUTER = "/entry/";
    String HOME_SERVICE = HOME_ROUTER + "home_service";
    Fragment getHomeFragment();
}
