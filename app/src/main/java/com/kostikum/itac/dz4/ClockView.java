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
import java.util.Date;

public class ClockView extends View {

    private Paint paint;
    private float radius;

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

        float cx = w / 2f;
        float cy = h / 2f;
        Log.i("size", "jj" + radius);
        radius = Math.min(cx, cy) / 1.3f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);

        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        Log.i("size", "jj" + radius);
        radius = Math.min(cx, cy) / 1.3f;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        Log.i("draw", "jj" + radius);
        radius = Math.min(cx, cy) / 1.3f;

        //Drawing clock face
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(cx, cy, radius, paint);

        //Drawing clock marks
        for (int i = 0; i < 12; i++){
            canvas.drawLine(cx, cy - radius, cx, cy - radius * 0.9f, paint);
            canvas.rotate(30, cx, cy);
        }

        //Drawing number markings
        canvas.drawText("12", cx, cy - radius * 1.15f + 35, paint);
        canvas.drawText("3", cx + radius * 1.15f, cy + 35, paint);
        canvas.drawText("6", cx, cy + radius * 1.15f + 35, paint);
        canvas.drawText("9", cx - radius * 1.15f, cy + 35, paint);


        Calendar calendar = Calendar.getInstance();
        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        //A very thin "second" or "sweep" hand
        paint.setColor(Color.RED);
        canvas.rotate(360 / 60f * seconds, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.95f, paint);
        canvas.rotate(-360 / 60f * seconds, cx, cy);

        //A long, thinner "minute" hand;
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(6);
        canvas.rotate(360 / 60f * minutes, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.8f, paint);
        canvas.rotate(-360 / 60f * minutes, cx, cy);

        //A short, thick "hour" hand
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        canvas.rotate(360 / 12f / 60f * (hours * 60 + minutes), cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.6f, paint);
        canvas.rotate(-360 / 12f / 60f * (hours * 60 + minutes), cx, cy);

        canvas.drawCircle(cx, cy, radius * 0.01f, paint);
    }

}

































