package net.webjoy.jackluo.asynctasktest;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by jackluo on 9/6/15.
 */                                     //空值
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {
    @Override
    protected Void doInBackground(Void... params) {
        Log.d("xys","doInBackground");
        publishProgress();
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("xys", "onPreExecute");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d("xys", "onPostExecute");
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        Log.d("xys", "onProgressUpdate");
    }
}
