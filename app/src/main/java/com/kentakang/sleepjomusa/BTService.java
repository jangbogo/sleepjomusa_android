package com.kentakang.sleepjomusa;

import android.app.Application;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;

public class BTService extends Application {
    private BluetoothSPP spp = null;

    public BluetoothSPP getSpp() {
        return spp;
    }

    public void setSpp(BluetoothSPP spp) {
        this.spp = spp;
    }

    public boolean isNull() {
        if (spp == null) {
            return true;
        } else {
            return false;
        }
    }
}
