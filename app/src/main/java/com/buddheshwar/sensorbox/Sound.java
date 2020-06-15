package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Sound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        SoundMeter soundmeter=new SoundMeter();
        try {
            SoundMeter.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_LONG);
        }
      /*  soundmeter.getAmplitude();
        TextView tv=findViewById(R.id.tv_reading);
        soundmeter.stop();*/
    }
}
