package net.webjoy.jackluo.android_json;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private JsonAdapter adapter;
    private Handler handler= new Handler();
    final String url = "http://192.168.0.107:8080";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);
        listView = (ListView) findViewById(R.id.list);
        adapter = new JsonAdapter(this);

        new HttpJson(url,listView,adapter,handler).start();
    }


}
