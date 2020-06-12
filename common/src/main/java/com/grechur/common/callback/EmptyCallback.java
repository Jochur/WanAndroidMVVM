package com.grechur.common.callback;

import com.grechur.common.R;
import com.kingja.loadsir.callback.Callback;

public class EmptyCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.empty_view;
    }
}
