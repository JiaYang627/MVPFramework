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
 * @date ：2019-06-10 16：18
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class TextViews extends View {
    private int mClickType;
    private Paint mStylePaint;
    private Paint mAlignPaint;

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

        initTextStyle();
        initTextAlign();
    }

    /**
     * 初始化 字体 Style样式
     */
    private void initTextStyle() {
        mStylePaint = new Paint();
        mStylePaint.setColor(Color.RED);
        mStylePaint.setStrokeWidth(3);
        mStylePaint.setAntiAlias(true);
        mStylePaint.setTextSize(60);


    }

    /**
     * 初始化 字体 起始点位置
     */
    private void initTextAlign() {
        mAlignPaint = new Paint();
        mAlignPaint.setColor(Color.RED);
        mAlignPaint.setStrokeWidth(3);
        mAlignPaint.setAntiAlias(true);
        mAlignPaint.setTextSize(60);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mClickType) {
            case Constants.TEXT_VIEW_STYLE:
                mStylePaint.setStyle(Paint.Style.STROKE);
                canvas.drawText("路够黑， 光 才亮",10,100,mStylePaint);

                mStylePaint.setStyle(Paint.Style.FILL);
                canvas.drawText("路够黑， 光 才亮",10,300,mStylePaint);

                mStylePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawText("路够黑， 光 才亮",10,500,mStylePaint);

                break;
            case Constants.TEXT_VIEW_ALIGN:
                mAlignPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText("路够黑， 光 才亮",500,100,mAlignPaint);
                mStylePaint.setColor(Color.BLACK);
                canvas.drawCircle(500, 100, 10, mStylePaint);

                mAlignPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText("路够黑， 光 才亮",500,300,mAlignPaint);

                mAlignPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText("路够黑， 光 才亮",500,500,mAlignPaint);



                break;
        }
    }
}
