package com.grechur.common.callback;

import com.grechur.common.R;
import com.kingja.loadsir.callback.Callback;

public class LoadingCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.load_view;
    }
}
