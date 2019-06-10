package com.jiayang.mvp.mvpframework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jiayang.mvp.mvpframework.utils.LogUtils;

import static java.lang.Math.PI;

/**
 * 蜘蛛网格
 *
 * @author ：张 奎
 * @date ：2019-06-04 17：01
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class SpiderGrid extends View {

    private float mRadius;
    private Paint mRadarPaint;
    private Paint mValuePaint;
    private int mCenterX;
    private int mCenterY;
    private int mCount = 6;
    private double mAngle = 2 * PI / 360 * 60;
    private double[] mData =  {3,5,1,6,4,5};

    public SpiderGrid(Context context) {
        this(context, null);
    }

    public SpiderGrid(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpiderGrid(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpiderGrid(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init();
    }

    private void init() {
        mRadarPaint = new Paint();
        mRadarPaint.setStyle(Paint.Style.STROKE);
        mRadarPaint.setColor(Color.BLACK);


        mValuePaint = new Paint();
        mValuePaint.setColor(Color.BLUE);
        mValuePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        LogUtils.e("onSizeChanged");

        mRadius = Math.min(w, h) / 2 * 0.9f;
        mCenterX = w / 2;
        mCenterY = h / 2;
        postInvalidate();


        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制蜘蛛网格
        drawPolygon(canvas);
        // 绘制网格中线
        drawLines(canvas);
        // 绘制数据图
        drawRegion(canvas);


    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = mRadius / mCount;

        for (int i = 1; i <= mCount; i++) {
            float mCurrentR = i * r;
            path.reset();

            for (int j = 0; j < mCount; j++) {
                if (j == 0) {
                    path.moveTo(mCenterX + mCurrentR, mCenterY);
                } else {
                    float x = (float) (mCenterX + mCurrentR * Math.cos(mAngle * j));
                    float y = (float) (mCenterY + mCurrentR * Math.sin(mAngle * j));

                    path.lineTo(x, y);
                }
            }

            path.close();
            canvas.drawPath(path, mRadarPaint);

        }

    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < mCount; i++) {
            path.reset();

            path.moveTo(mCenterX, mCenterY);
            float x = (float) (mCenterX + mRadius * Math.cos(mAngle * i));
            float y = (float) (mCenterY + mRadius * Math.sin(mAngle * i));
            path.lineTo(x,y);
            canvas.drawPath(path,mRadarPaint);
        }

    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        mValuePaint.setAlpha(127);
        for (int i = 0; i < mCount; i++) {
            double percent = mData[i] / mCount;

            float x = (float) (mCenterX + mRadius * Math.cos(mAngle * i) * percent);
            float y = (float) (mCenterY + mRadius * Math.sin(mAngle * i) * percent);


            if (i == 0) {
                path.moveTo(x, mCenterY);
            } else {
                path.lineTo(x, y);
            }

            canvas.drawCircle(x,y,10,mValuePaint);
        }

        mValuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path,mValuePaint);

    }
}
