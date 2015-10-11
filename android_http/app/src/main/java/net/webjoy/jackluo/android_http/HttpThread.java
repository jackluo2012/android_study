package net.webjoy.jackluo.android_http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jackluo on 9/26/15.
 */
public class HttpThread extends Thread {
    private String url;
    private WebView webView;
    private Handler handler;
    private ImageView imageView;

    public  HttpThread(String url,WebView webView,Handler handler){
        this.url = url;
        this.webView = webView;
        this.handler = handler;
    }
    public  HttpThread(String url,ImageView imageView,Handler handler){
        this.url = url;
        this.imageView  = imageView;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {

            URL httpUrl = new URL(url);
            //通过超时的时间请求网络
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);//获取输入流
            InputStream inputStream = connection.getInputStream();
            FileOutputStream out = null;
            File downloadFile = null ;//文件名
            //if (EnvironmentCompat.getStorageState().equals(EnvironmentCompat.MEDIA_UNKNOWN)){
            //创建文件名
            String fileName = String.valueOf(System.currentTimeMillis());
            //判断SD是不是存在
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                File parent = Environment.getExternalStorageDirectory();
                //创建目录
                downloadFile = new File(parent,fileName);
                //文件的输出目录  
                out = new FileOutputStream(downloadFile);
            };

            //写之前要创建一个缓冲区 2KB的
            byte[] bytes = new byte[2*1024];
            int len;
            if (out != null){
                //读流   有数据 //写入sd卡中
                while ((len = inputStream.read(bytes))!=-1 ){
                    out.write(bytes,0,len);
                }
            }
        //创建handler 去更新
            final Bitmap bitmap = BitmapFactory.decodeFile(downloadFile.getAbsolutePath());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });



            /* 获取网页
            final StringBuffer sb = new StringBuffer();
            //通过读入流 放入缓冲区中
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String str;
            //每次读一行,添加
            while ((str = reader.readLine())!=null){
                sb.append(str);
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //通过加载本地的方式添加
                    webView.loadData(sb.toString(), "text/html;charset=utf-8", null);
                }
            });
            */
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
