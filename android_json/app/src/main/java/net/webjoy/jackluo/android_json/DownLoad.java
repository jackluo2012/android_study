package net.webjoy.jackluo.android_json;

/**
 *  1.http Range "bytes="+  start+end
 *  2.RandomAccessFile设置写入的位置
 *  3开启多线程下载
 *
 * Created by jackluo on 9/30/15.
 */

import android.os.Environment;
import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 多线程下载
 */
public class DownLoad {
    //创建一个线程池      创建三个线程
    private Executor threadPool = Executors.newFixedThreadPool(3);

    private Handler handler;

    public DownLoad(Handler handler){
        this.handler = handler;
    }

    //创建一个线程池对象
    static class DownLoadRunnable implements Runnable{

        private String url;
        private String fileName;
        private long start ;//开始位置
        private long end;//结束位置
        private Handler handler;
        public DownLoadRunnable(String url,String fileName,long start,long end,Handler handler){
            this.url = url;
            this.fileName = fileName;
            this.start = start;
            this.end = end;
            this.handler = handler;
        }

        @Override
        public void run() {

            try {
                URL httpUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(5000);
                //设置下载位置  向服务器拿到指定的流信息
                connection.setRequestProperty("Range", "bytes=" + start + "-" + end);
                //创建一个文件  根据指定位置写入信息
                RandomAccessFile accessFile = new RandomAccessFile(new File(fileName),"rwd");
                accessFile.seek(start);//设置读写的位置
                InputStream inputStream = connection.getInputStream();
                byte[] b = new byte[1024*4];//设置缓冲区的大小存
                int len=0;
                while ((len = inputStream.read(b))!=-1){
                    accessFile.write(b,0,len);
                }
                if (accessFile !=null){
                    accessFile.close();
                }
                if (inputStream !=null){
                    inputStream.close();
                }
                //给主线程发送一个消息
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void downLoadFile(String url){
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int count = connection.getContentLength();//获取了图片的大小
            int block = count/3;


            //算线程的启始位置
            String fileName = getFileName(url);
            File parent = Environment.getExternalStorageDirectory();
            File fileDownLoad = new File(parent,fileName);
            /**
             * 11 / 3 = 每个下载 3个字节3 还余出2个字节空间
             * 第一个线程 0-2
             * 第二个线程 3-5
             * 第三个线程 6-10
             *
             *
             */
            for (int i=0;i<3;i++){
                long start = i*block;
                long end = (i+1)*block -1;
                if (i==2){
                    end = count;
                }
                DownLoadRunnable runnable = new DownLoadRunnable(url, fileDownLoad.getAbsolutePath(),start,end,handler);
                //提交任务
                threadPool.execute(runnable);
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取 URL后缀名
     */
    public String getFileName(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

}
