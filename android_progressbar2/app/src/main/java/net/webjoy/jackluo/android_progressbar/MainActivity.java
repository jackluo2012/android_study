package net.webjoy.jackluo.android_progressbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {

    private ProgressBar progressBar;
    private Button add;
    private Button reduce;
    private Button reset;
    private TextView text;
    private ProgressDialog progressDialog;
    private Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启用窗口特征,启用带进度和不带进度的进度条
//        requestWindowFeature(Window.FEATURE_PROGRESS);
//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
//        setContentView(R.layout.activity_main);

        //显示两种进度条

        setProgressBarVisibility(true);
        setProgressBarIndeterminate(true);
        setProgress(600);


        setContentView(R.layout.main);
        init();
    }

    private void init() {

        progressBar = (ProgressBar) findViewById(R.id.horiz);
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);
        reset =(Button) findViewById(R.id.reset);
        text = (TextView) findViewById(R.id.text);
        show = (Button) findViewById(R.id.show);
        //获取 第一显示 进度
        int first = progressBar.getProgress();
        //获取 第二显示 进度
        int second = progressBar.getSecondaryProgress();
        //获取 进度条的最大进度
        int max = progressBar.getMax();

        text.setText("第一进度百分比: "+ first*10/max *10 +"%第二进度的百分比: " + second*10 / max * 10 +'%');
        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
        reset.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:{
                //进度条,+
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            }
            case R.id.reduce:{
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            }
            case R.id.reset:{
                progressBar.setProgress(50);
                progressBar.setSecondaryProgress(80);
                break;
            }
            case R.id.show:{
                /**
                 * 页面显示 风格
                 */

                //新建ProgressDialog对象
                progressDialog = new ProgressDialog(MainActivity.this);
                //设置显示 风格  横向的显示进度条
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //设置标题
                progressDialog.setTitle("欢乐网");
                //设置对话框里的文字内容
                progressDialog.setMessage("欢迎大家支持我!!!");
                //设置图标
                progressDialog.setIcon(R.mipmap.ic_launcher);

                /**
                 * 设置progressBar的一些属性
                 */

                //设置最大进度
                progressDialog.setMax(100);

                //设定初始化已经增长的进度
                progressDialog.incrementProgressBy(50);
                //进度条是明确显示 进度的
                progressDialog.setIndeterminate(false);


                /**
                 * 设置一个确定按钮
                 */
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"欢迎大家支持我!!!",Toast.LENGTH_SHORT).show();
                    }
                });

                //是否可以通过返回按钮退出对话框
                progressDialog.setCancelable(true);

                //显示ProgressDialog
                progressDialog.show();

                break;
            }

        }
        text.setText("第一进度百分比: "+ progressBar.getProgress()*10/progressBar.getMax() *10 +"%第二进度的百分比: " + progressBar.getSecondaryProgress()*10 / progressBar.getMax() * 10 +'%');
    }
}
