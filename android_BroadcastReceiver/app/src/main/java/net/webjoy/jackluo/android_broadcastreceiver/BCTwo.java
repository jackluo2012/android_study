package net.webjoy.jackluo.android_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jackluo on 9/17/15.
 */
public class BCTwo extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        System.out.println("reveriver2收到消息:" + s);


        Bundle bundle = getResultExtras(true);
        String test = bundle.getString("test");
        System.out.println("得到的处理结果是:"+test);
    }
}
