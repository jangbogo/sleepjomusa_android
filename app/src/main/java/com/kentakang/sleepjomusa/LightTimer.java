package com.kentakang.sleepjomusa;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LightTimer extends Service {
    public LightTimer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
