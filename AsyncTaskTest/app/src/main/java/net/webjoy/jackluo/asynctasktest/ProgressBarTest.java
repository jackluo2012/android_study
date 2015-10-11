package net.webjoy.jackluo.asynctasktest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;

/**
 * Created by jackluo on 9/7/15.
 */
public class ProgressBarTest extends Activity {

    private ProgressBar progressBar;
    private MyAsyncTast mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.progressbar);
//        progressBar = (ProgressBar) findViewById(R.id.pg);
//        mTask = new MyAsyncTast();
//        mTask.execute();
    }

    /**
     * 程序暂停
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(mTask!=null && mTask.getStatus()==AsyncTask.Status.RUNNING){
            //cancel方法只是将AsyncTask标记为cacel状态,并不是真正的取消线程,在Java中不能取消
            mTask.cancel(true);//取消掉
        }
    }

    class MyAsyncTast extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for (int i=0;i<100;i++){
                //如果用户取消直接return
                if(isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //如果用户取消直接return
            if(isCancelled()){
                return;
            }
            //获取进度更新值
            progressBar.setProgress(values[0]);//获取参数
        }
    }
}
