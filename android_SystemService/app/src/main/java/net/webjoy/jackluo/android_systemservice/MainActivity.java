package net.webjoy.jackluo.android_systemservice;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main,null);
        setContentView(view);

    }

    public void doClick(View view){
        switch (view.getId()){
            case R.id.network:
                if (isNetWorkConnected(MainActivity.this)==true){
                    Toast.makeText(MainActivity.this,"网络是打开的",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"网络未连接的",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.wifi_manager:
                WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(WIFI_SERVICE);
                if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this,"wifi 已经关闭",Toast.LENGTH_SHORT).show();
                }else {
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this,"wifi 已经打开",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.getvoice:
                AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
                int max =     audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);//系统最大音量值
                int current =     audioManager.getStreamVolume(AudioManager.STREAM_RING);//系统最大音量值
                Toast.makeText(MainActivity.this,"系统的最大音量为:"+max+",当前音量是:"+current,Toast.LENGTH_SHORT).show();
                break;
            case R.id.getpackagename:
                String packagename =  MainActivity.this.getPackageName();
                
                Toast.makeText(MainActivity.this,"包名是:"+packagename,Toast.LENGTH_SHORT).show();
                break;
        }
    }
    public boolean isNetWorkConnected(Context context){
        if (context != null){
            ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE); //context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo mNetWorkInfo =  mConnectivityManager.getActiveNetworkInfo();
            if (mNetWorkInfo != null){
                return mNetWorkInfo.isAvailable();
            }
        }
        return false;
    }
}
