package com.example.baytalmuqadas.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.baytalmuqadas.MainActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (ni!=null && ni.isConnected()) {
            Intent ii = new Intent(context, MainActivity.class);
            context.startActivity(ii);

        }else{
           // Toast.makeText(context, " Wifi is Disconnected ", Toast.LENGTH_LONG).show();

        }
    }
}