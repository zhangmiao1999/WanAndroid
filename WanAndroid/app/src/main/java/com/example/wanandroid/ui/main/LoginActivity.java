package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.base.BasePresenter;
import com.example.wanandroid.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.ed_name)
    EditText mEdName;
    @BindView(R.id.ed_psd)
    EditText mEdPsd;
    @BindView(R.id.but_login)
    Button mButLogin;
    @BindView(R.id.but_register)
    Button mButRegister;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new BasePresenter() {
            @Override
            protected void initModel() {

            }
        };
    }


    @OnClick({R.id.iv_back, R.id.but_login, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_login:
                ToastUtil.showShort("登录成功");
                finish();
                break;
            case R.id.but_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
