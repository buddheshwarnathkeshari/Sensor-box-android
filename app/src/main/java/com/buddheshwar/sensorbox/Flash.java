package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Policy;

public class Flash extends AppCompatActivity {
    boolean start;
    CameraManager cameraManager;
    String cameraID;
    TextView tvOff;
    ImageView imgPower;
    View v1,v2,v3,v4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        final RelativeLayout rl=findViewById(R.id.id);
        tvOff=findViewById(R.id.tv_off);
        imgPower=findViewById(R.id.img_power);
        cameraManager=(CameraManager)getSystemService(Context.CAMERA_SERVICE);
        v1=findViewById(R.id.dash_1);
        v2=findViewById(R.id.dash_2);
        v3=findViewById(R.id.dash_3);
        v4=findViewById(R.id.dash_4);


         cameraID=null;
        start=false;

        try {
            cameraID=cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
                    Toast.makeText(getApplicationContext(),"Flash light not available in your device",Toast.LENGTH_SHORT).show();
                else{
                    if(start){

                        try {
                            cameraManager.setTorchMode(cameraID,false);
                            tvOff.setVisibility(View.VISIBLE);
                            rl.bringToFront();
                            v1.setBackgroundResource(R.color.dark);
                            v2.setBackgroundResource(R.color.dark);
                            v3.setBackgroundResource(R.color.dark);
                            v4.setBackgroundResource(R.color.dark);

                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        start=false;

                    }
                    else{

                        try {
                            cameraManager.setTorchMode(cameraID,true);
                            tvOff.setVisibility(View.GONE);
                            imgPower.bringToFront();

                            v1.setBackgroundResource(R.color.light);
                            v2.setBackgroundResource(R.color.light);
                            v3.setBackgroundResource(R.color.light);
                            v4.setBackgroundResource(R.color.light);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        start=true;

                    }


                }

            }
        });
    }
}
