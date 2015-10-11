package net.webjoy.jackluo.android_optionmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
       MenuItem item =      menu.add(1, 100, 1, "菜单一");
        item.setIcon(R.mipmap.ic_launcher);
        item.setTitle("aaaa");
        menu.add(1,101,1,"菜单二");
        menu.add(1,102,1,"菜单三");
        menu.add(1,103,1,"菜单四");
        menu.add(1,104,1,"菜单五");
        menu.add(1,105,1,"菜单六");
        menu.add(1,106,1,"菜单七");
        return true;
    }

    /**
     * 菜单选中
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            /*
            case R.id.action_menu_item1:
                Toast.makeText(this,"点击了菜单一",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_menu_item2:
                Toast.makeText(this,"点击了菜单二 ",Toast.LENGTH_SHORT).show();
                break;
                */

            case 100:
                Toast.makeText(this, "点击了菜单一 ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                item.setIntent(intent);

                break;
            case 101:
                Toast.makeText(this, "点击了菜单二 ", Toast.LENGTH_SHORT).show();
                break;
            case 103:
                Toast.makeText(this, "点击了菜单三 ", Toast.LENGTH_SHORT).show();
                break;
            case 104:
                Toast.makeText(this, "点击了菜单四 ", Toast.LENGTH_SHORT).show();
                break;
            case 105:
                Toast.makeText(this, "点击了菜单五 ", Toast.LENGTH_SHORT).show();
                break;
            case 106:
                Toast.makeText(this, "点击了菜单六 ", Toast.LENGTH_SHORT).show();
                break;
            case 107:
                Toast.makeText(this, "点击了菜单七 ", Toast.LENGTH_SHORT).show();
                break;


        }

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
