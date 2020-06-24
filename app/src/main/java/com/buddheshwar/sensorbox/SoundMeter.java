package com.buddheshwar.sensorbox;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class SoundMeter {

    public static MediaRecorder mRecorder = null;

    public static void start(Activity a) throws IOException {
        if (ContextCompat.checkSelfPermission(a
                , Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) a, new String[]{Manifest.permission.RECORD_AUDIO},
                    0);
        } else {


            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  //exception occurs why????
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            mRecorder.prepare();
            mRecorder.start();


        }
    }

        public static double stop () {
            if (mRecorder != null) {
                mRecorder.stop();
                //mRecorder.release();
                //double db=mRecorder.getMaxAmplitude();
                //mRecorder = null;
                //return db;
            }
            return 150;
        }

        public static double getAmplitude () {
            if (mRecorder != null)
                return mRecorder.getMaxAmplitude();
            else
                return 10;

        }

}