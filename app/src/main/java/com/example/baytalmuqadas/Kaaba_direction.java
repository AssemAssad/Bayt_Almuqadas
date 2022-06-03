package com.example.baytalmuqadas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

public class Kaaba_direction extends AppCompatActivity implements SensorEventListener {

    ImageView imageView;
    private static SensorManager sensorManager;
    private static Sensor sensor;
    private float currentDegree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaaba_direction);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView  = findViewById(R.id.img);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor =sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        if (sensor != null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_FASTEST);
        }else {
            Toast.makeText(this, "not support", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int degree = Math.round(sensorEvent.values[0]);
        RotateAnimation animation = new RotateAnimation(currentDegree,-degree, Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        imageView.setAnimation(animation);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}