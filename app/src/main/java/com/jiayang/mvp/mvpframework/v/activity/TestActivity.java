package com.jiayang.mvp.mvpframework.v.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.TestActivityPst;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.v.iview.TestActivityViewIpm;

import butterknife.BindView;
import butterknife.OnClick;

public class TestActivity extends BaseActivity<TestActivityPst> implements TestActivityViewIpm {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.activity_test)
    RelativeLayout activityTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //        String test = getIntent().getStringExtra("test");

        final int[] i = {0};
        /**
         * 部分文字点击
         */
        //        SpannableString spannableString = new SpannableString("测试  C8888820181114000002 复制");
        //        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 2, spannableString.length()-2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        ////        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),  spannableString.length()-2,spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //        spannableString.setSpan(new ClickableSpan() {
        //            @Override
        //            public void onClick(View widget) {
        ////                ToastUtils.initToast("点击了复制");
        //                LogUtils.e(String.valueOf(i[0]++));
        //
        //                avoidHintColor(widget);
        //            }
        //            @Override
        //            public void updateDrawState(TextPaint ds) {
        //                super.updateDrawState(ds);
        //                ds.setColor(Color.BLUE); //设置颜色
        //                ds.setUnderlineText(false);
        //            }
        //        }, spannableString.length()-2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //        name.setText(spannableString);
        //
        //        name.setMovementMethod(LinkMovementMethod.getInstance());  //很重要，点击无效就是由于没有设置这个引起


        /**
         * 文字改图片 并点击图片
         */
        Drawable drawable = getResources().getDrawable(R.mipmap.pay);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        ImageSpan imageSpan = new ImageSpan(drawable);

        SpannableString spannableString = new SpannableString("测试  C8888820181114000002 复制");
        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 2, spannableString.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
        //                ToastUtils.initToast("点击了复制");
                        LogUtils.e(String.valueOf(i[0]++));
                        Intent dialIntent =  new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "123456789"));//直接拨打电话
                        startActivity(dialIntent);

                        avoidHintColor(widget);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(false);
                    }
                }, spannableString.length()-2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        name.setText(spannableString);
        name.setMovementMethod(LinkMovementMethod.getInstance());  //很重要，点击无效就是由于没有设置这个引起
    }


    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }



    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void showA(String b) {
        Toast.makeText(context, b, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
    }
}
