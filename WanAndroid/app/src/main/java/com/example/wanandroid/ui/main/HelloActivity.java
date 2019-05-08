package com.example.wanandroid.ui.main;

import android.content.Intent;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.wanandroid.R;
import com.example.wanandroid.base.SimpleActivity;

import butterknife.BindView;

public class HelloActivity extends SimpleActivity {


    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }

    private Handler handler = new Handler();
    int time = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_hello;
    }

    @Override
    protected void initView() {
        initData();

        inSkip();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    private void inSkip() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    startActivity(new Intent(HelloActivity.this, MainActivity.class));
                    finish();
                } else {
                    time--;
                    inSkip();
                }
            }
        }, 1000);
    }

    private void initData() {
        startAnimation(mOneAnimation, "W.json");
        startAnimation(mTwoAnimation, "A.json");
        startAnimation(mThreeAnimation, "N.json");
        startAnimation(mFourAnimation, "A.json");
        startAnimation(mFiveAnimation, "N.json");
        startAnimation(mSixAnimation, "D.json");
        startAnimation(mSevenAnimation, "R.json");
        startAnimation(mEightAnimation, "I.json");
        startAnimation(mNineAnimation, "O.json");
        startAnimation(mTenAnimation, "D.json");
    }

    private void cancelAnimation() {
        cancelAnimation(mOneAnimation);
        cancelAnimation(mTwoAnimation);
        cancelAnimation(mThreeAnimation);
        cancelAnimation(mFourAnimation);
        cancelAnimation(mFiveAnimation);
        cancelAnimation(mSixAnimation);
        cancelAnimation(mSevenAnimation);
        cancelAnimation(mEightAnimation);

        cancelAnimation(mNineAnimation);
        cancelAnimation(mTenAnimation);
    }

    private void startAnimation(LottieAnimationView mLottieAnimationView, String animationName) {
        mLottieAnimationView.setAnimation(animationName);
        mLottieAnimationView.playAnimation();
    }

    private void cancelAnimation(LottieAnimationView mLottieAnimationView) {
        if (mLottieAnimationView != null) {
            mLottieAnimationView.cancelAnimation();
        }
    }

}
