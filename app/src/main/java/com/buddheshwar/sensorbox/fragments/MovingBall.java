package com.buddheshwar.sensorbox.fragments;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.buddheshwar.sensorbox.R;

import static android.content.Context.SENSOR_SERVICE;

public class MovingBall extends Fragment implements SensorEventListener {


    CustomDrawableView mCustomDrawableView = null;
    ShapeDrawable mDrawable = new ShapeDrawable();
    public float xPosition, xAcceleration,xVelocity = 0.0f;
    public float yPosition, yAcceleration,yVelocity = 0.0f;
    public float xmax,ymax;
    private Bitmap mBitmap;
    private Bitmap mWood;
    private SensorManager sensorManager = null;
    public float frameTime = 0.666f;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Get a reference to a SensorManager
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);

        mCustomDrawableView = new CustomDrawableView(getContext());

        // setContentView(R.layout.main);

        //Calculate Boundry
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        xmax = (float)display.getWidth()-100 ;
        ymax = (float)display.getHeight() - 100;

        View rootView=inflater.inflate(R.layout.fragment_moving_ball,container,false);


        return mCustomDrawableView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            //Set sensor values as acceleration
            yAcceleration = event.values[1];
            xAcceleration = event.values[2];
            updateBall();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    private void updateBall() {


        //Calculate new speed
        xVelocity += (xAcceleration * frameTime);
        yVelocity += (yAcceleration * frameTime);

        xVelocity /= 1.5;
        yVelocity /=1.5;

        //Calc distance travelled in that time
        float xS = (xVelocity/2)*frameTime;
        float yS = (yVelocity/2)*frameTime;

        //Add to position negative due to sensor
        //readings being opposite to what we want!
        xPosition -= xS;
        yPosition -= yS;

        if (xPosition >= xmax) {
            xPosition = xmax;
        } else if (xPosition < 0) {
            xPosition = 0;
        }
        if (yPosition > ymax) {
            yPosition = ymax;
        } else if (yPosition < 0) {
            yPosition = 0;
        }


    }

    public class CustomDrawableView extends View
    {
        public CustomDrawableView(Context context)
        {
            super(context);
            // Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

            Bitmap ball = ((BitmapDrawable)getResources().getDrawable(R.drawable.ball)).getBitmap();
            final int dstWidth = 100;
            final int dstHeight = 100;
            mBitmap = Bitmap.createScaledBitmap(ball, dstWidth, dstHeight, true);


            //  mWood = BitmapFactory.decodeResource(getResources(), R.drawable.wood);


        }

        protected void onDraw(Canvas canvas)
        {
            final Bitmap bitmap = mBitmap;
            // canvas.drawBitmap(mWood, 0, 0, null);
            canvas.drawBitmap(bitmap, xPosition, yPosition, null);
            invalidate();
        }
    }




}
