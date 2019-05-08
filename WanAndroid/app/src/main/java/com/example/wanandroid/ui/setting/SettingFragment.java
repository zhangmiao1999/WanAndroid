package com.example.wanandroid.ui.setting;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.app.constant.Constants;
import com.example.wanandroid.ui.main.MainActivity;
import com.example.wanandroid.util.SpUtil;
import com.example.wanandroid.util.ToastUtil;
import com.example.wanandroid.util.UIModeUtil;

/**
 * Created by 张嘉河 on 2019/5/4.
 */

public class SettingFragment extends Fragment {

    private View view;
    private CheckBox mSettingCache;
    private CheckBox mSwichImg;
    private CheckBox mSwichNight;
    private LinearLayout mLlSettingFeedback;
    /**
     * 200kb
     */
    private TextView mTvSettingClear;
    private LinearLayout mLlSettingClear;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_setting, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mSettingCache = (CheckBox) inflate.findViewById(R.id.setting_cache);
        mSwichImg = (CheckBox) inflate.findViewById(R.id.swich_img);
        mSwichNight = (CheckBox) inflate.findViewById(R.id.swich_night);
        mLlSettingFeedback = (LinearLayout) inflate.findViewById(R.id.ll_setting_feedback);
        mTvSettingClear = (TextView) inflate.findViewById(R.id.tv_setting_clear);
        mLlSettingClear = (LinearLayout) inflate.findViewById(R.id.ll_setting_clear);

        IfNightMode();
        mSwichImg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    SpUtil.setParam("img",isChecked);
                }else{
                    SpUtil.setParam("img",isChecked);
                }
            }
        });

        mSwichNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //切换模式
                //切换日夜间模式的时候Activity会重新创建
                //对应的这个碎片也会重建,重建的时候SwitchCompat会设置默认值
                //设置默认值的时候这个回调会被调用
                //if (用户点击的情况下){
                if (buttonView.isPressed()) {
                    //切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS, MainActivity.mSettingFragment);
                }
            }
        });

    }

    private void IfNightMode() {
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            mSwichNight.setChecked(false);
        } else {
            mSwichNight.setChecked(true);
        }
    }

}
