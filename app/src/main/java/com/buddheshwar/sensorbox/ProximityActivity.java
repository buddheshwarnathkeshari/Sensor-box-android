package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class ProximityActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;
    private TextView tvReading;
    private LottieAnimationView lottieAnimationView;
    boolean isAnimationReverse=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);


        tvReading=findViewById(R.id.tv_reading);
        lottieAnimationView=findViewById(R.id.animationView);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);



    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                //near
                Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                tvReading.setText(""+event.values[0]+" cm.");
                if(!isAnimationReverse){
                    isAnimationReverse=true;
                    lottieAnimationView.reverseAnimationSpeed();

                }
                lottieAnimationView.playAnimation();

            } else {
                //far
                Toast.makeText(getApplicationContext(), "far", Toast.LENGTH_SHORT).show();
                tvReading.setText(""+event.values[0]+" cm.");
               // lottieAnimationView.cancelAnimation();
                if(isAnimationReverse){
                    isAnimationReverse=false;
                    lottieAnimationView.reverseAnimationSpeed();

                }

                lottieAnimationView.playAnimation();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",4);
        startActivity(i);
    }
}
