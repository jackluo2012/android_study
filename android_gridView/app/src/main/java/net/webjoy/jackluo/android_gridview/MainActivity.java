package net.webjoy.jackluo.android_gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView gridView;
    private List<Map<String,Object>>dataList;
    private int[]icons ={R.drawable.address_book,R.drawable.calendar,
            R.drawable.camera,R.drawable.clock,R.drawable.games_control,
            R.drawable.messenger,R.drawable.ringtone,R.drawable.settings,
            R.drawable.speech_balloon,R.drawable.weather,R.drawable.world,
            R.drawable.youtube};
    private String[]iconsNames = {
            "通讯录","日历","照相机","闹钟","游戏","短信","铃声","设置","语音","天气","浏览器","视频"
    };
    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridView = (GridView) findViewById(R.id.gridView);

        //1.准备数据源
        //2.新建适配器(SimpleAdapter)
        //3.GridView加载视配器
        //4.GridView配置事件监听器 (OnItemClikListener);
        dataList = new ArrayList<Map<String,Object>>();


        simpleAdapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"image","text"},new int[]{R.id.image,R.id.text});

        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String,Object>> getData() {


        for (int i=0;i<icons.length;i++){
            Map<String,Object>map = new HashMap<String,Object>();
            map.put("image",icons[i]);
            map.put("text",iconsNames[i]);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"我是"+iconsNames[position],Toast.LENGTH_SHORT).show();
    }
}
