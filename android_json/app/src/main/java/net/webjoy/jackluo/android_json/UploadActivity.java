package net.webjoy.jackluo.android_json;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class UploadActivity extends AppCompatActivity {

    private Button downlad_btn;

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
        setContentView(R.layout.activity_upload);
        button = (Button) findViewById(R.id.upload);
        downlad_btn = (Button) findViewById(R.id.downlad_btn);
        //上传
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.0.107:8080/upload";
                File file = Environment.getExternalStorageDirectory();
                File fileAbs = new File(file, "g3.png");
                String fileName = fileAbs.getAbsolutePath();
                UploadThread thread = new UploadThread(url, fileName);
                thread.start();
            }
        });

        //下载
        downlad_btn = (Button) findViewById(R.id.downlad_btn);
        textView = (TextView) findViewById(R.id.textView);
        downlad_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        DownLoad load = new DownLoad(handler);
                        load.downLoadFile("http://192.168.0.107:8080/static/img/g3.png");
                    }
                }.start();



            }
        });

    }


}
