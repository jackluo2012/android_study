package net.webjoy.jackluo.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ThreeActivity extends AppCompatActivity {
    private TextView textView;
    private HandlerThread thread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("handler Thread");
        setContentView(textView);

        //创建looper 跟
        //模拟异步任务
        //比较消号时的操作,比如图片下载
        thread = new HandlerThread("handler thread");
        thread.start();

        handler = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                System.out.println("current thread-------->"+thread.currentThread());
            }
        };
        handler.sendEmptyMessage(1);
    }


}
