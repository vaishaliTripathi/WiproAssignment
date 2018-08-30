package com.wipro.assignment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.squareup.otto.Bus;
import com.wipro.assignment.R;
import com.wipro.assignment.util.AppUtils;
import com.wipro.assignment.util.ConnectionState;
import com.wipro.assignment.util.OttoClient;


/**
 * Created by varora on 28-08-2018.
 */
public class BaseActivity extends AppCompatActivity {

    private static final int REQUEST_ACCESS_INTERNET = 1001;
    private Bus mBus;
    /**
     * Runtime Broadcast receiver inner class to capture internet connectivity events
     */
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int permissionCheck = ContextCompat.checkSelfPermission(BaseActivity.this, Manifest.permission.INTERNET);

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(BaseActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_ACCESS_INTERNET);
            } else {
                sendConnectionStatus();
            }

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        mBus = OttoClient.getInstance().getOttoBus();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerInternetCheckReceiver();
    }

    private void sendConnectionStatus() {
        boolean status = AppUtils.isConnectingToInternet(getApplicationContext());
        int connectionState = status ? ConnectionState.CONNECTED : ConnectionState.NOT_CONNECTED;
        ConnectionState connectionStateObj = new ConnectionState();
        connectionStateObj.setConnectionState(connectionState);
        mBus.post(connectionStateObj);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * Method to register runtime broadcast receiver to show snackbar alert for internet connection..
     */
    private void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(broadcastReceiver, internetFilter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBarColor(int color) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ACCESS_INTERNET:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    sendConnectionStatus();
                }
                break;

            default:
                break;
        }
    }

}
