package com.kentakang.sleepjomusa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;
import app.akexorcist.bluetotohspp.library.BluetoothState;
import app.akexorcist.bluetotohspp.library.DeviceList;

public class LightRelease extends Activity {

    private BluetoothSPP spp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // 블루투스 연결 시
        spp.setBluetoothConnectionListener(new BluetoothSPP.BluetoothConnectionListener() {
            @Override
            public void onDeviceConnected(String name, String address) {
                Toast.makeText(getApplicationContext(),
                        "블루투스가 정상적으로 연결되었습니다.",
                        Toast.LENGTH_SHORT).show();
                spp.send("0q", true);
                spp.send("0e", true);
                spp.send("0w", true);
                finish();
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
}
