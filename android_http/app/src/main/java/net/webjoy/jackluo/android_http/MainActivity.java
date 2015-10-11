package net.webjoy.jackluo.android_http;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private WebView webView;
    private Handler handler = new Handler();
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.web);
        imageView = (ImageView) findViewById(R.id.image);
        //new HttpThread("http://www.baidu.com",webView,handler).start();
        new HttpThread("http://cdn.wallxd.com/51b05f046ba2b62533.jpg",imageView,handler).start();


    }


}
