package com.buddheshwar.sensorbox.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.buddheshwar.sensorbox.R;

public class AccelerometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_activity);

      //  getSupportFragmentManager().beginTransaction().replace(R.id.fl,new MovingBall()).commit();
    }
}