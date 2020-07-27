package com.buddheshwar.sensorbox.movingcube;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MovinfCubeFragment extends Fragment {


    /** Hold a reference to our GLSurfaceView */
    private GLSurfaceView mGLSurfaceView;
    private SensorFusion mSensorFusion;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mSensorFusion = new SensorFusion(getContext());
        mGLSurfaceView = new GLSurfaceView(getContext());

        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if (supportsEs2)
        {
            // Request an OpenGL ES 2.0 compatible context.
            mGLSurfaceView.setEGLContextClientVersion(2);

            // Set the renderer to our demo renderer, defined below.
            mGLSurfaceView.setRenderer(new Renderer(mSensorFusion));


            //mGLSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        }
        else
        {
            // This is where you could create an OpenGL ES 1.x compatible
            // renderer if you wanted to support both ES 1 and ES 2.

        }

        return mGLSurfaceView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }
}
