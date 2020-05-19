package com.grechur.entry.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.gson.reflect.TypeToken;
import com.grechur.common.Applications;
import com.grechur.common.util.GsonUtils;
import com.grechur.common.util.SpUtils;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.SearchResultActivity;
import com.grechur.entry.bean.ArticleInfo;
import com.grechur.entry.bean.HotBean;
import com.grechur.entry.bean.SearchBean;
import com.grechur.entry.databinding.EntrySearchItemViewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SearchItemView
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/19 14:14
 */
public class SearchItemView extends FrameLayout {

    private EntrySearchItemViewBinding binding;

    public SearchItemView(@NonNull Context context) {
        this(context,null);
    }

    public SearchItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SearchItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
    }

    private void initView(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.entry_search_item_view, null, false);
        addView(binding.getRoot());
    }

    public void setData(SearchBean searchBean){
        binding.setVariable(BR.bean,searchBean);

        binding.flexLayout.removeAllViews();
        SearchBean.SearchItem item = searchBean.getItem();
        List<String> history = item.getHistory();
        List<HotBean> hot = item.getHot();
        if(history != null && !history.isEmpty()){
            for (final String s : history) {
                TagView tagView = new TagView(getContext());
                tagView.setData(s);
                tagView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(getContext(), SearchResultActivity.class);
                        intent.putExtra("key",s);
                        getContext().startActivity(intent);
                    }
                });
                binding.flexLayout.addView(tagView);
            }
        }
        if(hot != null && !hot.isEmpty()){
            for (final HotBean bean : hot) {
                TagView tagView = new TagView(getContext());
                tagView.setData(bean);
                tagView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> history1 = getHistory();
                        if(history1 == null){
                            history1 = new ArrayList<>();
                        }
                        history1.add(bean.getName());
                        SpUtils.saveString(getContext(),"search_his",GsonUtils.createArrayToString(history1));
                        Intent intent = new Intent();
                        intent.setClass(getContext(), SearchResultActivity.class);
                        intent.putExtra("key",bean.getName());
                        getContext().startActivity(intent);
                    }
                });
                binding.flexLayout.addView(tagView);
            }
        }
    }


    public List<String> getHistory(){
        String  json = SpUtils.getString(getContext(), "search_his", "");
        if(!TextUtils.isEmpty(json)) {
            List<String> history = GsonUtils.changeGsonToList(json);
            if (history != null && !history.isEmpty()) {
                return history;
            }
        }
        return null;
    }
}
