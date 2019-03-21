package com.kostikum.itac.dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {

    private Paint mPaint;
    private float mRadius;
    private int mHours;
    private int mMinutes;
    private int mSeconds;
    private float cx;
    private float cy;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cx = w / 2f;
        cy = h / 2f;
        mRadius = Math.min(cx, cy) / 1.3f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void updateClock() {
        Calendar calendar = Calendar.getInstance();
        mHours = calendar.get(Calendar.HOUR);
        mMinutes = calendar.get(Calendar.MINUTE);
        mSeconds = calendar.get(Calendar.SECOND);

        invalidate();
    }

    public void updateSeconds(int seconds) {
        mSeconds = seconds;

        invalidate();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(100);
        mPaint.setTextAlign(Paint.Align.CENTER);

        updateClock();
    }

    public float getRadius() {
        return mRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Циферблат
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(cx, cy, mRadius, mPaint);

        //Риски на часах
        for (int i = 0; i < 12; i++){
            canvas.drawLine(cx, cy - mRadius, cx, cy - mRadius * 0.9f, mPaint);
            canvas.rotate(30, cx, cy);
        }

        //Циферки на циферблате
        canvas.drawText("12", cx, cy - mRadius * 1.15f + 35, mPaint);
        canvas.drawText("3", cx + mRadius * 1.15f, cy + 35, mPaint);
        canvas.drawText("6", cx, cy + mRadius * 1.15f + 35, mPaint);
        canvas.drawText("9", cx - mRadius * 1.15f, cy + 35, mPaint);

        //Часовая стрелка
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(20);
        canvas.rotate(360 / 12f / 60f * (mHours * 60 + mMinutes), cx, cy);
        canvas.drawLine(cx, cy, cx, cy - mRadius * 0.6f, mPaint);
        canvas.rotate(-360 / 12f / 60f * (mHours * 60 + mMinutes), cx, cy);

        //Минутная стрелка
        mPaint.setColor(Color.CYAN);
        mPaint.setStrokeWidth(6);
        canvas.rotate(360 / 60f * mMinutes, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - mRadius * 0.8f, mPaint);
        canvas.rotate(-360 / 60f * mMinutes, cx, cy);

        //Секундная стрелка
        mPaint.setColor(Color.RED);
        canvas.rotate(360 / 60f * mSeconds, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - mRadius * 0.95f, mPaint);
        canvas.rotate(-360 / 60f * mSeconds, cx, cy);

        canvas.drawCircle(cx, cy, mRadius * 0.01f, mPaint);
    }

}
