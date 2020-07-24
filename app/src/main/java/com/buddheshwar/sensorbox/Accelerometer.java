package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {


        private SensorManager sensorManager;
        private TextView tvX,tvY,tvZ;
        float ax,ay,az;   // these are the acceleration in x,y and z axis
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_accelerometer);
            tvX=findViewById(R.id.tv_x);
            tvY=findViewById(R.id.tv_y);
            tvZ=findViewById(R.id.tv_z);
            sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        }
        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
                ax=event.values[0];
                ay=event.values[1];
                az=event.values[2];

                tvX.setText(String.format("X-Axis: %.2f",ax));
                tvY.setText(String.format("Y-Axis: %.2f",ay));
                tvZ.setText(String.format("Z-Axis: %.2f",az));
            }
        }


    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",1);
        startActivity(i);
    }
}


