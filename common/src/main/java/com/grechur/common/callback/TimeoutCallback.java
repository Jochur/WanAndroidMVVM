package com.grechur.common.callback;

import com.grechur.common.R;
import com.kingja.loadsir.callback.Callback;

public class TimeoutCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.error_view;
    }
}
