package com.kentakang.sleepjomusa;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

import com.skydoves.colorpickerview.ColorPickerView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private BluetoothSPP spp;
    private ColorPickerView colorPickerView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.mainBackground);
        GradientDrawable drawable = (GradientDrawable) getApplicationContext().getDrawable(R.drawable.round_background);
        imageView.setBackground(drawable);
        imageView.setClipToOutline(true);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        View navigation = bottomNavigationView.findViewById(R.id.actionDashboard);
        navigation.performClick();

        BTService btService = (BTService) getApplication();

        if (btService.isNull()) {
            initBluetooth();
            btService.setSpp(spp);
        } else {
            spp = btService.getSpp();
        }

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

        spp.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            @Override
            public void onDataReceived(byte[] data, String message) {
                int humidity = Integer.parseInt(message.substring(10, 12));
                int temp = Integer.parseInt(message.substring(28, 30));
                TextView textTemp = findViewById(R.id.textThermo);
                TextView textHumidity = findViewById(R.id.textThermo2);
                TextView textEmote = findViewById(R.id.textEmote);
                ImageView iconEmote = findViewById(R.id.iconEmote);
                ImageView imageTemp = findViewById(R.id.iconThermometer);
                ImageView imageHumidity = findViewById(R.id.iconThermometer2);

                textTemp.setText(String.valueOf(temp));
                textHumidity.setText(String.valueOf(humidity));

                if (humidity <= 40) {
                    textEmote.setText("잠들기에는 건조한 환경이에요.\n가습이 필요할 것 같아요.");
                    iconEmote.setImageResource(R.drawable.icon_soso);
                } else if (humidity <= 70) {
                    textEmote.setText("잠들기에 적당한 환경이에요!");
                    iconEmote.setImageResource(R.drawable.icon_happy);
                } else {
                    textEmote.setText("잠들기에는 습한 환경이에요 ㅠ\n제습이 필요할 것 같아요.");
                    iconEmote.setImageResource(R.drawable.icon_bad);
                }

                if (temp <= 10) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_10);
                } else if (temp <= 20) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_20);
                } else if (temp <= 30) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_30);
                } else if (temp <= 40) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_40);
                } else if (temp <= 50) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_50);
                } else if (temp <= 60) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_60);
                } else if (temp <= 70) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_70);
                } else if (temp <= 80) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_80);
                } else if (temp <= 90) {
                    imageTemp.setImageResource(R.drawable.icon_thermo_90);
                } else {
                    imageTemp.setImageResource(R.drawable.icon_thermo_100);
                }

                if (humidity <= 10) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_10);
                } else if (humidity <= 20) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_20);
                } else if (humidity <= 30) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_30);
                } else if (humidity <= 40) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_40);
                } else if (humidity <= 50) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_50);
                } else if (humidity <= 60) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_60);
                } else if (humidity <= 70) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_70);
                } else if (humidity <= 80) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_80);
                } else if (humidity <= 90) {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_90);
                } else {
                    imageHumidity.setImageResource(R.drawable.icon_thermo_100);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        spp.stopService();
    }

    public void onStart() {
        super.onStart();

        if (!spp.isBluetoothEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, BluetoothState.REQUEST_ENABLE_BT);
        } else {
            if (!spp.isServiceAvailable()) {
                spp.setupService();
                spp.startService(BluetoothState.DEVICE_OTHER);
                setup();
            }
        }
    }

    public void initBluetooth() {
        spp = new BluetoothSPP(this);

        Intent intent = new Intent(getApplicationContext(), DeviceList.class);
        startActivityForResult(intent, BluetoothState.REQUEST_CONNECT_DEVICE);

        // 블루투스 사용 불가 시
        if (!spp.isBluetoothAvailable()) {
            Toast.makeText(getApplicationContext(),
                    "블루투스 사용이 불가능합니다.",
                    Toast.LENGTH_SHORT).show();

            finish();
        }

        // 블루투스 데이터 수신
        spp.setOnDataReceivedListener(new BluetoothSPP.OnDataReceivedListener() {
            @Override
            public void onDataReceived(byte[] data, String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        // 블루투스 연결 시
        spp.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext(),
                        "블루투스가 정상적으로 연결되었습니다.",
                        Toast.LENGTH_SHORT).show();
                spp.send("t", true);
            }

            @Override
            public void onDeviceDisconnected() {
                Toast.makeText(getApplicationContext(),
                        "Connection lost",
                        Toast.LENGTH_SHORT).show();
            }

            // 연결 실패시
            @Override
            public void onDeviceConnectionFailed() {
                Toast.makeText(getApplicationContext(),
                        "연결할 수 없습니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setup() {
    }

    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        if (reqCode == BluetoothState.REQUEST_CONNECT_DEVICE) {
            if (resultCode == Activity.RESULT_OK) {
                spp.connect(data);
            }
        } else if (reqCode == BluetoothState.REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                spp.setupService();
                spp.startService(BluetoothState.DEVICE_OTHER);
                setup();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Bluetooth was not enabled.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}