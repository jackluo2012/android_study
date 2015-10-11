package net.webjoy.jackluo.android_scrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv;
    private ScrollView scrollView;
    private Button upbtn;
    private Button downtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv = (TextView) findViewById(R.id.content);
        //tv.setText(R.string.content);
        tv.setText(getResources().getString(R.string.content));
        upbtn = (Button) findViewById(R.id.up);
        downtb = (Button) findViewById(R.id.down);


        upbtn.setOnClickListener(this);
        downtb.setOnClickListener(this);


        scrollView = (ScrollView) findViewById(R.id.scroll);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        /**
                         * (1) getScrollY() --滚动条滑动的距离
                         * (2) getMeasuredHeight-- 当一个屏显示得下 这两个值相等
                         * (3)getHeight--
                         */

                        //顶部状态
                        if (scrollView.getScrollY() <= 0) {
                            Log.i("Main", "顶部");
                        }
                        //底部状态
                        //textView 总高度=一屏幕的高度+滚动条的滚动距离
                        if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY()) {
                            Log.i("Main", "滑动到底部");
                            Log.i("Main", "scrollView.getChildAt(0).getMeasuredHeight()" + scrollView.getChildAt(0).getMeasuredHeight());
                            Log.i("Main", "scrollView.getHeight()" + scrollView.getHeight());
                            Log.i("Main", "scrollView.getScrollY()" + scrollView.getScrollY());
                        }
                        tv.append(getResources().getString(R.string.content));
                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //scrollTo:以滚动视图起启位置开始算的
            //scrollBy:去滚动前一次滚动的距离


            case R.id.up:{
                //scrollView.scrollTo(0,-30);
                scrollView.scrollBy(0, -30);
                break;
            }
            case R.id.down:{
                //scrollView.scrollTo(0,30);
                scrollView.scrollBy(0,30);
                break;
            }
        }
    }
}
