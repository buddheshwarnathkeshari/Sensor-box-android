package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Memory extends AppCompatActivity {


    double per,usedper;
    int usedPer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);




        final TextView tvPer=findViewById(R.id.tv_per);

        final Handler handler=new Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                final ActivityManager.MemoryInfo memoryInfo=new ActivityManager.MemoryInfo();
                ActivityManager activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
                activityManager.getMemoryInfo(memoryInfo);
                double availableMB=memoryInfo.availMem/0x100000L;

                per=memoryInfo.availMem/(double)memoryInfo.totalMem*100.0;
                usedper=100-per;
                tvPer.setText(""+String.format("%.2f",usedper)+"%");
                handler.postDelayed(this,100);
            }
        };
        handler.post(runnable);









    }


}
