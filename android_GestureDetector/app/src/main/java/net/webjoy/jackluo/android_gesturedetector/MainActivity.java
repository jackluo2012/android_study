package net.webjoy.jackluo.android_gesturedetector;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    GestureDetector myGestureDetector;

    class myGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if ((e1.getX()-e2.getX())>50){
                Toast.makeText(MainActivity.this,"从右往左滑动",Toast.LENGTH_LONG).show();
            }else if((e2.getX()-e1.getX())>50){
                Toast.makeText(MainActivity.this,"从左往右滑动",Toast.LENGTH_LONG).show();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGestureDetector = new GestureDetector(MainActivity.this,new myGestureListener());
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override//可以得到触摸屏目发成   Event事件
            public boolean onTouch(View v, MotionEvent event) {

                //转发事件
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }


}
