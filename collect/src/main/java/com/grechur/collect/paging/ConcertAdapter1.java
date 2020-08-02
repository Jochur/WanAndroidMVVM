package com.grechur.collect.paging;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.collect.R;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: ConcertAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/6/13 13:31
 */
public class ConcertAdapter1 extends PagedListAdapter<Concert, ConcertAdapter1.ConcertViewHolder> {
    protected ConcertAdapter1() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ConcertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.collect_item_concert_again, parent, false);
        return new ConcertViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ConcertViewHolder holder, int position) {
        Concert concert = getItem(position);
        ViewDataBinding binding = holder.binding;
        binding.setVariable(BR.concert,concert);
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
        private ViewDataBinding binding;
        public ConcertViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }
}
