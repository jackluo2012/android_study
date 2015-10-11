package net.webjoy.jackluo.android_layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {


    TextView title_tv;
    Button button;
    ViewStub viewStub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        title_tv = (TextView) findViewById(R.id.title);
        title_tv.setText("自定义布局");
        button = (Button) findViewById(R.id.button);
        viewStub = (ViewStub) findViewById(R.id.stub);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewStub.inflate();
            }
        });
    }


}
