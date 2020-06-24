package com.buddheshwar.sensorbox.ui.main;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.buddheshwar.sensorbox.Accelerometer;
import com.buddheshwar.sensorbox.CompassActivity;
import com.buddheshwar.sensorbox.Flash;
import com.buddheshwar.sensorbox.FlashLightActivity;
import com.buddheshwar.sensorbox.GyroscopeActivity;
import com.buddheshwar.sensorbox.LightActivity;
import com.buddheshwar.sensorbox.Magnetic;
import com.buddheshwar.sensorbox.MainScreen;
import com.buddheshwar.sensorbox.Memory;
import com.buddheshwar.sensorbox.ProximityActivity;
import com.buddheshwar.sensorbox.R;
import com.buddheshwar.sensorbox.Sound;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    ImageView accelerometer,light,orientation,proximity,temperature,gyroscope,sound,magnetic,pressure,memory,
            flash;


    SensorManager sensorManager;
    Sensor accelerometerSensor,lightSensor,orientationSensor,proximitySensor,temperatureSensor,gyroscopeSensor,soundSensor,magneticSensor,pressureSensor;


    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_screen, container, false);
        final LinearLayout llSensor=root.findViewById(R.id.ll_SensorBox);
        final LinearLayout llTool=root.findViewById(R.id.ll_ToolBox);
        accelerometer=root.findViewById(R.id.img_1);
        light=root.findViewById(R.id.img_2);
        orientation=root.findViewById(R.id.img_3);
        proximity=root.findViewById(R.id.img_4);
        temperature=root.findViewById(R.id.img_5);
        gyroscope=root.findViewById(R.id.img_6);
        sound=root.findViewById(R.id.img_7);
        magnetic=root.findViewById(R.id.img_8);
        pressure=root.findViewById(R.id.img_9);
        memory=root.findViewById(R.id.img_10);
        flash=root.findViewById(R.id.img_11);



        sensorManager = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);

        accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        orientationSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        temperatureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        gyroscopeSensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magneticSensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pressureSensor=sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

    //click handling on accelerometer
        accelerometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accelerometerSensor==null)
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(getActivity(), Accelerometer.class));
            }
        });



        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lightSensor==null)
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                else
                     startActivity(new Intent(getActivity(), LightActivity.class));
            }
        });


        orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orientationSensor==null)
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                else
                     startActivity(new Intent(getActivity(), CompassActivity.class));
            }
        });


        proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proximitySensor==null)
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                else
                startActivity(new Intent(getActivity(), ProximityActivity.class));
            }
        });


        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                if (temperatureSensor == null) {
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                }
            }

        });

        gyroscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(gyroscopeSensor==null)
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(getActivity(), GyroscopeActivity.class));
            }
        });


       sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Sound.class));
            }
        });



        magnetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Magnetic.class));
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
                if (pressureSensor == null) {
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support the sensor.",Toast.LENGTH_SHORT).show();
                }
            }
        });







        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Memory.class));
            }
        });

        flash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
                startActivity(new Intent(getActivity(), Flash.class));
                else
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, your device hardware doesn't support Flash Light.",Toast.LENGTH_SHORT).show();


            }
        });

        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                if(s.equals("1")){
                    llSensor.setVisibility(View.VISIBLE);
                    llTool.setVisibility(View.GONE);

                }
                else
                {

                    llSensor.setVisibility(View.GONE);
                    llTool.setVisibility(View.VISIBLE);
                }

            }
        });
        return root;
    }
}