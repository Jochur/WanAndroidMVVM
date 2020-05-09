package com.grechur.common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.launcher.ARouter;
import com.grechur.common.BR;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: BaseFragment
 * @Description: 基础fragment
 * @Author: Grechur
 * @CreateDate: 2020/5/9 17:23
 */
public abstract class BaseFragment<VM extends BaseViewModel,VDB extends ViewDataBinding> extends Fragment {
    protected VM viewModel;
    protected VDB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        binding = DataBindingUtil.inflate(inflater, getLayoutId(),container,false);
        //所有布局中dababinding对象变量名称都是vm
        viewModel = new ViewModelProvider(getActivity()).get(getTClass());
        binding.setVariable(BR.vm,viewModel);
        initView(savedInstanceState);
        return binding.getRoot();
    }



    private Class<VM> getTClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            if(actualTypeArguments.length>0) {
                return (Class<VM>) actualTypeArguments[0];
            }
        }
        return null;
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getLayoutId();
}
