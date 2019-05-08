package com.example.wanandroid.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends SimpleActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ed_email)
    EditText mEdEmail;
    @BindView(R.id.ed_psd)
    EditText mEdPsd;
    @BindView(R.id.ok_psd)
    EditText mOkPsd;
    @BindView(R.id.but_register)
    Button mButRegister;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick({R.id.iv_back, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_register:
                ToastUtil.showShort("注册成功");
                break;
        }
    }
}
