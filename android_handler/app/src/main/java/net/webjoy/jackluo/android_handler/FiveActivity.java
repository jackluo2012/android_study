package net.webjoy.jackluo.android_handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FiveActivity extends AppCompatActivity {

    private TextView textView;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            textView.setText("ok");
        }
    };
    /**
     * 4个Handler 更新
     * 通过handler 更新
     */
    private void handler1(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok");
            }
        });
    }

    /**
     * 常用的
     * 通过发送消息更新
     */
    private void handler2(){
        handler.sendEmptyMessage(1);
    }

    /**
     * 通过
     * @param savedInstanceState
     */
    private void updateUI(){
         runOnUiThread(new Runnable() {
             @Override
             public void run() {
                 textView.setText("ok");
             }
         });
    }
    private void viewUI(){
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("ok");
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five);
        textView = (TextView) findViewById(R.id.textView);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
//                    handler2();
//                    updateUI();
                    viewUI();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
