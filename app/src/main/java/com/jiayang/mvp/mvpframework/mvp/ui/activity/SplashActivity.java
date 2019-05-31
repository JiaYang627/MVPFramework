package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.SplashViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.SplashPresenter;

import butterknife.BindView;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashViewIpm {


    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.fragment)
    FrameLayout mFragment;
    @BindView(R.id.nameTextView)
    TextView mTextView;


    private long SplashDuration = 2_000;
    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initText();
        mFragment.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorAndGoToOther();
            }
        }, 2_000);

    }

    private void initText() {

        SpannableString spannableString = new SpannableString("MVPFrameworkDemo");
        spannableString.setSpan(new TypefaceSpan("serif"), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTextView.setText(spannableString);
    }

    private void AnimatorAndGoToOther() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mFragment, "alpha", 1, 0)
                .setDuration(SplashDuration);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mPresenter.goToMain();
            }
        });
    }
}
