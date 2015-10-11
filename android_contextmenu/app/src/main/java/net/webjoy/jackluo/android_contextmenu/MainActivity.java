package net.webjoy.jackluo.android_contextmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showListView();
    }

    /**
     * 设置listView的显示内容
     */
    private void showListView(){
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData());
        listView.setAdapter(adapter);
        this.registerForContextMenu(listView);//注册上下文菜单
    }

    /**
     * 创建上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //设置menu显示的内容
        menu.setHeaderTitle("文件操作");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
//        menu.add(1, 1, 1, "复制");
//        menu.add(1,2,1,"粘粘");
//        menu.add(1,3,1,"剪切");
//        menu.add(1,4,1,"重命名");
//        menu.add(1,2,1,"删除");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

    }

    /**
     * 设置菜单项的点击事件
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 1:
                Toast.makeText(this,"选择了复制",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"选择了粘粘",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"选择了剪切",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"选择了重命名",Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this,"选择了删除",Toast.LENGTH_SHORT).show();
                break;
            case 6:
                break;
        }

        return super.onContextItemSelected(item);
    }

    /**
     * 构造返回的数据
     * @return
     */
    private ArrayList<String> getData(){
        ArrayList<String> list = new ArrayList<String>();
        for (int i=0;i<6;i++){
            list.add("文件"+(i+1));
        }
        return list;
    }
}
