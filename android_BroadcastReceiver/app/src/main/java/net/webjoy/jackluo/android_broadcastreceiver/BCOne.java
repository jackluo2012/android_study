package net.webjoy.jackluo.android_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jackluo on 9/17/15.
 */
public class BCOne extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String s = intent.getStringExtra("msg");
        System.out.println("reveriver1收到消息:" + s);
        abortBroadcast();//截断广播 要报错 一般广播
        //做数据返回
        Bundle bundle = new Bundle();
        bundle.putString("test","广播处理的数据");
        setResultExtras(bundle);
    }
}
