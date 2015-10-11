package net.webjoy.jackluo.android_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by jackluo on 9/17/15.
 */
public class MyStartService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("info","Service--onBind()");
        return null;
    }

    public MyStartService() {
        super();
        Log.i("info", "Service--MyStartService()");
    }

    @Override
    public void onCreate() {
        Log.i("info","Service--onCreate()");
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("info","Service--onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i("info","Service--onDestroy()");
        super.onDestroy();

    }
}
