package com.kostikum.itac.dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

    Paint mPaint;
    RectF mRectF;
    float cx;
    float cy;
    float mRadius;
    ArrayMap<Integer, Integer> mNumbersAndColors;

    public PieChartView(Context context) {
        super(context);
        init();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cx = getWidth() / 2f;
        cy = getHeight() / 2f;
        mRadius = Math.min(cx, cy) / 1.5f;

        mRectF = new RectF();
        mRectF.bottom = cy + mRadius;
        mRectF.top = cy - mRadius;
        mRectF.left = cx - mRadius;
        mRectF.right = cx + mRadius;
    }

    public void recutPieChart(int[] numbers) {
        mNumbersAndColors = generateColors(numbers);
        invalidate();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(30);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mNumbersAndColors = generateColors(new int[]{1,2,3,4,5,10,20,30,50,10,10,10});

    }

    private ArrayMap<Integer, Integer> generateColors(int[] numbers) {

        ArrayMap<Integer, Integer> numbersAndColors = new ArrayMap<>();
        float[] hsvColor = new float[3];

        for (int num : numbers) {
            //Hue - тон
            hsvColor[0] = (float) Math.random() * 360;
            //Saturation - насыщенность
            hsvColor[1] = 0.6f;
            //Luminance - яркость
            hsvColor[2] = 0.9f;
            numbersAndColors.put(num, Color.HSVToColor(hsvColor));
        }

        return numbersAndColors;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sum = 0;
        float currentAngle = 0;

        for (int i = 0; i < mNumbersAndColors.size(); i++) {
            sum = sum + mNumbersAndColors.keyAt(i);
        }

        for (int i = 0; i < mNumbersAndColors.size(); i++) {

            float sweepAngle = ((float) mNumbersAndColors.keyAt(i)) / sum * 360;
            mPaint.setColor(mNumbersAndColors.valueAt(i));
            canvas.drawArc(mRectF, currentAngle + 0.5f,
                    sweepAngle - 0.5f, true, mPaint);


            float markAngle = currentAngle + sweepAngle / 2f;
            mPaint.setStrokeWidth(5);
            mPaint.setColor(Color.DKGRAY);

            //Подсчёт отклонений
            float xDeviation = ((float) Math.cos(Math.toRadians(markAngle)) * mRadius);
            float yDeviation = ((float) Math.sin(Math.toRadians(markAngle)) * mRadius);

            //Метки для  кусочков
            canvas.drawLine(
                    cx + xDeviation, cy + yDeviation,
                    cx + xDeviation / 0.8f,cy + yDeviation / 0.8f, mPaint);

            //Круг на конце метки
            canvas.drawCircle(
                    cx + xDeviation / 0.8f,cy + yDeviation / 0.8f,
                    mRadius * 0.01f, mPaint);

            //Цифра у метки
            canvas.drawText(Integer.toString(i),
                    cx + xDeviation / 0.72f,
                    cy + yDeviation / 0.72f, mPaint);

            currentAngle = currentAngle + sweepAngle;
        }
    }
}























