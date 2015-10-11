package net.webjoy.jackluo.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 主线程调用子线程示例
 */
public class FourActivity extends AppCompatActivity implements View.OnClickListener{

    //创建主线程的handler
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Message message = new Message();
            System.out.println("main Handler");
            //向子线程发送消息
            threadHanlder.sendMessageDelayed(message,1000);
        }
    };

    private Handler threadHanlder;
    private Button send;
    private Button stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four);
        send = (Button) findViewById(R.id.send);
        stop = (Button) findViewById(R.id.stop);
        send.setOnClickListener(this);
        stop.setOnClickListener(this);
        HandlerThread thread = new HandlerThread("handlerThread");
        thread.start();
        //创建子线程的handler
        threadHanlder = new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                Message message = new Message();
                System.out.println("thread handler");
                //向主线程发送消息
                handler.sendMessageDelayed(message,1000);
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                handler.sendEmptyMessage(1);
                break;
            case R.id.stop:
                handler.removeMessages(1);
                break;
        }
    }
}
