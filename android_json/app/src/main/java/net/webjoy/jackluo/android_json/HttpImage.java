package net.webjoy.jackluo.android_json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jackluo on 9/28/15.
 */
public class HttpImage extends Thread {
    private ImageView imageView;
    private String url;
    private Handler handler;


    public HttpImage(String url,Handler handler,ImageView imageView){
        this.url = url;
        this.handler = handler;
        this.imageView = imageView;
    }
    @Override
    public void run() {
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            //创建bitmap
            final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                }
            });


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
