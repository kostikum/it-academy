package com.kostikum.itac.dz5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import static com.kostikum.itac.dz5.Dz5Activity.EXTRA_WIFI_STATE;

public class WifiCheckingService extends Service {

    private final IBinder mBinder = new LocalBinder();
    WifiStateBroadcastReceiver mWifiStateBroadcastReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        mWifiStateBroadcastReceiver = new WifiStateBroadcastReceiver();
        registerReceiver(mWifiStateBroadcastReceiver, intentFilter);

        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        unregisterReceiver(mWifiStateBroadcastReceiver);

        return false;
    }

    public int getWifiState() {
        WifiManager wifiManager = (WifiManager) getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);

        return wifiManager.getWifiState();
    }

    protected class LocalBinder extends Binder {
        WifiCheckingService getService() {
            return WifiCheckingService.this;
        }
    }

    private class WifiStateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            Intent localBroadcastIntent = new Intent();
            localBroadcastIntent.setAction("com.kostikum.itac.broadcast.WIFI_STATE");
            localBroadcastIntent.putExtra(EXTRA_WIFI_STATE, wifiState);
            LocalBroadcastManager.getInstance(context).sendBroadcast(localBroadcastIntent);
        }
    }
}
