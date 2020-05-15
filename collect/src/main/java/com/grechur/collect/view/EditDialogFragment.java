package com.grechur.collect.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.grechur.collect.R;
import com.grechur.common.base.BaseDialogFragment;

/**
 * @ProjectName: WanAndroidMVVM
 * @ClassName: DeleteDialogFragment
 * @Description: 删除确认弹窗
 * @Author: Grechur
 * @CreateDate: 2020/5/15 16:03
 */
public class EditDialogFragment extends BaseDialogFragment {
    private EditText et_edit_name;
    private EditText et_edit_link;
    private String link;
    private String name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景透明
        getDialog().getWindow().setWindowAnimations(R.style.common_bottom_anim);//添加一组进出动画
        View view = inflater.inflate(R.layout.collect_edit_dialog, container, false);
        et_edit_name = view.findViewById(R.id.et_edit_name);
        et_edit_link = view.findViewById(R.id.et_edit_link);

        et_edit_link.setText(link);
        et_edit_name.setText(name);

        TextView cancel = view.findViewById(R.id.tv_edit_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        TextView sure = view.findViewById(R.id.tv_edit_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.onSureClick(v,et_edit_name.getText().toString(),et_edit_link.getText().toString());
                dismiss();
            }
        });
        return view;
    }

    public EditDialogFragment(String name,String link){
        this.name = name;
        this.link = link;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置弹出框宽屏显示，适应屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels*2/3, getDialog().getWindow().getAttributes().height);
        //移动弹出菜单到底部
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(wlp);
    }

    public OnSureClickListener listener;

    public void setListener(OnSureClickListener listener) {
        this.listener = listener;
    }

    public interface OnSureClickListener{
        void onSureClick(View view,String name,String link);
    }
}
