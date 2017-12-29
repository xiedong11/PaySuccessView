package com.zhuandian.paysuccessview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

public class PathMeasureView extends View {

    private Path mPath = null;
    private Path mDest = new Path();
    private Paint mPaint = null;
    private PathMeasure mPathMeasure = new PathMeasure();
    private float mLength = 0f;
    private float mFloatPos = 0f;
    private float mStop = 0f;
    private float mStart = 0f;

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#f90876"));
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

        // 创建从0 到1的动画
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFloatPos = (float) animation.getAnimatedValue();
                // 不断刷新，重新调用onDraw方法
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1500);
        valueAnimator.start();
    }

    public PathMeasureView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取到当前view的宽和高
        int width = getWidth();
        int height = getHeight();
        // 绘制的图形为圆形
        mPath.addCircle(width / 2, height / 2, 60, Path.Direction.CW);  //Path.Direction.CCW  逆时针，  CW顺时针
        // 将mPath和mPathMeasure关联起来
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();

        // 每次重新绘制之前将mDest重置
        mDest.reset();
        mDest.lineTo(0, 0);

        mStart = (float) (mFloatPos * mLength - ((0.5 - Math.abs(mFloatPos - 0.5)) * mLength));
        mStop = mFloatPos * mLength;

        // 截取mPath中从mStart起点到mStop终点的片段，到mDest里
        mPathMeasure.getSegment(mStart, mStop, mDest, true);
        // 最终绘制的是截取之后的mDest
        canvas.drawPath(mDest, mPaint);
    }
}