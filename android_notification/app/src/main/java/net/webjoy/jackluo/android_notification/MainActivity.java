package net.webjoy.jackluo.android_notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //private NotificationManagerCompat managerCompat ;//通知控制类
    NotificationManager managerCompat;
    int notification_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerCompat = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        findViewById(R.id.btn_send).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send:
                sendNotification();
                break;
            case R.id.btn_cancel:
                managerCompat.cancel(notification_ID);
                break;

        }
    }

    /**
     * 构造 notification并发送到通知栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(){
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);//设置图标
        builder.setTicker("Hello");//设置手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());//设置时间
        builder.setContentTitle("通知通知");//设置标题
        builder.setContentText("我来自NotificationDemo");//设置通知内容
        builder.setContentIntent(pendingIntent);//点击后的意图
        builder.setDefaults(Notification.DEFAULT_SOUND);//设置提示声音
        builder.setDefaults(Notification.DEFAULT_LIGHTS);//设置指示灯
        builder.setDefaults(Notification.DEFAULT_VIBRATE);//设置震动
//        builder.setDefaults(Notification.DEFAULT_ALL);//设置上面几个
        Notification notification = builder.build();//4.1以上 builder.getNotification() 4.1以下的
        managerCompat.notify(notification_ID,notification);

    }
}
