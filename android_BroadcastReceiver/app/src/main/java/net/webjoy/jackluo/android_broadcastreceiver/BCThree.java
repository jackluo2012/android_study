package net.webjoy.jackluo.android_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by jackluo on 9/17/15.
 */
public class BCThree extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("收到了异步广播");
    }
}
