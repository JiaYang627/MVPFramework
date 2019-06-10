package com.jiayang.mvp.mvpframework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author ：张 奎
 * @date ：2019-06-10 16：18
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class TextViews extends View {
    private int mClickType;

    public TextViews(Context context) {
        this(context,null);
    }

    public TextViews(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TextViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViews(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    public void onClick(int mClickType) {
        this.mClickType = mClickType;
        invalidate();
    }

    private void initPaint() {

    }
}
