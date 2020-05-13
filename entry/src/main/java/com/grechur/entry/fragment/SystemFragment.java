package com.grechur.entry.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.internal.FlowLayout;
import com.grechur.common.base.BaseFragment;
import com.grechur.common.contant.RouterSchame;
import com.grechur.common.util.toast.ToastUtils;
import com.grechur.entry.R;
import com.grechur.entry.SystemArticleActivity;
import com.grechur.entry.adapter.SystemAdapter;
import com.grechur.entry.bean.Children;
import com.grechur.entry.databinding.EntryFragmentSystemBinding;
import com.grechur.entry.view.TagView;
import com.grechur.entry.viewmodel.SystemArticleViewModel;
import com.grechur.entry.viewmodel.SystemViewModel;
import com.grechur.net.ApiException;

import java.util.List;


/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemFragment
 * @Description: 体系fragment
 * @Author: Grechur
 * @CreateDate: 2020/5/12 11:42
 */
public class SystemFragment extends BaseFragment<SystemViewModel, EntryFragmentSystemBinding> {

    private SystemAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {

        mAdapter = new SystemAdapter(getContext(),viewModel.mLeftData);

        binding.recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycleView.setAdapter(mAdapter);


        viewModel.mData.observe(this, new Observer<List<Children>>() {
            @Override
            public void onChanged(List<Children> children) {
                if(children!=null&&!children.isEmpty()){
                    viewModel.mLeftData.addAll(children);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        viewModel.mError.observe(this, new Observer<ApiException>() {
            @Override
            public void onChanged(ApiException e) {
                if(e!=null){
                    ToastUtils.show(e.getMessage());
                }
            }
        });

        mAdapter.setListener(new SystemAdapter.OnItemChangeListener() {
            @Override
            public void onItemChange(List<Children> rightData, int position) {
                binding.systemFlexBox.removeAllViews();
                if(rightData!=null&&!rightData.isEmpty()){
                    for (final Children rightDatum : rightData) {
                        TagView tagView = new TagView(getContext());
                        tagView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent();
                                intent.setClass(getContext(), SystemArticleActivity.class)
                                        .putExtra("cid",rightDatum.getId())
                                        .putExtra("system_title",rightDatum.getName());
                                startActivity(intent);
                            }
                        });
                        tagView.setData(rightDatum);
                        binding.systemFlexBox.addView(tagView);
                    }
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_system;
    }


}
