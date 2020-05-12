package com.grechur.entry.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.grechur.common.base.BaseAdapter;
import com.grechur.common.base.BaseViewHolder;
import com.grechur.entry.BR;
import com.grechur.entry.R;
import com.grechur.entry.bean.Children;

import java.util.List;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: SystemAdapter
 * @Description:
 * @Author: Grechur
 * @CreateDate: 2020/5/12 14:50
 */
public class SystemAdapter extends BaseAdapter<Children,BaseViewHolder> {

    public int lastPosition = -1;

    public SystemAdapter(Context mContext, List<Children> mData){
        super(mContext,mData);
    }

    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(mInflater, R.layout.entry_system_item, parent, false);
        return new BaseViewHolder(binding);
    }


    @Override
    protected void onBindVH(BaseViewHolder holder, int position) {
        Children children = mData.get(position);
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.children,children);
        binding.setVariable(BR.adapter,this);
        binding.setVariable(BR.position,position);
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    public void itemClick(Children children, int position){
        if(position!=lastPosition){
            if(lastPosition>-1) {
                Children lastChildren = mData.get(lastPosition);
                notifyItemChanged(lastPosition);
                lastChildren.setHasSelect(!lastChildren.isHasSelect());
            }
            lastPosition = position;
            children.setHasSelect(true);
            if(listener!=null) listener.onItemChange(children.getChildren(),position);
            notifyItemChanged(position);
        }else{
            return;
        }

    }

    @Override
    public int getItemCount() {
        return mData.isEmpty()?0:mData.size();
    }

    private OnItemChangeListener listener;

    public void setListener(OnItemChangeListener listener) {
        this.listener = listener;
    }

    public interface OnItemChangeListener{
        void onItemChange(List<Children> rightData,int position);
    }
}
