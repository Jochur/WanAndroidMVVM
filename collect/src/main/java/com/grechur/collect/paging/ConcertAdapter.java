package com.grechur.collect.paging;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.collect.BR;
import com.grechur.collect.R;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConcertAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 13:31
 */
public class ConcertAdapter extends PagedListAdapter<Concert, ConcertAdapter.ConcertViewHolder> {
    protected ConcertAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ConcertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.collect_item_concert, parent, false);
        return new ConcertViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertViewHolder holder, int position) {
        Concert concert = getItem(position);
        holder.tv_name.setText(concert.getName());
        holder.tv_phone.setText(concert.getPhone());
    }

    private static DiffUtil.ItemCallback<Concert> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Concert>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Concert oldConcert, Concert newConcert) {
                    return oldConcert.getId() == newConcert.getId();
                }

                @Override
                public boolean areContentsTheSame(Concert oldConcert,
                                                  Concert newConcert) {
                    return oldConcert.getId()==newConcert.getId();
                }
            };

    public static class ConcertViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name;
        private TextView tv_phone;
        public ConcertViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_phone = view.findViewById(R.id.tv_phone);
        }

    }
}
