package com.buddheshwar.sensorbox.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.buddheshwar.sensorbox.CompassActivity;
import com.buddheshwar.sensorbox.Flash;
import com.buddheshwar.sensorbox.FlashLightActivity;
import com.buddheshwar.sensorbox.LightActivity;
import com.buddheshwar.sensorbox.MainScreen;
import com.buddheshwar.sensorbox.Memory;
import com.buddheshwar.sensorbox.ProximityActivity;
import com.buddheshwar.sensorbox.R;
import com.buddheshwar.sensorbox.Sound;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    ImageView accelerometer,light,orientation,proximity,temperature,gyroscope,sound,magnetic,pressure,memory,
            flash;

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
        flash=root.findViewById(R.id.img_11);
        memory=root.findViewById(R.id.img_10);
        orientation=root.findViewById(R.id.img_3);
        proximity=root.findViewById(R.id.img_4);
        light=root.findViewById(R.id.img_2);
        sound=root.findViewById(R.id.img_7);


        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Sound.class));
            }
        });

        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LightActivity.class));
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
                startActivity(new Intent(getActivity(), Flash.class));
            }
        });

        orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CompassActivity.class));
            }
        });

        proximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ProximityActivity.class));
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