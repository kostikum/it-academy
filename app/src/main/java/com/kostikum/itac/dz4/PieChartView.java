package com.kostikum.itac.dz4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

    Paint paint;
    RectF rectF;
    float cx;
    float cy;
    float radius;
    int[] colors;
    int[] numbers;
    float[] hsv;

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
        radius = Math.min(cx, cy) / 1.5f;

        rectF = new RectF();
        rectF.bottom = cy + radius;
        rectF.top = cy - radius;
        rectF.left = cx - radius;
        rectF.right = cx + radius;
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);

        numbers =  new int[]{100, 4, 12, 7, 12, 9, 2, 7, 8, 10, 10, 10, 10, 100,
                100, 4, 12, 7, 12, 9, 2, 7, 8, 10, 10, 10, 10, 100,100, 4, 12, 7, 12, 9, 2, 7, 8, 10, 10, 10, 10, 100};
        hsv = new float[3];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int sum = 0;
        float currentAngle = 0;


        for (int i : numbers) {
            sum = sum + i;
        }

        for (int i : numbers) {

            float sweepAngle = ((float) i) / sum * 360;

            //Hue
            hsv[0] = (float) Math.random() * 360;
            //Saturation
            hsv[1] = 0.6f;//1.0 for brilliant, 0.0 for dull
            //Luminance
            hsv[2] = 1.0f; //1.0 for brighter, 0.0 for black

            paint.setColor(Color.HSVToColor(hsv));

            canvas.drawArc(rectF, currentAngle + 0.5f, sweepAngle - 0.5f, true, paint);

            float markAngle = currentAngle + sweepAngle / 2f;

            paint.setStrokeWidth(5);
            paint.setColor(Color.DKGRAY);
            canvas.drawLine(
                    cx + ((float) Math.cos(Math.toRadians(markAngle)) * radius),
                    cy + ((float) Math.sin(Math.toRadians(markAngle)) * radius),
                    cx + ((float) Math.cos(Math.toRadians(markAngle)) * radius) / 0.8f,
                    cy + ((float) Math.sin(Math.toRadians(markAngle)) * radius) / 0.8f,
                    paint);

            canvas.drawCircle(cx + ((float) Math.cos(Math.toRadians(markAngle)) * radius) / 0.8f,
                    cy + ((float) Math.sin(Math.toRadians(markAngle)) * radius) / 0.8f,
                    radius * 0.01f, paint);

            canvas.drawText(Integer.toString(i),
                    cx + ((float) Math.cos(Math.toRadians(markAngle)) * radius) / 0.72f,
                    cy + ((float) Math.sin(Math.toRadians(markAngle)) * radius) / 0.72f,
                    paint);

            currentAngle = currentAngle + sweepAngle;
        }
    }
}























