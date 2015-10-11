package net.webjoy.jackluo.news_webjoy;

/**
 * Created by jackluo on 9/8/15.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 多线程的方式进行异步加载
 */
public class ImageLoader {

    private ImageView mImageView;
    private String mUrl;
    //创建cache
    private LruCache<String,Bitmap> mCaches;   //增加图片缓存 算法
    private ListView mListView;//保存列表
    private Set<NewsAsyncTask> mTask;//保存要加载的AsyncTask;

    public ImageLoader(ListView listView){
        mListView = listView;
        mTask = new HashSet<>();

        //申请缓存空间 获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }
    //设置cache值
    public void addBitmapToCache(String url ,Bitmap bitmap){
        if(getBitmapTocache(url)==null) {
            mCaches.put(url, bitmap);
        }
    }
    public Bitmap getBitmapTocache(String url){
        return mCaches.get(url);
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //缓存图片对正确图片的影响
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    /**
     * 通过多线程加载
     * @param imageView
     * @param url
     */
    public void showImageByThread(ImageView imageView, final String url){

        mImageView = imageView;
        mUrl = url;
        new Thread(){
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    /**
     * 从URL 获取 bitmap
     */
    public Bitmap getBitmapFromUrl(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            //Thread.sleep(1000);
            connection.disconnect();
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }/* catch (InterruptedException e) {
            e.printStackTrace();
        } */finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }

    /**
     * 通过AsyncTask 实现
     */
    public void showImageByAsyncTask(ImageView imageView,String url){
        //从缓存 中取出 对应的图片
        Bitmap bitmap = getBitmapTocache(url);
        if (bitmap==null) {
            imageView.setImageResource(R.mipmap.ic_launcher);
            //new NewsAsyncTask(url).execute(url);
        }else {
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 取消所有正在加载的任务
     */
    public void cancelAllTasks(){
        if (mTask != null){
            //取出所有的任务
            for (NewsAsyncTask task :mTask){
                task.cancel(false);
            }
        }
    }
    /**
     * 用来加载 start 到 end 的图片
     * 加载 指定的图片
     */
    public void loadImages(int start,int end){
        for (int i=start;i<end;i++){
            String url = NewsAdapter.URLS[i];//获取要加载的总图片地址
            //从缓存 中取出 对应的图片
            Bitmap bitmap = getBitmapTocache(url);
            if (bitmap==null) {
                //new NewsAsyncTask(imageView, url).execute(url);
                NewsAsyncTask task = new NewsAsyncTask(url);
                task.execute(url);
                //将任务列表存入集合中
                mTask.add(task);
            }else {
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    /**
     * 创建AsyncTask
     */
    class NewsAsyncTask extends AsyncTask<String,Void,Bitmap>{
//        private ImageView imageView;
        private String mUrl;
        public NewsAsyncTask(String url){
        //    imageView = iView;
            mUrl = url;
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            //从网络中获取图片
            Bitmap bitmap = getBitmapFromUrl(params[0]);
            if (bitmap !=null){
                //将不在缓存中的图片 加入缓存中
                addBitmapToCache(params[0],bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //从
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
            if (imageView != null && bitmap != null){
                imageView.setImageBitmap(bitmap);
            }
           //当AsyncTask 完成,从
            mTask.remove(this);
        }
    }
}
