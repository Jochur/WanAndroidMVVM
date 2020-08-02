package com.grechur.collect.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.collect.R;
import com.grechur.collect.adapter.CollectWebAdapter;
import com.grechur.collect.adapter.CollectWebAdapter1;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.databinding.CollectFragmentWebAgainBinding;
import com.grechur.collect.databinding.CollectFragmentWebBinding;
import com.grechur.collect.view.DeleteDialogFragment;
import com.grechur.collect.view.EditDialogFragment;
import com.grechur.collect.viewmodel.CollectWebViewModel;
import com.grechur.collect.viewmodel.CollectWebViewModel1;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.callback.ErrorCallback;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.itemtouchhelper.DNItemTouchHelper;
import com.grechur.common.itemtouchhelper.ItemTouchHelperCallback;
import com.grechur.common.listener.OnItemClickListener;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.net.ApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: CollectWebFragment
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/15 10:20
 */
public class CollectWebFragment1 extends BaseFragment<CollectWebViewModel1, CollectFragmentWebAgainBinding>  {

    private CollectWebAdapter1 mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        loadService.showSuccess();
        mAdapter = new CollectWebAdapter1();
        binding.collectRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.collectRecycleView.setAdapter(mAdapter);

        viewModel.getWebList().observe(this, new Observer<PagedList<CollectWebInfo>>() {
            @Override
            public void onChanged(PagedList<CollectWebInfo> collectWebInfos) {
                loadService.showSuccess();
                mAdapter.submitList(collectWebInfos);
            }
        });



    }

    @Override
    protected int getLayoutId() {
        return R.layout.collect_fragment_web_again;
    }


}
