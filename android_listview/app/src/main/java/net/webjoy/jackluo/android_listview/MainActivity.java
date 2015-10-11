package net.webjoy.jackluo.android_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AbsListView.OnScrollListener {
    private ListView listView;
    private ArrayAdapter<String>arr_adapter;
    private SimpleAdapter simp_adapter;
    private List<Map<String,Object>> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //进行数据 绑定
        listView = (ListView) findViewById(R.id.listView);

        //1.新建一个数据适配器
        //ArrayAdapter(上下文,当前listView 加载 的每一项所对应的布局文件,数据源)
        //SimpAdapter()
        /**
         * context:数据源(List<? extends Map<String,?> data) 一个Map所组成的List集合
         *      每一个Map都会对应ListView列表中的一行
         *      每一个Map(键-值对)中的键必须 包含所有在form中所指定的键
         *      resource:列表项的布局文件ID
         *      from:Map中的键值
         *      to:绑定数据视图中的,ID 与 from成对应关系
         */


        //2.适配器加载数据源
        String[] arr_data = {"欢乐网1","欢乐网2","欢乐网3","欢乐网4","欢乐网5","欢乐网6","欢乐网6"};
        //初始化simp_adapter需要的数据
        dataList = new ArrayList<Map<String,Object>>();

        arr_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_data);

        simp_adapter = new SimpleAdapter(this,getData(),R.layout.item,new String[]{"pic","text"},new int[]{R.id.pic,R.id.text});

        //3.视图加载 适配器
        //listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    private List<Map<String,Object>> getData(){
        for (int i=0;i<5;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("pic",R.mipmap.ic_launcher);
            map.put("text","欢乐网"+i);
            dataList.add(map);
        }

        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String text  = listView.getItemAtPosition(position)+"";
        Toast.makeText(this,"position="+position+" text="+text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch(scrollState){
            case SCROLL_STATE_FLING:
                Log.i("Main","用户离开屏目,由于用力滑一下,视力仍然滑动");
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("pic",R.mipmap.ic_launcher);
                map.put("text","新增项!!!!");
                dataList.add(map);
                simp_adapter.notifyDataSetChanged();//后台通知UI View
                break;
            case SCROLL_STATE_IDLE:
                Log.i("Main","视图已停止滑动了");
                break;
            case  SCROLL_STATE_TOUCH_SCROLL:
                Log.i("Main","手指没有离开屏目,视图正在滑动");
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
