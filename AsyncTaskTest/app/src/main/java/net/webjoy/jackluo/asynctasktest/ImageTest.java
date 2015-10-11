package net.webjoy.jackluo.asynctasktest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jackluo on 9/6/15.
 */
public class ImageTest extends Activity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private static String url="http://img.my.csdn.net/uploads/201504/12/1428806103_9476.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //开启快异步操作
        //设置传递进去的参数
        new MyAsyncTask().execute(url);
    }
                                    //<url 类型,进度值类型,返回值类型
    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);//显示进度条
        }

                                        @Override
                                        protected void onPostExecute(Bitmap bitmap) {
                                            super.onPostExecute(bitmap);
                                            progressBar.setVisibility(View.GONE);
                                            imageView.setImageBitmap(bitmap);

                                        }

                                        @Override               //可变长的数组
        protected Bitmap doInBackground(String... params) {
            String url = params[0];//获取传递进来的第一个参数
            Bitmap bitmap = null;//要返回的
            URLConnection connection;
            InputStream is;//用于获取数据流
            try {
                connection = new URL(url).openConnection();//获取网络连接对象
                is = connection.getInputStream();//获取输入流
                BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                Thread.sleep(3000);
                //解析输入流
                bitmap = BitmapFactory.decodeStream(bufferedInputStream);//将数据流解析成bitmap
                is.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                                            //将Bitmap   返回
            return bitmap;
        }

    }
}
