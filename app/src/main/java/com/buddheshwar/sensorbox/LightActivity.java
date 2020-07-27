package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LightActivity extends AppCompatActivity /*implements SensorEventListener*/ {
    TextView textLIGHT_available, textLIGHT_reading;
    ImageView imgBulb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);

        textLIGHT_available
                = (TextView)findViewById(R.id.tv_available);
        textLIGHT_reading
                = (TextView)findViewById(R.id.tv_reading);
        imgBulb=findViewById(R.id.img_bulb);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null){
            mySensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        } else {
            textLIGHT_available.setText("Sensor.TYPE_LIGHT NOT Available");
        }
    }

    private final SensorEventListener lightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){

                float result=event.values[0];

                textLIGHT_reading.setText("LIGHT: \n" + event.values[0]+" Luxes");


                if(result>1200)
                    imgBulb.setImageResource(R.drawable.ic_bulb_red);
                else if(result>80)
                    imgBulb.setImageResource(R.drawable.ic_bulb100);
                else if(result>50)
                    imgBulb.setImageResource(R.drawable.ic_bulb80);
                else if(result>30)
                    imgBulb.setImageResource(R.drawable.ic_bulb60);
                else if(result>20)
                    imgBulb.setImageResource(R.drawable.ic_bulb40);
                else if(result>10)
                    imgBulb.setImageResource(R.drawable.ic_bulb30);
                else if(result>2)
                    imgBulb.setImageResource(R.drawable.ic_bulb20);
                else
                    imgBulb.setImageResource(R.drawable.ic_bulb10);





            }
        }

    };


    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",2);
        startActivity(i);
    }

}

