package net.webjoy.jackluo.android_json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DownLoadActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private int count =1;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            int result = msg.what;
            count +=result;
            if (count==3){
                textView.setText("download success");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);

        button = (Button) findViewById(R.id.downlad_btn);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        DownLoad load = new DownLoad(handler);
                        load.downLoadFile("http://news.jsyks.com/photo/img1a.xgo-img.com.cn/pics/737/736406.jpg");
                    }
                }.start();



            }
        });
    }


}
