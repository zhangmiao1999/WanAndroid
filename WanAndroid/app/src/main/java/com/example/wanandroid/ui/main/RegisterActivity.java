package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    private String mName;
    private String mPsd;
    private String mOkPsd1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_back, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_register:
                mName = mEdEmail.getText().toString().trim();
                mPsd = mEdPsd.getText().toString().trim();
                mOkPsd1 = mOkPsd.getText().toString().trim();
                if (TextUtils.isEmpty(mName)||TextUtils.isEmpty(mOkPsd1)||TextUtils.isEmpty(mPsd)){
                    ToastUtil.showShort("不能为空");
                    return;
                }else {
                    if (mPsd.equals(mOkPsd1)){
                        ToastUtil.showShort("注册成功");
                        Intent intent = new Intent();
                        intent.putExtra("name",mName);
                        intent.putExtra("psd",mPsd);
                        setResult(250,intent);
                        finish();
                    }else {
                        ToastUtil.showShort("请确认两次密码是否一致");
                    }
                }

                break;
        }
    }
}
