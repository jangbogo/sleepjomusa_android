package com.kentakang.sleepjomusa;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.AlarmManager;

import com.skydoves.colorpickerview.ColorListener;
import com.skydoves.colorpickerview.ColorPickerView;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class LightActivity extends AppCompatActivity {

    private BluetoothSPP spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        BTService btService = (BTService) getApplication();
        spp = btService.getSpp();

        final ColorPickerView colorPickerView = (ColorPickerView) findViewById(R.id.colorPickerView);
        colorPickerView.setColorListener(new ColorListener() {
            @Override
            public void onColorSelected(int color) {
                int[] colorArr = colorPickerView.getColorRGB();

                spp.send("0q", true);
                spp.send("0e", true);
                spp.send("0w", true);
                spp.send(colorArr[0] + "q", true);
                spp.send(colorArr[1] + "w", true);
                spp.send(colorArr[2] + "e", true);
                setAlarm(getApplicationContext());
            }
        });

        Button button = findViewById(R.id.button) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spp.send("0q", true);
                spp.send("0e", true);
                spp.send("0w", true);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.actionDashboard:
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.actionLight:
                        Intent intent1 = new Intent(getApplicationContext(), LightActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.actionTip:
                        Intent intent2 = new Intent(getApplicationContext(), TipActivity.class);
                        startActivity(intent2);
                        break;
                }

                return true;
            }
        });
    }

    private void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), LightRelease.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 7200000, pIntent);
    }
}
