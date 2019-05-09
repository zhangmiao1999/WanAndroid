package com.example.wanandroid.ui.web;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.app.App;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.http.bean.Bean;
import com.example.wanandroid.http.bean.HomeListBean;
import com.example.wanandroid.util.DaoUtil;
import com.example.wanandroid.util.ToastUtil;

import java.lang.reflect.Method;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends SimpleActivity {

    private static final String TAG = "WebActivity";
    @BindView(R.id.toolBarTitle)
    TextView mToolBarTitle;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.item_iv_love)
    ImageView mItemIvLove;
    private String mUrl;
    private String mTitle;
    private List<Bean> mQuery;
    private String mNiceDate;
    private String mAuthor;
    private String mSuperChapterName;
    private String mChapterName;
    private int mId;
    private Bean mBean;
    private boolean isBoolean;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        getBean();
        //一上来查询数据库
        mQuery = DaoUtil.getBaseUtil().query();
        mItemIvLove.setImageResource(R.mipmap.ic_toolbar_like_n);
        for (int i = 0; i < mQuery.size(); i++) {
            //这是查询出来的link值
            String link = mQuery.get(i).getLink();
            //比较数据库和 web的link值
            if (link.equals(mUrl)) {
                mItemIvLove.setImageResource(R.mipmap.love_seleced);
            }
        }

        setToolBar(mToolBar);
        setToolBarTitle(mToolBarTitle, mTitle);
        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(mUrl);

        mBean = new Bean();
        mBean.setTitle(mTitle);
        mBean.setNiceDate(mNiceDate);
        mBean.setLink(mUrl);
        mBean.setId((long) mId);
        mBean.setCharName(mChapterName);
        mBean.setCharSuperName(mSuperChapterName);
        mBean.setAuthor(mAuthor);
    }

    @OnClick(R.id.item_iv_love)
    public void onViewClicked() {
        Drawable.ConstantState littleLove = getResources().getDrawable(R.mipmap.love_seleced).getConstantState();
        Drawable.ConstantState littleLike = getResources().getDrawable(R.mipmap.ic_toolbar_like_n).getConstantState();
        //当前图片
        Drawable.ConstantState nowPic = mItemIvLove.getDrawable().getConstantState();

        if (littleLove.equals(nowPic)) {
            //删除
            // TODO: 2019/5/8  收藏和webView还没有实现联动
            boolean delete = DaoUtil.getBaseUtil().delete(mBean);
            if (delete) {
                ToastUtil.showShort("取消收藏");
                List query = DaoUtil.getBaseUtil().query();
                Log.d(TAG, "删完之后数据库还剩: "+query.size());
                //改变状态为小黑心
                mItemIvLove.setImageResource(R.mipmap.ic_toolbar_like_n);
            } else {
                //状态还是红心
                mItemIvLove.setImageResource(R.mipmap.love_seleced);
            }
        } else if (littleLike.equals(nowPic)) {
            //要插入，
            long insert = DaoUtil.getBaseUtil().insert(mBean);
            if (insert == -1) {
                //改变状态为小红心
                mItemIvLove.setImageResource(R.mipmap.love_seleced);
                ToastUtil.showShort("收藏成功");
            } else {
                //否则状态还是小黑心
                mItemIvLove.setImageResource(R.mipmap.ic_toolbar_like_n);
                ToastUtil.showShort("收藏失败");
            }
        }

        //给一个布尔值监听状态通知页面同步
        isBoolean = true;
    }

    @Override
    protected void initListener() {
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isBoolean", isBoolean);
                setResult(200, intent);
                finish();
            }
        });
    }

    private void getBean() {
        mUrl = getIntent().getStringExtra("url");
        mTitle = getIntent().getStringExtra("title");
        mNiceDate = getIntent().getStringExtra("niceDate");
        mAuthor = getIntent().getStringExtra("author");
        mSuperChapterName = getIntent().getStringExtra("superChapterName");
        mChapterName = getIntent().getStringExtra("chapterName");
        mId = getIntent().getIntExtra("id", 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_option_menu, menu);
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
                openBrowser();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //打开系统浏览器
    private void openBrowser() {
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
        WebActivity.this.startActivity(Intent.createChooser(intent, "分享"));
    }

}
