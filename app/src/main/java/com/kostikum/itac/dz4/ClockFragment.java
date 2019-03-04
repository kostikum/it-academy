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
import android.widget.Toast;

import com.kostikum.itac.R;

import java.util.Calendar;

public class ClockFragment extends Fragment {

    private ClockFragment sClockFragment;

    public ClockFragment get(Context context) {
        if (sClockFragment == null) {
            sClockFragment = new ClockFragment();
        }
        return sClockFragment;
    }


    View mClockView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clock_view, container, false);

        ((AnimationDrawable) v.findViewById(R.id.owl_anim_image_view).getBackground()).start();

        mClockView = v.findViewById(R.id.clock_view);

        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);

        setTimer(59 - seconds, "BEGINNER");

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
            Toast.makeText(getActivity(), "Minute", Toast.LENGTH_LONG).show();
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
