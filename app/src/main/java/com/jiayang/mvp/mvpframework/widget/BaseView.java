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
    private Paint mSignPoint;
    private Paint mMorePoint;
    private Paint mRectPaint;
    private Paint mRoundPaint;
    private Paint mCirclePaint;
    private Paint mOvalPaint;
    private Paint mArcPaint;

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);


        init();
    }

    public void onClick(int mClickType) {
        this.mClickType = mClickType;
        invalidate();
    }

    private void init() {

        initLinePaint();
        initMoreLinePaint();


        initSignPoint();
        initMorePoint();


        initRect();
        initRoundRect();

        initCircle();
        initOval();

        initArc();
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

    /**
     * 初始化 单点 画笔
     */
    private void initSignPoint() {

        mSignPoint = new Paint();
        mSignPoint.setStrokeWidth(15);
        mSignPoint.setColor(Color.BLACK);
    }

    /**
     * 初始化 多点画笔
     */
    private void initMorePoint() {

        mMorePoint = new Paint();
        mMorePoint.setStrokeWidth(15);

    }

    /**
     * 初始化 矩形画笔
     */
    private void initRect() {

        mRectPaint = new Paint();
        mRectPaint.setStrokeWidth(10);
        mRectPaint.setColor(Color.RED);
    }

    /**
     * 初始化 圆角画笔
     */
    private void initRoundRect() {

        mRoundPaint = new Paint();
        mRoundPaint.setStrokeWidth(10);
        mRoundPaint.setColor(Color.RED);
        mRoundPaint.setAntiAlias(true);

    }

    /**
     * 初始化 圆画笔
     */
    private void initCircle() {

        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStrokeWidth(10);

    }

    /**
     * 初始化 椭圆画笔
     */
    private void initOval() {

        mOvalPaint = new Paint();
        mOvalPaint.setStrokeWidth(10);
        mOvalPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 初始化 弧画笔
     */
    private void initArc() {
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.RED);
        mArcPaint.setStrokeWidth(10);
        mArcPaint.setStyle(Paint.Style.STROKE);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        switch (mClickType) {
            case Constants.BASE_VIEW_SIGN_LINE:
                canvas.drawLine(10, 10, 100, 100, mLinePaint);
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
            case Constants.BASE_VIEW_SIGN_POINT:

                canvas.drawPoint(100, 200, mSignPoint);

                break;
            case Constants.BASE_VIEW_MORE_POINT:

                float[] point = {200, 200, 300, 300, 400, 400};
                mMorePoint.setColor(Color.RED);
                canvas.drawPoints(point, mMorePoint);

                float[] pointTwo = {100, 100, 300, 200, 400, 300};
                mMorePoint.setColor(Color.BLACK);
                // offset:点数组中角标开始值  count:从开始值开始算 保留多少个值，此处 为：300, 200, 400, 300
                canvas.drawPoints(pointTwo, 2, 4, mMorePoint);
                break;

            case Constants.BASE_VIEW_RECT:
                mRectPaint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(100, 20, 300, 220, mRectPaint);


                mRectPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(100, 320, 300, 520, mRectPaint);

                break;
            case Constants.BASE_VIEW_ROUND_RECT:


                mRoundPaint.setStyle(Paint.Style.STROKE);
                // rx ry 是4角椭圆生成时 椭圆的各轴半径
                canvas.drawRoundRect(200, 20, 400, 220, 10, 10, mRoundPaint);


                mRoundPaint.setStyle(Paint.Style.FILL);
                canvas.drawRoundRect(200, 320, 400, 520, 10, 10, mRoundPaint);
                break;

            case Constants.BASE_VIEW_CIRCLE:
                mCirclePaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(300, 300, 100, mCirclePaint);

                mCirclePaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(500, 300, 100, mCirclePaint);
                break;
            case Constants.BASE_VIEW_OVAL:

                mOvalPaint.setColor(Color.BLACK);
                canvas.drawRect(200, 100, 400, 400, mOvalPaint);


                mOvalPaint.setColor(Color.RED);
                canvas.drawOval(200, 100, 400, 400, mOvalPaint);

                break;

            case Constants.Base_VIEW_ARC:

                // 画笔 为 描边时 两种情况，useCenter:是否带弧的两边
                canvas.drawArc(200,100,400,300,0,180,false,mArcPaint);
                canvas.drawArc(500,100,700,300,0,180,true,mArcPaint);


                // 画笔 为 填充时 两种情况，useCenter:是否带弧的两边
                mArcPaint.setStyle(Paint.Style.FILL);
                canvas.drawArc(200,400,400,600,0,90,false,mArcPaint);
                canvas.drawArc(500,400,700,600,0,90,true,mArcPaint);

                break;
        }


    }
}
