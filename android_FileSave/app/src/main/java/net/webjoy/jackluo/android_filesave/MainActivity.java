package net.webjoy.jackluo.android_filesave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText editText,readText;
    Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //new File("/mnt/extsdcard/test");
        //创建文件
        /*
        File file = new File("/mnt/sdcard/test");

        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            file.delete();
            Toast.makeText(MainActivity.this,"文件已存在",Toast.LENGTH_SHORT).show();
        }
        */
        //获取文件目录
/*
        File file = this.getFilesDir();//获取应用程序的默认存储目录
        Log.i("info", String.valueOf(file));
*/
        //把不是很重要的文件存储到这里,使用
        //如果手机内存不足的时候,系统自动去删除 APP的cache目录数据
/*
        File file = this.getCacheDir();//获取应用程序的默认缓存存储目录
        Log.i("info", String.valueOf(file));
*/
        //自定义目录
        // /data/data/<包名>/app_imooc
/*
        File file = this.getDir("imooc", MODE_PRIVATE);
        Log.i("info", String.valueOf(file));
*/
        //可以得到外部的存储位置 该位置的数据跟内置的使用一样的
        //如果APP卸载了这里面的数据也会自动清除掉
/*
        File file = this.getExternalCacheDir();
        Log.i("info",file.toString());
*/
        //如果 开发者不遵守的规则,不把数据 放到/data/data<包名>/
        // /mnt/sdcard/Android/data/<包名
        //卸载之后数据 会造成很多数据 垃圾


        //读取数据
        editText = (EditText) findViewById(R.id.editText);
        readText = (EditText) findViewById(R.id.readText);
        sendbtn = (Button) findViewById(R.id.send);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteFiles(editText.getText().toString());
                readText.setText(readFiles());
            }
        });
    }
    //保存文件内容
    public void WriteFiles(String content){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("a.txt",MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取文件内容
    }
    //读取文件内容
    public String readFiles(){
        String content = null;
        FileInputStream fis = null;
        try {
            fis = openFileInput("a.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer =  new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            content = baos.toString();
            fis.close();
            baos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ByteArrayInputStream bais = new ByteArrayInputStream();
        return content;
    }

}
