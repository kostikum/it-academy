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

    private ClockView mClockView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clock_view, container, false);

        ImageView animOwl = v.findViewById(R.id.owl_anim_image_view);
        mClockView = v.findViewById(R.id.clock_view);

        ((AnimationDrawable) animOwl.getBackground()).start();

        int seconds = Calendar.getInstance().get(Calendar.SECOND);
        setTimer(60 - seconds);

        return v;
    }

    private BroadcastReceiver onClockMinutesTick = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mClockView.updateClock();
            setTimer(60);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        getActivity().registerReceiver(onClockMinutesTick, filter);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(onClockMinutesTick);
    }

    private void setTimer(int secondsLeft) {
        CountDownTimer countDownTimer = new CountDownTimer(secondsLeft * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mClockView == null) {
                    this.cancel();
                } else {
                    mClockView.updateSeconds(60 - ((int) (millisUntilFinished / 1000)));
                }
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        };
        countDownTimer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mClockView = null;
    }
}
