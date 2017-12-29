package com.zhuandian.paysuccessview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * desc：
 * author：xiedong
 * date：2017/12/28.
 */
public class TestPath extends View {
    private Paint mPaint;
    public TestPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0,0);
        path.lineTo(200,200);
        canvas.drawPath(path,mPaint);

        //设置偏移量
        path.offset(500,0);
        canvas.drawPath(path,mPaint);

    }
}
