package com.grechur.entry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.view.SelfLayout;
import com.grechur.common.view.WarrpRecyclerView;
import com.grechur.entry.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: TestActivity
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/9 17:11
 */
public class TestActivity extends AppCompatActivity {
//    private WarrpRecyclerView wrv;
//    private TestAdapter mAdapter;
//    private List<String> mData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_test);
//        wrv = findViewById(R.id.wrv);
//
//
//        mData = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            mData.add("我是"+i);
//        }
//        mAdapter = new TestAdapter(mData,this);
//
//        wrv.setLayoutManager(new LinearLayoutManager(this));
//        wrv.setAdapter(mAdapter);
    }
}
