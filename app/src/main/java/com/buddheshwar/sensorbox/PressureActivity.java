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

public class PressureActivity extends AppCompatActivity {


    TextView textPressure_reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);



        textPressure_reading
                = (TextView)findViewById(R.id.tv_reading);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor pressureSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(pressureSensor != null){
            mySensorManager.registerListener(
                    pressureSensorListener,
                    pressureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }




    }


    private final SensorEventListener pressureSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_PRESSURE){
                textPressure_reading.setText("LIGHT: " + event.values[0]);
            }
        }

    };



    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",9);
        startActivity(i);
    }
}