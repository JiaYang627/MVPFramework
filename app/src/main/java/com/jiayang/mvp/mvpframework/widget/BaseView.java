package com.jiayang.mvp.mvpframework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jiayang.mvp.mvpframework.common.Constants;

/**
 * @author ：张 奎
 * @date ：2019-05-30 16：38
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class BaseView extends View {

    private int mClickType;
    private Paint mLinePaint;
    private Paint mMoreLinePaint;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        init();
    }

    private void init() {

        initLinePaint();
        initMoreLinePaint();




    }

    /**
     * 初始化 单线条画笔
     */
    private void initLinePaint() {
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(5);
    }

    /**
     * 初始化 多线条画笔
     */
    private void initMoreLinePaint() {

        mMoreLinePaint = new Paint();

        mMoreLinePaint.setStrokeWidth(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        switch (mClickType) {
            case Constants.BASE_VIEW_SIGN_LINE:
                canvas.drawLine(10,10,100,100,mLinePaint);
                break;
            case Constants.BASE_VIEW_MORE_LINE:

                float[] pts = {10, 10, 100, 100, 200, 200, 400, 400};
                float[] ptsTwo = {10, 10, 200, 100, 300, 200, 500, 400};
                mMoreLinePaint.setColor(Color.BLACK);
                canvas.drawLines(pts, mMoreLinePaint);


                mMoreLinePaint.setColor(Color.RED);
                // offset:点数组中角标开始值  count:从开始值开始算 保留多少个值，此处 为：200，100，300，200
                canvas.drawLines(ptsTwo, 2, 4, mMoreLinePaint);
                break;
        }


    }

    public void onClick(int mClickType) {
        this.mClickType = mClickType;
        invalidate();
    }
}
