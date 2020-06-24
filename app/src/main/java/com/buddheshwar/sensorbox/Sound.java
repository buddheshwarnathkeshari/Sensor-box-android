package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Sound extends AppCompatActivity {

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        TextView tv1=findViewById(R.id.tv1);
        TextView tv2=findViewById(R.id.tv2);
        TextView tv3=findViewById(R.id.tv3);
        TextView tv4=findViewById(R.id.tv4);

        MediaRecorder mRecorder = null;

   /*     SoundMeter soundmeter=new SoundMeter();
        try {
            soundmeter.start(Sound.this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
      double db= soundmeter.stop();
        TextView tv=findViewById(R.id.tv_reading);
       // tv.setText(""+db);
      //  soundmeter.stop();

*/



        if (ContextCompat.checkSelfPermission(Sound.this
                , Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) Sound.this, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
        }



            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  //exception occurs why????
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try {
                mRecorder.prepare();

                tv2.setText("Prepare:\t"+mRecorder.getMaxAmplitude());
            } catch (IOException e) {
                e.printStackTrace();
            }
            mRecorder.start();
            tv1.setText("Start:\t"+mRecorder.getMaxAmplitude());

         //   mRecorder.stop();

        tv3.setText("Stop:\t"+mRecorder.getMaxAmplitude());
      //  mRecorder.release();

        tv4.setText("Release:\t"+mRecorder.getMaxAmplitude());



            mRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {

                }
            });






    }
}
