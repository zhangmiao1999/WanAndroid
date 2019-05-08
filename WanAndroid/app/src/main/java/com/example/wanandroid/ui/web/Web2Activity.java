package com.example.wanandroid.ui.web;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.SimpleActivity;

import java.lang.reflect.Method;

import butterknife.BindView;

public class Web2Activity extends SimpleActivity {


    @BindView(R.id.toolBarTitle)
    TextView mToolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.web)
    WebView mWeb;
    private String mUrl;
    private String mTitle;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_web2;
    }

    @Override
    protected void initView() {
        getUrl();
        setToolBar(mToolBar);
        setToolBarTitle(mToolBarTitle, mTitle);
        WebSettings settings = mWeb.getSettings();
        //支持javascript
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        //settings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        settings.setUseWideViewPort(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(mUrl);


    }

    @Override
    protected void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getUrl() {
        mUrl = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web2_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // 让菜单同时显示图标和文字
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                share();
                break;
            case R.id.browser:
                openBrowse();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openBrowse() {
        Uri uri = Uri.parse(mUrl);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(uri);
        startActivity(intent);
    }

    private void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "掌上生活");
        intent.putExtra(Intent.EXTRA_TEXT, "文本");
        Web2Activity.this.startActivity(Intent.createChooser(intent, "分享"));
    }
}
