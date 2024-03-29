package com.example.flotingapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int SYSTEM_ALERT_WINDOW_PERMISSION = 2084;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            askPermission();
        }

        findViewById(R.id.buttonCreateWidget).setOnClickListener(this);
    }


    private void askPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, SYSTEM_ALERT_WINDOW_PERMISSION);
    }

    @Override
    public void onClick(View v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            startService(new Intent(MainActivity.this, FloatingService.class));
            finish();
        } else if (Settings.canDrawOverlays(this)) {
            startService(new Intent(MainActivity.this, FloatingService.class));
            finish();
        } else {
            askPermission();
            Toast.makeText(this, "You need System Alert Window Permission to do this", Toast.LENGTH_SHORT).show();
        }


    }
    ////Firebase///


}