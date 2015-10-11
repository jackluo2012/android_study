package net.webjoy.jackluo.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
        //返回true 时 截获数据
            return false;
        }
    }){
        //发送消息
        @Override
        public void handleMessage(Message msg) {

        //    textView.setText(""+msg.arg1+"-"+msg.arg2);
            textView.setText(""+msg.obj);
            Toast.makeText(getApplicationContext(),"2",Toast.LENGTH_SHORT).show();
        }
    };
    private ImageView imageView;

    private int images[] = {R.drawable.g1,R.drawable.g2,R.drawable.g3};
    private int index;
    private MyRunnable myRunnable = new MyRunnable();

    class Person {
        public int age;

        public String name;
        @Override
        public String toString() {
            return "name="+name+",age="+age;
        }
    }

    class MyRunnable implements Runnable{
        @Override
        public void run() {
            index++;
            index=index%3;
            imageView.setImageResource(images[index]);
            handler.postDelayed(myRunnable,1000);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button) findViewById(R.id.button);

        handler.postDelayed(myRunnable,1000);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //发送一个message
/*
                    Message message = new Message();
                    message.arg1 = 88;
                    message.arg2 = 100;
*/
                    //先检查系统中,是否有,如果没有就更新
                    Message message = handler.obtainMessage();
                    Person p = new Person();
                    p.name = "jackluo";
                    p.age = 26;
                    message.obj = p;
                    //handler.sendMessage(message);
                    message.sendToTarget();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        /*
        new Thread(){
            public void run(){
                try {
                    Thread.sleep(1000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("update thread");
                        }
                    });

                //    textView.setText("update thread");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(myRunnable);
                //发送一个空消息
                handler.sendEmptyMessage(1);
            }
        });
    }


}
