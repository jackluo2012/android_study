package net.webjoy.jackluo.android_fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by jackluo on 9/3/15.
 */
public class MainActivity2 extends Activity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        Button button = (Button) findViewById(R.id.button);
        button.setText("改变");
        textView = (TextView) findViewById(R.id.text);
        /**
         * 做一个事件监听器
         */
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                //改变TextView中的值
                textView.setText("TextView值改变了");
            }
        });

    }
}

