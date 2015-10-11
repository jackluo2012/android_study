package net.webjoy.jackluo.android_broadcastreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //动态注册
        IntentFilter intentFilter = new IntentFilter("BC_One");
        BCTwo bcTwo = new BCTwo();
        registerReceiver(bcTwo,intentFilter);

        Toast.makeText(MainActivity.this,"ss",Toast.LENGTH_SHORT).show();
    }
    public void doClick(View view){
        switch (view.getId()){
            case R.id.send_nornal://发送一个普通广播
                Intent intent = new Intent();
                intent.putExtra("msg","这是一条普通广播");
                intent.setAction("BC_One");
                sendBroadcast(intent);

                break;
            case R.id.send_short://发送一条有序广播
                Intent intent2 = new Intent();
                intent2.putExtra("msg","这是一条有序广播");
                intent2.setAction("BC_One");
                sendOrderedBroadcast(intent2, null);
                break;
            case R.id.send_shift://发送一条异步广播
                //不同先发送后收消息
                Intent intent3 = new Intent();
                intent3.putExtra("msg", "这是一条异步广播");
                intent3.setAction("BC_Three");
                //sendOrderedBroadcast(intent3,null);
                sendStickyBroadcast(intent3);
                IntentFilter intentFilter = new IntentFilter("BC_Three");
                BCThree bcThree= new BCThree();
                registerReceiver(bcThree, intentFilter);
                break;
        }
    }
}
