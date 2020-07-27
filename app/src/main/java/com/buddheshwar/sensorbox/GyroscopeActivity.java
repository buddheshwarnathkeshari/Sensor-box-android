package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GyroscopeActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    private TextView tvX,tvY,tvZ;
    float ax,ay,az;   // these are the acceleration in x,y and z axis

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);



        tvX=findViewById(R.id.tv_x);
        tvY=findViewById(R.id.tv_y);
        tvZ=findViewById(R.id.tv_z);


        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener( this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_GYROSCOPE){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];

            tvX.setText(String.format("X: %.2f",ax));
            tvY.setText(String.format("Y: %.2f",ay));
            tvZ.setText(String.format("Z: %.2f",az));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }


    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",6);
        startActivity(i);
    }
}