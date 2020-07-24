package com.buddheshwar.sensorbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        int value=getIntent().getExtras().getInt("VALUE");
        String[] arrAbouts=getResources().getStringArray(R.array.Abouts);
        String[] arrNames=getResources().getStringArray(R.array.sensors);
        TextView tv_title =findViewById(R.id.tv_title);
        TextView tv_desc=findViewById(R.id.tv_desc);
        tv_title.setText("About "+arrNames[value-1]);
        tv_desc.setText(arrAbouts[value-1]);
    }
}