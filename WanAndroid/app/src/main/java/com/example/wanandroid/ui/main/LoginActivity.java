package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
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

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_back, R.id.but_login, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_login:
                String name = mEdName.getText().toString().trim();
                String psd = mEdPsd.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psd)) {
                    ToastUtil.showShort("账号密码不能为空");
                    return;
                } else {
                    ToastUtil.showShort("登录成功");
                    Intent intent = new Intent();
                    intent.putExtra("name", name);
                    setResult(300, intent);
                    finish();
                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();

                    edit.putString("name", name);//et1String是Editext获取到的文本内容
                    edit.putBoolean("flag", true);
                    //提交
                    edit.commit();
                }
                break;
            case R.id.but_register:
                startActivityForResult(new Intent(this, RegisterActivity.class), 150);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 150 && resultCode == 250) {
            String name = data.getStringExtra("name");
            String psd = data.getStringExtra("psd");
            mEdPsd.setText(psd);
            mEdName.setText(name);
        }
    }
}
