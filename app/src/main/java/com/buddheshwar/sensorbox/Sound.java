package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Sound extends AppCompatActivity {
TextView tv1;

ProgressBar pb;
    MediaRecorder mRecorder;
    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);


        tv1=findViewById(R.id.tv1);
        pb=findViewById(R.id.progress_bar);


        mRecorder = null;



        if (ContextCompat.checkSelfPermission(Sound.this
                , Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) Sound.this, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
        }


        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mRecorder.setOutputFile("/dev/null");
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder.start();


        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {





                int referenceAmp=94;

                double result= 19* Math.log10(getAmplitude() /1);


                tv1.setText(String.format("%.1f Db",result));


                pb.setProgress((int)result);
                if((int)result<40)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_blue));
                else if((int)result<80)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_green));
                else if((int)result<100)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_orange));
                else
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_red));



                handler.postDelayed(this,300);
            }
        };
        handler.post(runnable);




    }

    private void update() {




    }

    public double getAmplitude(){
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude());
        else
            return 0;

    }




    public void goToInfo(View view) {
        Intent i=new Intent(this,AboutActivity.class);
        i.putExtra("VALUE",7);
        startActivity(i);
    }
}
