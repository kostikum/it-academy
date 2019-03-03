package com.kostikum.itac.dz4;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kostikum.itac.R;

import java.util.Calendar;

public class Dz4Activity extends Activity {

    View mClockView;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz4Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz4);

        ((AnimationDrawable) findViewById(R.id.owl_anim_image_view).getBackground()).start();

        mClockView = findViewById(R.id.clock_view);

        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);

        setTimer(59 - seconds, "BEGINNER");
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        Dz4Activity.this.registerReceiver(onClockMinutesTick, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Dz4Activity.this.unregisterReceiver(onClockMinutesTick);
    }

    private BroadcastReceiver onClockMinutesTick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(Dz4Activity.this, "Minute", Toast.LENGTH_LONG).show();
            mClockView.invalidate();

            setTimer(60, "SECONDARY");
        }
    };

    private void setTimer(int secondsLeft, String tag) {


        final  String taggy = tag;


        CountDownTimer countDownTimer = new CountDownTimer(secondsLeft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mClockView.invalidate();
                Log.i("CLOCK", taggy + Long.toString(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        };

        countDownTimer.start();

    }
}
