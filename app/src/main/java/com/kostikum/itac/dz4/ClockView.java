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
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;

        float radius = Math.min(cx, cy) / 1.3f;

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setElegantTextHeight(true);
        paint.setColor(Color.BLACK);

        canvas.drawCircle(cx, cy, radius, paint);

        for (int i = 0; i < 12; i++){
            canvas.drawLine(cx, cy - radius, cx, cy - radius * 0.9f, paint);
            canvas.rotate(30, cx, cy);
        }


        canvas.drawText("12", cx, cy - radius * 1.15f + 35, paint);
        canvas.drawText("3", cx + radius * 1.15f, cy + 35, paint);
        canvas.drawText("6", cx, cy + radius * 1.15f + 35, paint);
        canvas.drawText("9", cx - radius * 1.15f, cy + 35, paint);


        Calendar calendar = Calendar.getInstance();

        int hours = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        Log.i("CLOCK", Integer.toString(hours) + "  " +
                Integer.toString(minutes) + "  " + Integer.toString(seconds));

        //Рассчитываем градус вращения для секундной стрелки и рисуем
        paint.setColor(Color.RED);
        canvas.rotate(360 / 60f * seconds, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.95f, paint);
        canvas.rotate(-360 / 60f * seconds, cx, cy);

        //Рассчитываем градус вращения для минутной стрелки и рисуем
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(6);
        canvas.rotate(360 / 60f * minutes, cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.8f, paint);
        canvas.rotate(-360 / 60f * minutes, cx, cy);

        //Рассчитываем градус вращения для часовой стрелки и рисуем
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        canvas.rotate(360 / 12f / 60f * (hours * 60 + minutes), cx, cy);
        canvas.drawLine(cx, cy, cx, cy - radius * 0.6f, paint);
        canvas.rotate(-360 / 12f / 60f * (hours * 60 + minutes), cx, cy);

        canvas.drawCircle(cx, cy, radius * 0.01f, paint);
    }
}

































