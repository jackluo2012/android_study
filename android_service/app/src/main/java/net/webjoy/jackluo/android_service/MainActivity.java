package net.webjoy.jackluo.android_service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private Intent intent2;
    MyBindService service;
    private ServiceConnection sc = new ServiceConnection() {
        @Override//当我们的启动源跟Service 成功连接之后将会自动调用这个这个方法
        public void onServiceConnected(ComponentName name, IBinder binder) {
           service =  ((MyBindService.MyBinder)binder).getService();//获取服务对象
        }

        @Override//当我们的启动源跟Service,意外丢失,调用,崩亏和强行杀死
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void doClick(View view){
        switch (view.getId()){
            case R.id.start:
                intent = new Intent(MainActivity.this,MyStartService.class);
               // startActivity(intent);
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
            case R.id.play:
                service.Play();
                break;
            case R.id.pause:
                service.Pause();
                break;
            case R.id.previous:
                service.Previous();
                break;
            case R.id.next:
                service.Next();
                break;

            case R.id.bind:
                intent2 = new Intent(MainActivity.this,MyBindService.class);
                //bindService(intent2,sc, Context.BIND_AUTO_CREATE);
                bindService(intent2,sc, Service.BIND_AUTO_CREATE);
                startService(intent2);
                Log.i("info","ok");
                break;
            case R.id.unbind:
                unbindService(sc);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        stopService(intent2);
        unbindService(sc);
        super.onDestroy();
    }
}
