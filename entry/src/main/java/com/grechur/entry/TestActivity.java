package com.grechur.entry;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.grechur.common.view.SelfLayout;
import com.grechur.common.view.WarrpRecyclerView;
import com.grechur.common.view.recycle.MyRecyclerView;
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
    private MyRecyclerView wrv;
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
        wrv = findViewById(R.id.wrv);
        wrv.setAdapter(new MyRecyclerView.Adapter() {
            @Override
            public View onCreateViewHolder(int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.entry_layout_item,parent,false);
                TextView textView = convertView.findViewById(R.id.textView);
                textView.setText("腾讯课堂"+position);
                return convertView;
            }

            @Override
            public View onBinderViewHolder(int position, View convertView, ViewGroup parent) {
                TextView textView = convertView.findViewById(R.id.textView);
                textView.setText("腾讯课堂"+position);
                return convertView;
            }

            @Override
            public int getItemViewType(int row) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public int getCount() {
                return 40;
            }

            @Override
            public int getHeight(int position) {
                return 100;
            }
        });
    }
}
