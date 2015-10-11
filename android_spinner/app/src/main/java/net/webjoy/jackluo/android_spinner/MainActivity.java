package net.webjoy.jackluo.android_spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private TextView textView;
    private Spinner spinner;
    private List<String>list;
    private ArrayAdapter<String>adapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main);

        textView = (TextView) findViewById(R.id.textView);
        spinner = (Spinner)findViewById(R.id.spinner );
        textView.setText("你选择的城市是:北京");
        //1.设置数据 源
        list = new ArrayList<String>();
        dataList = new ArrayList<Map<String,Object>>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        //2.ArrayAdapter数组适配器 ()
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        simpleAdapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"image","text"},new int[]{R.id.image,R.id.textView});
        //3.adapter 设置一个下拉列表样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //4. spinner 加载适配器
        //spinner.setAdapter(adapter);
        spinner.setAdapter(simpleAdapter);

        //5.spinner 设置监听器
        spinner.setOnItemSelectedListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i=0;i<list.size();i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.ic_launcher);
            map.put("text", list.get(i));
            dataList.add(map);
        }

        return dataList;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityName = adapter.getItem(position);
        //String name = list.get(position);
        textView.setText("你选择的城市是:"+cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
