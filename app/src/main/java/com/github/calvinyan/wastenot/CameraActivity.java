package com.github.calvinyan.wastenot;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class CameraActivity extends AppCompatActivity {

    private static String TAG = "CameraActivity";

    private Camera camera;
    private CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        try {
            camera = Camera.open();
        } catch (Exception e) {
            Log.e(TAG, "Exception when attempting to access camera!");
        }

        cameraView = new CameraView(this, camera);

        ((FrameLayout) findViewById(R.id.camera_frame)).addView(cameraView);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
