package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.app.App;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.ui.homepage.fragment.HomePageFragment;
import com.example.wanandroid.ui.knowledge.fragment.KnowledgeFragment;
import com.example.wanandroid.ui.navigation.fragment.NavigationFragment;
import com.example.wanandroid.ui.project.fragment.ProjectFragment;
import com.example.wanandroid.ui.setting.SettingFragment;
import com.example.wanandroid.ui.wx.fragment.OfficialFragment;
import com.example.wanandroid.util.CircularAnimUtil;
import com.example.wanandroid.util.SpUtil;
import com.example.wanandroid.util.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends SimpleActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.frameLayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.floatingButton)
    FloatingActionButton mFlaBut;
    @BindView(R.id.home_but)
    RadioButton mHomeBut;
    @BindView(R.id.knowledge_but)
    RadioButton mKnowledgeBut;
    @BindView(R.id.official_but)
    RadioButton mOfficialBut;
    @BindView(R.id.navigation_but)
    RadioButton mNavigationBut;
    @BindView(R.id.project_but)
    RadioButton mProjectBut;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.linearLayout)
    LinearLayout mLinearLayout;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.toolBarTitle)
    TextView mToolBarTitle;
    @BindView(R.id.i)
    TextView mI;
    @BindView(R.id.coord)
    CoordinatorLayout mCoord;
    private HomePageFragment mMainFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private OfficialFragment mOfficialFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private FragmentManager mManager;
    public static SettingFragment mSettingFragment;
    //用来获取的值
    public boolean mIsBoolean;

    public boolean isBoolean() {
        return mIsBoolean;
    }

    public FloatingActionButton getFlaBut() {
        return mFlaBut;
    }

    public RadioGroup getRadioGroup() {
        return mRadioGroup;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()) {
                    case R.id.wanAndroid:
                        mDl.closeDrawer(Gravity.LEFT);
                        mToolBarTitle.setText(R.string.homePage);
                        mManager.beginTransaction()
                                .show(mMainFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mOfficialFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .hide(mSettingFragment)
                                .commit();
                        mFlaBut.setVisibility(View.VISIBLE);
                        mRadioGroup.setVisibility(View.VISIBLE);
                        mHomeBut.setChecked(true);
                        break;
                    case R.id.collect:
                        startActivityForResult(new Intent(MainActivity.this, CollectActivity.class), 1);
                        break;
                    case R.id.setting:
                        mToolBarTitle.setText(R.string.setting);
                        mManager.beginTransaction().show(mSettingFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mOfficialFragment)
                                .hide(mNavigationFragment)
                                .hide(mMainFragment)
                                .hide(mMainFragment).commit();
                        mDl.closeDrawer(Gravity.LEFT);
                        mFlaBut.setVisibility(View.GONE);
                        mRadioGroup.setVisibility(View.GONE);
                        break;
                    case R.id.about:
                        startActivity(new Intent(MainActivity.this, AboutActivity.class));
                        break;
                }
                return false;
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home_but:
                        mToolBarTitle.setText(R.string.homePage);
                        mManager.beginTransaction().show(mMainFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mOfficialFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .hide(mSettingFragment)
                                .commit();
                        break;
                    case R.id.knowledge_but:
                        setToolBarTitle(mToolBarTitle, App.getInstance().getResources().getString(R.string.knowledge));
                        mManager.beginTransaction().show(mKnowledgeFragment)
                                .hide(mMainFragment)
                                .hide(mOfficialFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .hide(mSettingFragment)
                                .commit();
                        break;
                    case R.id.official_but:
                        setToolBarTitle(mToolBarTitle, App.getInstance().getResources().getString(R.string.official));
                        mManager.beginTransaction().show(mOfficialFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mMainFragment)
                                .hide(mNavigationFragment)
                                .hide(mProjectFragment)
                                .hide(mSettingFragment)
                                .commit();
                        break;
                    case R.id.navigation_but:
                        setToolBarTitle(mToolBarTitle, App.getInstance().getResources().getString(R.string.navigation));
                        mManager.beginTransaction().show(mNavigationFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mMainFragment)
                                .hide(mOfficialFragment)
                                .hide(mProjectFragment)
                                .hide(mSettingFragment)
                                .commit();
                        break;
                    case R.id.project_but:
                        setToolBarTitle(mToolBarTitle, App.getInstance().getResources().getString(R.string.project));
                        mManager.beginTransaction().show(mProjectFragment)
                                .hide(mKnowledgeFragment)
                                .hide(mOfficialFragment)
                                .hide(mNavigationFragment)
                                .hide(mMainFragment)
                                .hide(mSettingFragment)
                                .commit();
                        break;
                }
            }
        });
        mNv.getHeaderView(0).findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class),3);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            boolean isBoolean = data.getBooleanExtra("isBoolean", true);
            Log.d(TAG, "isBoolean: " + isBoolean);
            mIsBoolean = isBoolean;
        }else if (requestCode == 3 && resultCode == 300){
            TextView tv_login = mNv.getHeaderView(0).findViewById(R.id.tv_login);
            tv_login.setText(data.getStringExtra("name"));
        }
    }

    @Override
    public void initView() {
        mManager = getSupportFragmentManager();
        //设置ToolBar
        setToolBar(mToolBar);
        setToolBarTitle(mToolBarTitle, App.getInstance().getResources().getString(R.string.homePage));
        initDrawLayout();
        initFragment();

        TextView tv_login = mNv.getHeaderView(0).findViewById(R.id.tv_login);
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String name = sp.getString("name", "");
        boolean flag = sp.getBoolean("flag", false);
        if (flag){
            tv_login.setText(name);
        }
    }

    private void initFragment() {
        mMainFragment = new HomePageFragment();
        mKnowledgeFragment = new KnowledgeFragment();
        mOfficialFragment = new OfficialFragment();
        mNavigationFragment = new NavigationFragment();
        mProjectFragment = new ProjectFragment();
        mSettingFragment = new SettingFragment();
        FragmentTransaction transaction = mManager.beginTransaction();
        transaction.add(R.id.frameLayout, mMainFragment);
        transaction.add(R.id.frameLayout, mKnowledgeFragment);
        transaction.add(R.id.frameLayout, mOfficialFragment);
        transaction.add(R.id.frameLayout, mNavigationFragment);
        transaction.add(R.id.frameLayout, mProjectFragment);
        transaction.add(R.id.frameLayout, mSettingFragment);
        transaction.show(mMainFragment)
                .hide(mKnowledgeFragment)
                .hide(mOfficialFragment)
                .hide(mNavigationFragment)
                .hide(mProjectFragment)
                .hide(mSettingFragment)
                .commit();

    }

    private void initDrawLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDl, mToolBar, R.string.app_name, R.string.about) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDl.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.White));
        mDl.addDrawerListener(toggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.seek:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, WeekActivity.class);
                CircularAnimUtil.startActivity(this, intent, mI, R.color.colorWhite);
                break;
            case R.id.project:
                Intent intent1 = new Intent();
                intent1.setClass(MainActivity.this, CommonActivity.class);
                CircularAnimUtil.startActivity(this, intent1, mI, R.color.colorWhite);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();
        }
        return false;
    }

    /**
     * 双击退出
     */
    private static Boolean isExit = false;
    Timer tExit = null;

    private void exitBy2Click() {
        if (isExit == false) {
            isExit = true; //准备退出
            Snackbar snackbar = Snackbar.make(mCoord, "再按一次回退键退出WanAndroid", Snackbar.LENGTH_SHORT)
                    .setAction("知道了", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).setActionTextColor(Color.parseColor("#009688"));

            View snackbarView = snackbar.getView();//获取Snackbar显示的View对象
            //设置Snackbar的背景色
            snackbarView.setBackgroundColor(Color.BLACK);
            //获取显示文本View,并设置其显示颜色
            ((TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
            snackbar.show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

}
