package com.grechur.entry;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.grechur.common.arouter.news.IHomeService;
import com.grechur.entry.fragment.HomeFragment;

@Route(path = IHomeService.HOME_SERVICE)
public class HomeService implements IHomeService {
    @Override
    public void init(Context context) {

    }

    @Override
    public Fragment getHomeFragment() {
        return new HomeFragment();
    }
}
