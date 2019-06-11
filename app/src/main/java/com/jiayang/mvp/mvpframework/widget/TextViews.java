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
    private Paint mTextStylePaint;
    private String mStr;

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

        mStr = "路够黑， 光 才亮";
        initTextPaintStyle();
        initTextAlign();
        initTextStyle();
    }

    /**
     * 初始化 字体 Style样式
     */
    private void initTextPaintStyle() {
        mStylePaint = new Paint();

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

    private void initTextStyle() {
        mTextStylePaint = new Paint();
        mTextStylePaint.setColor(Color.RED);
        mTextStylePaint.setAntiAlias(true);
        mTextStylePaint.setTextSize(60);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mClickType) {
            case Constants.TEXT_VIEW_PAINT_STYLE:

                mStylePaint.setColor(Color.RED);
                mStylePaint.setStyle(Paint.Style.STROKE);
                canvas.drawText(mStr,10,100,mStylePaint);

                mStylePaint.setStyle(Paint.Style.FILL);
                canvas.drawText(mStr,10,300,mStylePaint);

                mStylePaint.setStyle(Paint.Style.FILL_AND_STROKE);
                canvas.drawText(mStr,10,500,mStylePaint);

                break;
            case Constants.TEXT_VIEW_ALIGN:
                mAlignPaint.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(mStr,500,100,mAlignPaint);
                mStylePaint.setColor(Color.BLACK);
                canvas.drawCircle(500, 100, 10, mStylePaint);

                mAlignPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(mStr,500,300,mAlignPaint);

                mAlignPaint.setTextAlign(Paint.Align.RIGHT);
                canvas.drawText(mStr,500,500,mAlignPaint);



                break;
            case Constants.TEXT_VIEW_STYLE:
                canvas.drawText(mStr,500,100,mTextStylePaint);


                mTextStylePaint.setFakeBoldText(true);
                canvas.drawText(mStr,500,300,mTextStylePaint);


                mTextStylePaint.setFakeBoldText(false);
                // 下划线
                mTextStylePaint.setUnderlineText(true);
                // 删除线
                mTextStylePaint.setStrikeThruText(true);
                canvas.drawText(mStr, 500, 500, mTextStylePaint);

                mTextStylePaint.setUnderlineText(false);
                mTextStylePaint.setStrikeThruText(false);

                // 倾斜
                mTextStylePaint.setTextSkewX(-0.25f);
                canvas.drawText(mStr,10,100,mTextStylePaint);

                mTextStylePaint.setTextSkewX(0.25f);
                canvas.drawText(mStr,10,300,mTextStylePaint);


                mTextStylePaint.setTextSkewX(0);
                // 拉伸
                mTextStylePaint.setTextScaleX(2);
                mTextStylePaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(mStr,500,700,mTextStylePaint);

                break;
        }
    }
}
