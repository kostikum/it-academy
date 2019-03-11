package com.kostikum.itac.dz5;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.kostikum.itac.R;

public class Dz5Activity extends Activity {

    WifiCheckingService mService;
    LocalWifiStateReceiver mLocalWifiStateReceiver;
    TextView mWifiStatusTextView;

    public static Intent getIntent(Context context) {
        return new Intent(context, Dz5Activity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);

        mWifiStatusTextView = findViewById(R.id.wifi_status_text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, WifiCheckingService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        IntentFilter intentFilter = new IntentFilter("com.kostikum.itac.broadcast.WIFI_STATE");

        mLocalWifiStateReceiver = new LocalWifiStateReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mLocalWifiStateReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        stopService()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mLocalWifiStateReceiver);
    }

    private void updateWifiStatusTextView(int wifiState) {

        switch (wifiState) {
            case WifiManager.WIFI_STATE_ENABLED:
                mWifiStatusTextView.setText(R.string.wifi_on_text_view_text);
                mWifiStatusTextView.setBackground(new ColorDrawable(Color.GREEN));
                break;
            case WifiManager.WIFI_STATE_DISABLED:
                mWifiStatusTextView.setText(R.string.wifi_off_text_view_text);
                mWifiStatusTextView.setBackground(new ColorDrawable(Color.RED));
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WifiCheckingService.LocalBinder binder = (WifiCheckingService.LocalBinder) service;
            mService = binder.getService();
            updateWifiStatusTextView(mService.getWifiState());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private class LocalWifiStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra("WifiState", WifiManager.WIFI_STATE_UNKNOWN);
            updateWifiStatusTextView(wifiState);
        }
    }

}
