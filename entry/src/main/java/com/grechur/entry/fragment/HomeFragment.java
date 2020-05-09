package com.grechur.entry.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.base.BaseFragment;
import com.grechur.entry.R;
import com.grechur.entry.databinding.EntryFragmentHomeBinding;
import com.grechur.entry.viewmodel.HomeViewModel;

public class HomeFragment extends BaseFragment<HomeViewModel, EntryFragmentHomeBinding> {


    @Override
    protected void initView(Bundle savedInstanceState) {

        binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



    }

    @Override
    protected int getLayoutId() {
        return R.layout.entry_fragment_home;
    }
}
