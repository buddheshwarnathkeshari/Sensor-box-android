package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Magnetic extends AppCompatActivity  implements SensorEventListener {


    private TextView magneticX;
    private TextView magneticY;
    private TextView magneticZ;
    private TextView magnitude;
    private SensorManager sensorManager = null;
    private ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Capture magnetic sensor related view elements
        magneticX = (TextView) findViewById(R.id.tv_x);
        magneticY = (TextView) findViewById(R.id.tv_y);
        magneticZ = (TextView) findViewById(R.id.tv_z);
        magnitude=(TextView)findViewById(R.id.tv_magnitude);

        pb=findViewById(R.id.progress_bar);

        pb.setProgress(0);

        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        // Register magnetic sensor
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register magnetic sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ignoring this for now

    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                float x=sensorEvent.values[0];
                float y=sensorEvent.values[1];
                float z=sensorEvent.values[2];

                float result=(float)Math.sqrt((x*x+y*y+z*z));
                magneticX.setText(String.format("X-Axis: %.2f",x));
                magneticY.setText(String.format("Y-Axis: %.2f",y));
                magneticZ.setText(String.format("Z-Axis: %.2f",z));
                magnitude.setText(String.format("Magnitude: %.2f\u00B5T",result));

                pb.setProgress((int)result);
                if((int)result<50)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_blue));
               else if((int)result<100)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_green));
                else if((int)result<150)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_orange));
                else
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_red));


            }
        }

    }


}

