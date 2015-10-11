package net.webjoy.jackluo.android_webview;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private String url = "http://2014.qq.com";
    private WebView webView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

//        Uri uri = Uri.parse(url);//url 为你要链接的地址
//        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);
        init();
    }

    private void init() {

        webView = (WebView) findViewById(R.id.web);

        //加载本地文件
        webView.loadUrl("file:///android_asset/example.html");

        //加载Web 外部资源
        webView.loadUrl("http://www.baidu.com");
        //覆盖WebView默认通过第三方或者是系统浏览器找开网页行为,使用网页可以在WebView中打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                //返回值是true的时候控制网页在WebView中打开,如果为false调用系统 浏览器或第三方浏览器去打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助webView去处理一些页面控制和请求通知
        });
        //启用支持JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //WebView 优先使用缓存加载
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebChromeClient(new WebChromeClient() {

            //判断页面加载的过程
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                //newProgress  1-100之间的整数
                if (newProgress == 100) {
                    //网页    加载完毕
                    //关闭
                    closeDialog();
                } else {
                    //网页加载 中
                    openDialog(newProgress);
                }

                super.onProgressChanged(view, newProgress);
            }

            private void closeDialog() {
                 if (progressDialog !=null  && progressDialog.isShowing()){
                     progressDialog.dismiss();
                     progressDialog = null;
                 }
            }

            private void openDialog(int newProgress) {
                 if (progressDialog == null){
                           progressDialog = new ProgressDialog(MainActivity.this);
                            progressDialog.setTitle("正在加载");
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                            progressDialog.setProgress(newProgress);
                            progressDialog.show();
                 }else {
                     progressDialog.setProgress(newProgress);
                 }
            }
        });
    }




    //改写物理按键--返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){

            Toast.makeText(this,webView.getUrl(),Toast.LENGTH_SHORT).show();
            if(webView.canGoBack()){
                webView.goBack();//返回上一页面
                return true;
            }else {
                System.exit(0);//退出 程序
            }
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }
}
