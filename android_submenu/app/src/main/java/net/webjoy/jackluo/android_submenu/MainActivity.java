package net.webjoy.jackluo.android_submenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //动态添加
        SubMenu file =  menu.addSubMenu("文件");
        SubMenu edit = menu.addSubMenu("编辑");
        file.add(1,1,1,"新建");
        file.add(1,2,1,"打开");
        file.add(1,3,1,"保存");
        file.setHeaderTitle("文件操作"); //设置标题
        file.setHeaderIcon(R.mipmap.ic_launcher);//设置ICON
        edit.add(2, 1, 1, "复制");
        edit.add(2, 2, 1, "粘粘");
        edit.add(2,3,1,"剪切");
        edit.setHeaderTitle("编辑操作"); //设置标题
        edit.setHeaderIcon(R.mipmap.ic_launcher);//设置ICON

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (item.getGroupId()==1) {
            switch (id) {
                case 1:
                    Toast.makeText(this,"文件新建操作",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this,"文件打开操作",Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this,"文件保存操作",Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{
            switch (id) {
                case 1:
                    Toast.makeText(this, "编辑复制操作", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "编辑粘粘操作", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "编辑剪切操作", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
