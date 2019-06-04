package com.jiayang.mvp.mvpframework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
    private Paint mRectContainsPaint;
    private int mX, mY;
    private Rect mRectContains;
    private Paint mLinePathPaint;
    private Path mLinePath;
    private Paint mArcPathPaint;
    private Path mArcPath;
    private Path mArcPathForce;
    private Paint mAddArcPaint;
    private Path mAddArcPath;
    private Paint mAddRectPaint;
    private Path mAddRectCWWPath;
    private Path mAddRectCWPath;
    private String mStr;
    private Paint mAddRoundRectPaint;
    private Path mAddRoundRectPath;
    private Path mPathFillTypeLeft;
    private Path mPathFillTypeTop;
    private Path mPathFillTypeRight;
    private Path mPathFillTypeBottom;
    private Paint mPathFillTypePaint;

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
        mX = mY = -1;
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
        initRectContains();

        initLinePath();
        initArcPath();
        initAddArcPath();
        initAddRectPath();
        initAddRoundRectPath();

        initPathFillType();
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

    /**
     * 初始化 矩形是否包含点 画笔
     */
    private void initRectContains() {
        mRectContainsPaint = new Paint();
        mRectContainsPaint.setStrokeWidth(10);
        mRectContainsPaint.setStyle(Paint.Style.STROKE);

        mRectContains = new Rect(100, 40, 300, 300);


    }

    /**
     * 初始化 直线路径 画笔
     */
    private void initLinePath() {

        mLinePathPaint = new Paint();
        mLinePathPaint.setColor(Color.BLACK);
        mLinePathPaint.setStyle(Paint.Style.STROKE);
        mLinePathPaint.setStrokeWidth(10);

        mLinePath = new Path();
        mLinePath.moveTo(100, 120);
        mLinePath.lineTo(100, 360);
        mLinePath.lineTo(700, 360);
        mLinePath.close();

    }

    /**
     * 初始化 弧 路径画笔
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initArcPath() {
        mArcPathPaint = new Paint();
        mArcPathPaint.setColor(Color.RED);
        mArcPathPaint.setStyle(Paint.Style.STROKE);
        mArcPathPaint.setStrokeWidth(10);


        mArcPath = new Path();
        mArcPath.moveTo(100, 100);
        mArcPath.arcTo(200, 200, 400, 400, 0, 90, false);


        mArcPathForce = new Path();
        mArcPathForce.moveTo(500, 100);
        mArcPathForce.arcTo(600, 200, 800, 400, 0, 90, true);

    }

    /**
     * 初始化 添加弧 路径 画笔
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initAddArcPath() {
        mAddArcPaint = new Paint();
        mAddArcPaint.setColor(Color.BLACK);
        mAddArcPaint.setStrokeWidth(10);
        mAddArcPaint.setStyle(Paint.Style.STROKE);

        mAddArcPath = new Path();
        mAddArcPath.moveTo(100, 100);
        mAddArcPath.lineTo(200, 200);
        mAddArcPath.addArc(300, 200, 500, 600, 0, 90);


    }

    /**
     * 初始化 添加 矩形路径 画笔
     */
    private void initAddRectPath() {

        mAddRectPaint = new Paint();
        mAddRectPaint.setColor(Color.RED);
        mAddRectPaint.setStrokeWidth(5);
        mAddRectPaint.setStyle(Paint.Style.STROKE);
        mAddRectPaint.setAntiAlias(true);

        mAddRectCWWPath = new Path();
        mAddRectCWWPath.addRect(50, 50, 240, 200, Path.Direction.CCW);

        mAddRectCWPath = new Path();
        mAddRectCWPath.addRect(290, 50, 480, 200, Path.Direction.CW);

        mStr = "路够黑， 光 才亮";
        mAddRectPaint.setTextSize(35);

    }

    /**
     * 初始化 添加 圆角矩形路径 画笔
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initAddRoundRectPath() {

        mAddRoundRectPaint = new Paint();
        mAddRoundRectPaint.setStyle(Paint.Style.STROKE);
        mAddRoundRectPaint.setStrokeWidth(10);
        mAddRoundRectPaint.setColor(Color.BLACK);


        mAddRoundRectPath = new Path();

        mAddRoundRectPath.addRoundRect(50, 50, 240, 200, 10, 15, Path.Direction.CCW);

        float[] radii = {10, 10, 20, 20, 30, 30, 40, 40};
        mAddRoundRectPath.addRoundRect(290, 50, 480, 200, radii, Path.Direction.CCW);


    }

    /**
     * 初始化 路径填充 画笔
     */
    private void initPathFillType() {
        mPathFillTypePaint = new Paint();
        mPathFillTypePaint.setColor(Color.BLACK);
        mPathFillTypePaint.setStyle(Paint.Style.FILL);
        mPathFillTypePaint.setStrokeWidth(10);


        RectF rectFLeft = new RectF(100, 100, 300, 300);
        RectF rectFTop = new RectF(500, 100, 700, 300);
        RectF rectFRight = new RectF(100, 500, 300, 700);
        RectF rectFBottom = new RectF(500, 500, 700, 700);


        mPathFillTypeLeft = new Path();
        mPathFillTypeLeft.addRect(rectFLeft, Path.Direction.CW);
        mPathFillTypeLeft.addCircle(300,300,100, Path.Direction.CW);
        mPathFillTypeLeft.setFillType(Path.FillType.WINDING);


        mPathFillTypeTop = new Path();
        mPathFillTypeTop.addRect(rectFTop, Path.Direction.CW);
        mPathFillTypeTop.addCircle(700,300,100, Path.Direction.CW);
        mPathFillTypeTop.setFillType(Path.FillType.EVEN_ODD);


        mPathFillTypeRight = new Path();
        mPathFillTypeRight.addRect(rectFRight, Path.Direction.CW);
        mPathFillTypeRight.addCircle(300,700,100, Path.Direction.CW);
        mPathFillTypeRight.setFillType(Path.FillType.INVERSE_WINDING);


        mPathFillTypeBottom = new Path();
        mPathFillTypeBottom.addRect(rectFBottom, Path.Direction.CW);
        mPathFillTypeBottom.addCircle(700,700,100, Path.Direction.CW);
        mPathFillTypeBottom.setFillType(Path.FillType.INVERSE_EVEN_ODD);


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

            case Constants.BASE_VIEW_ARC:

                // 画笔 为 描边时 两种情况，useCenter:是否带弧的两边
                canvas.drawArc(200, 100, 400, 300, 0, 180, false, mArcPaint);
                canvas.drawArc(500, 100, 700, 300, 0, 180, true, mArcPaint);


                // 画笔 为 填充时 两种情况，useCenter:是否带弧的两边
                mArcPaint.setStyle(Paint.Style.FILL);
                canvas.drawArc(200, 400, 400, 600, 0, 90, false, mArcPaint);
                canvas.drawArc(500, 400, 700, 600, 0, 90, true, mArcPaint);

                break;
            case Constants.BASE_VIEW_RECT_CONTAINS:

                if (mRectContains.contains(mX, mY)) {
                    mRectContainsPaint.setColor(Color.RED);
                } else {
                    mRectContainsPaint.setColor(Color.BLACK);
                }

                canvas.drawRect(mRectContains, mRectContainsPaint);
                break;
            case Constants.BASE_VIEW_LINE_PATH:

                canvas.drawPath(mLinePath, mLinePathPaint);
                break;
            case Constants.BASE_VIEW_ARC_PATH:
                canvas.drawPath(mArcPath, mArcPathPaint);
                canvas.drawPath(mArcPathForce, mArcPathPaint);
                break;

            case Constants.BASE_VIEW_ADD_ARC_PATH:

                canvas.drawPath(mAddArcPath, mAddArcPaint);
                break;

            case Constants.BASE_VIEW_ADD_RECT_PATH:
                canvas.drawPath(mAddRectCWWPath, mAddRectPaint);
                canvas.drawPath(mAddRectCWPath, mAddRectPaint);


                canvas.drawTextOnPath(mStr, mAddRectCWWPath, 0, 18, mAddRectPaint);
                canvas.drawTextOnPath(mStr, mAddRectCWPath, 0, 18, mAddRectPaint);
                break;
            case Constants.BASE_VIEW_ADD_ROUND_RECT_PATH:
                canvas.drawPath(mAddRoundRectPath, mAddRoundRectPaint);
                break;
            case Constants.BASE_VIEW_PATH_FILL_TYPE:
                canvas.drawPath(mPathFillTypeLeft,mPathFillTypePaint);
                canvas.drawPath(mPathFillTypeTop,mPathFillTypePaint);
                // 因为 当前View 没有设置固定宽高， 如果使用下面两种模式，除了绘画的部分 View其余部分都是黑色，上面两种就看不出来效果了
//                canvas.drawPath(mPathFillTypeRight,mPathFillTypePaint);
//                canvas.drawPath(mPathFillTypeBottom,mPathFillTypePaint);

                break;

        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mClickType != Constants.BASE_VIEW_RECT_CONTAINS) {

            return super.onTouchEvent(event);
        } else {


            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                mX = (int) event.getX();
                mY = (int) event.getY();


            } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                mX = mY = -1;
            }

            invalidate();
            return true;


        }

    }
}
