package com.zhuandian.paysuccessview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc：支付成功View
 * author：xiedong
 * date：2017/12/28.
 */
public class PaySuccessView extends View implements ValueAnimator.AnimatorUpdateListener {
    private float mLineWidth = 10;
    private Paint mPaint;
    private Path dst1;
    private Path dst2;
    private float v1;
    private float v2;
    private Path mPath1;
    private Path mPath2;
    private PathMeasure mPathMeasure;
    private ValueAnimator valueAnimator1;
    private ValueAnimator valueAnimator2;

    public PaySuccessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initPath();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
    }

    private void initPath() {
        mPath1 = new Path();
        mPath2 = new Path();
        dst1 = new Path();
        dst2 = new Path();
        mPathMeasure = new PathMeasure();
        valueAnimator1 = ValueAnimator.ofFloat(0, 1);
        valueAnimator1.setDuration(1000);
        valueAnimator1.start();
        valueAnimator2 = ValueAnimator.ofFloat(0, 1);
        valueAnimator2.setDuration(1000);
        valueAnimator1.addUpdateListener(this);
        valueAnimator2.addUpdateListener(this);
    }

    public void animatorStart() {
        initPaint();
        initPath();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath1.addCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - mLineWidth / 2, Path.Direction.CW);
        mPath2.moveTo(getWidth() * 0.2f, getHeight() * 0.5f);
        mPath2.lineTo(getWidth() * 0.45f, getHeight() * 0.7f);
        mPath2.lineTo(getWidth() * 0.78f, getHeight() * 0.38f);
        mPathMeasure.setPath(mPath1, false);
        mPathMeasure.getSegment(0, v1 * mPathMeasure.getLength(), dst1, true);
        canvas.drawPath(dst1, mPaint);

        if (v1 == 1) {
            mPathMeasure.nextContour();
            mPathMeasure.setPath(mPath2, false);
            mPathMeasure.getSegment(0, v2 * mPathMeasure.getLength(), dst2, true);
            canvas.drawPath(dst2, mPaint);
        }

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (animation.equals(valueAnimator1)) {
            v1 = (float) animation.getAnimatedValue();
            invalidate();
            if (v1 == 1) {
                valueAnimator2.start();
            }
        } else if (animation.equals(valueAnimator2)) {
            v2 = (float) animation.getAnimatedValue();
            invalidate();
        }
    }
}
