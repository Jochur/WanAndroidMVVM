package com.grechur.collect.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.collect.R;
import com.grechur.collect.adapter.CollectWebAdapter;
import com.grechur.collect.bean.CollectWebInfo;
import com.grechur.collect.databinding.CollectFragmentWebBinding;
import com.grechur.collect.view.DeleteDialogFragment;
import com.grechur.collect.view.EditDialogFragment;
import com.grechur.collect.viewmodel.CollectWebViewModel;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.contant.Constants;
import com.grechur.common.contant.RouterSchame;
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
public class CollectWebFragment extends BaseFragment<CollectWebViewModel, CollectFragmentWebBinding> implements OnItemClickListener {

    private CollectWebAdapter mAdapter;
    private List<CollectWebInfo> mData;

    private int mPosition = -1;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mData = new ArrayList<>();
        mAdapter = new CollectWebAdapter(getContext(),mData);
        binding.collectRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.collectRecycleView.setAdapter(mAdapter);

        viewModel.mWebData.observe(this, new Observer<List<CollectWebInfo>>() {
            @Override
            public void onChanged(List<CollectWebInfo> collectWebInfos) {
                if(collectWebInfos!=null&&!collectWebInfos.isEmpty()){
                    mData.addAll(collectWebInfos);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e != null){
                    ToastUtils.show(e.getMessage());
                }
            }
        });

        viewModel.mWebInfo.observe(this, new Observer<CollectWebInfo>() {
            @Override
            public void onChanged(CollectWebInfo webInfo) {
                if(webInfo!=null){
                    mData.remove(mPosition);
                    mData.add(mPosition,webInfo);
                    mAdapter.notifyDataSetChanged();
                    mPosition = -1;
                }
            }
        });

        viewModel.delete.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mData.remove(mPosition);
                mAdapter.notifyDataSetChanged();
                mPosition = -1;
            }
        });

        mAdapter.setItemClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.collect_fragment_web;
    }

    @Override
    public void onItemClick(View view, final int position) {
        if(view!=null){
            Log.e("itemClick",view.toString());
            int id = view.getId();
            String resourceName = getResources().getResourceName(id);
            String resourceEntryName = getResources().getResourceEntryName(id);
            String resourcePackageName = getResources().getResourcePackageName(id);
            getResources().getResourceTypeName(id);
            Log.e("itemClick","view name:"+resourceName+" resourceEntryName:"+resourceEntryName);
            Log.e("itemClick","resourcePackageName:"+resourcePackageName+" ");
        }
        final CollectWebInfo webInfo = mData.get(position);
        if(view.getId() == R.id.rl_web) {
            ARouter.getInstance()
                    .build(RouterSchame.WEB_VIEW_ACTIVITY)
                    .withString(Constants.INTENT_TITLE, webInfo.getName())
                    .withString(Constants.INTENT_URL, webInfo.getLink())
                    .navigation();
        }else if(view.getId() == R.id.iv_delete){

            DeleteDialogFragment dialogFragment = new DeleteDialogFragment(webInfo.getName());
            dialogFragment.setListener(new DeleteDialogFragment.OnSureClickListener() {
                @Override
                public void onSureClick(View view) {
                    mPosition = position;
                    viewModel.deletWeb(webInfo.getId());
                }
            });
            dialogFragment.show(getChildFragmentManager(),"delete");
        }else if(view.getId() == R.id.iv_edit){
            EditDialogFragment editDialogFragment = new EditDialogFragment(webInfo.getName(),webInfo.getLink());
            editDialogFragment.setListener(new EditDialogFragment.OnSureClickListener() {
                @Override
                public void onSureClick(View view, String name, String link) {
                    if(!TextUtils.equals(name,webInfo.getName())||!TextUtils.equals(link,webInfo.getLink())) {
                        mPosition = position;
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", webInfo.getId());
                        map.put("name", name);
                        map.put("link", link);
                        viewModel.updateWeb(map);
                    }else{
                        ToastUtils.show("编辑内容没有改变");
                    }
                }
            });
            editDialogFragment.show(getChildFragmentManager(),"edit");
        }
    }
}
