package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Memory extends AppCompatActivity {

    private ProgressBar pb;


    double per,usedper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);


        pb=findViewById(R.id.progress_bar);

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

                pb.setProgress((int)usedper);
                if(usedper<=25)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_blue));
                else if(usedper<=50)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_green));
                else if(usedper<=75)
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_orange));
                else
                    pb.setProgressDrawable(getDrawable(R.drawable.custom_progressbar_red));

                tvPer.setText(""+String.format("%.2f",usedper)+"%");

                handler.postDelayed(this,100);
            }
        };
        handler.post(runnable);









    }


}
