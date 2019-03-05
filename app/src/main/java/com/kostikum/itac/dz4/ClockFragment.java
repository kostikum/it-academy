package com.kostikum.itac.dz4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.kostikum.itac.R;

import java.util.Calendar;

public class ClockFragment extends Fragment {

    ClockView mClockView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clock_view, container, false);

        final ImageView animOwl = v.findViewById(R.id.owl_anim_image_view);
        mClockView = v.findViewById(R.id.clock_view);
        mClockView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                float density = getActivity().getResources().getDisplayMetrics().density;

                animOwl.getLayoutParams().width = (int) (mClockView.getRadius() / density * 2);
                animOwl.setMinimumWidth((int) (mClockView.getRadius() / density * 2));
                Log.i("width", "pixels " + (int) (mClockView.getRadius() * 2));
            }
        });

        ((AnimationDrawable) animOwl.getBackground()).start();

        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);

        setTimer(60 - seconds);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        getActivity().registerReceiver(onClockMinutesTick, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(onClockMinutesTick);
    }

    private BroadcastReceiver onClockMinutesTick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mClockView.invalidate();
            setTimer(60);
        }
    };

    private void setTimer(int secondsLeft) {
        CountDownTimer countDownTimer = new CountDownTimer(secondsLeft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mClockView.invalidate();
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        };
        countDownTimer.start();
    }
}
