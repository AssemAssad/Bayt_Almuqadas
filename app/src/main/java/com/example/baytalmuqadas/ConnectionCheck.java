package com.example.baytalmuqadas;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import com.example.baytalmuqadas.utils.MyReceiver;

public class ConnectionCheck extends AppCompatActivity {
    MyReceiver receiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_check);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {

            IntentFilter nf = new IntentFilter();
            nf.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            registerReceiver(new MyReceiver(),nf);

        }
    }
    @Override
    protected void onDestroy() {
        //unregisterReceiver(receiver);
        try{
            unregisterReceiver(receiver);
        }catch (Exception e){
            // already unregistered
        }
        super.onDestroy();

    }

}