package net.webjoy.jackluo.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    /*
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            System.out.println("UI--------->"+Thread.currentThread());
        }
    };
    */
    private Handler handler;
    //自定义handler
    class MyThread extends Thread{
        public Handler handler;
        public Looper looper;
        @Override
        public void run() {
            Looper.prepare();//创建一个looper
            looper = Looper.myLooper();
            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    //super.handleMessage(msg);
                    //打印出当前线程
                    System.out.println("currentThread:"+Thread.currentThread());
                }
            };//根据当前线程去拿到Looper
            Looper.loop();//循环处理消息
        }
    }
    private MyThread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("Hello Handler");
        setContentView(textView);
        thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(500);//休眠半秒中
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.handler.sendEmptyMessage(1);

        //handler.sendEmptyMessage(1);

        //setContentView(R.layout.main);

        //threadlocal set get 方法

        //ActivityThead main 创建一个线程 Looper 创建 messagequeque
    //    handler.sendEmptyMessage(1);


        //要注意一些地方 轮循
        //在一个线程中 looper 无在run 中创建,存在先后问题   
        handler = new Handler(thread.looper){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg);
            }
        };
        handler.sendEmptyMessage(1);
    }

}
