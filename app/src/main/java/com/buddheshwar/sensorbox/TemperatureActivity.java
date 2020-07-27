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

public class TemperatureActivity extends AppCompatActivity {
    TextView textTemp_reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        textTemp_reading
                = (TextView)findViewById(R.id.tv_reading);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor temperatureSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(temperatureSensor != null){
            mySensorManager.registerListener(
                    temperatureSensorListener,
                    temperatureSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }
    }

    private final SensorEventListener temperatureSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
                textTemp_reading.setText("Temperature: " + event.values[0]+"Unit");
            }
        }

    };




    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",5);
        startActivity(i);
    }

}