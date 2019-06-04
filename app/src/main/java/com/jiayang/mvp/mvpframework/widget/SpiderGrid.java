package com.jiayang.mvp.mvpframework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 * 蜘蛛网格
 *
 * @author ：张 奎
 * @date ：2019-06-04 17：01
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class SpiderGrid extends View {
    public SpiderGrid(Context context) {
        this(context,null);
    }

    public SpiderGrid(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpiderGrid(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpiderGrid(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
