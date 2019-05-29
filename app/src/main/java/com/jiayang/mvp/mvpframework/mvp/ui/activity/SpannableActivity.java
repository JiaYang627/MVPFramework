package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.SpannableViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.SpannablePresenter;
import com.jiayang.mvp.mvpframework.utils.ToastUtilsBlankJ;

import butterknife.BindView;


/**
 * 测试 Spannable 文字图片混排 并带有点击事件
 */
public class SpannableActivity extends BaseActivity<SpannablePresenter> implements SpannableViewIpm {


    @BindView(R.id.testCopy)
    TextView mTestCopy;
    @BindView(R.id.testClick)
    TextView mTestClick;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spannable;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        onSpannableClick();
        onSpannablePhotoClick();
    }

    private void onSpannableClick() {
        /**
         * 部分文字点击
         */
        SpannableString spannableString = new SpannableString("测试  C8888820181114000002 复制");

        CharSequence charSequence = spannableString.subSequence(2, spannableString.length() - 2);
        final String trim = charSequence.toString().trim();


        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 2, spannableString.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        //        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE),  spannableString.length()-2,spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //                ToastUtils.initToast("点击了复制");
                copyToClipBoard(SpannableActivity.this, trim);
                avoidHintColor(widget);
                ToastUtilsBlankJ.showShort("复制成功");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE); //设置颜色
                ds.setUnderlineText(false);
            }
        }, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTestCopy.setText(spannableString);

        mTestCopy.setMovementMethod(LinkMovementMethod.getInstance());  //很重要，点击无效就是由于没有设置这个引起
    }

    private void onSpannablePhotoClick() {
        /**
         * 文字改图片 并点击图片
         */
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_phone);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        //居中对齐imageSpan
        CenterAlignImageSpan imageSpan = new CenterAlignImageSpan(drawable);

        SpannableString spannableString = new SpannableString("测试  123456789 复制");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#2A99FF")), 2, spannableString.length() - 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(imageSpan, spannableString.length() - 2, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //                ToastUtils.initToast("点击了复制");
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
        mTestClick.setText(spannableString);
        mTestClick.setMovementMethod(LinkMovementMethod.getInstance());  //很重要，点击无效就是由于没有设置这个引起

    }

    /**
     * 复制到剪切板
     *
     * @param context
     * @param str
     */
    public static void copyToClipBoard(Context context, String str) {

        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(str, str);
        clipboard.setPrimaryClip(clip);
    }
    /**
     * 处理点击后 点击文字背景色 (不处理 - 点击后是其他颜色)
     * @param view
     */
    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    /**
     * 处理SpannableString 图片文字混排 居中问题
     */
    public class CenterAlignImageSpan extends ImageSpan {

        public CenterAlignImageSpan(Drawable drawable) {
            super(drawable);

        }

        public CenterAlignImageSpan(Bitmap b) {
            super(b);
        }

        @Override
        public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom,
                         @NonNull Paint paint) {

            Drawable b = getDrawable();
            Paint.FontMetricsInt fm = paint.getFontMetricsInt();
            int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;//计算y方向的位移
            canvas.save();
            canvas.translate(x, transY);//绘制图片位移一段距离
            b.draw(canvas);
            canvas.restore();
        }
    }
}
