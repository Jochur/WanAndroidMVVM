package com.grechur.entry.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.entry.R;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: TestAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/10 15:48
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    private List<String> mData;
    private Context mContext;

    public TestAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.entry_item_test,parent,false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null?0:mData.size();
    }

    public static class TestViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
