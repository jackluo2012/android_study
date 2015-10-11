package net.webjoy.jackluo.sqlitedemo1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //每个程序  都有自己的数据库,默认情况下互不干扰
        //没有用户的概念
        //创建一个数据 库并且打开 只创建一次

        SQLiteDatabase db =  openOrCreateDatabase("stu.db",MODE_PRIVATE,null);
        db.execSQL("Create Table if not exists stutb(_id integer primary key autoincrement,name text not null,sex text not null,age integer not null)");
        //db.insert("stutb");
        ContentValues values = new ContentValues();
        values.put("name","张三");
        values.put("sex", "男");
        values.put("age", 19);
        long rowId = db.insert("stutb",null,values);
        values.clear();
        values.put("name", "李四");
        values.put("sex", "男");
        values.put("age", 29);
         db.insert("stutb", null, values);
        values.clear();
        values.put("name", "王五");
        values.put("sex", "男");
        values.put("age", 25);
        db.insert("stutb", null, values);
        values.clear();
        values.put("name", "张三疯");
        values.put("sex", "男");
        values.put("age", 28);
        db.insert("stutb", null, values);
        values.clear();
        values.put("sex", "女");
        db.update("stutb", values, "_id>?", new String[]{"3"});//将全部ID==3的改成女
        values.clear();
        db.delete("stutb", "name like ?", new String[]{"%三%"});//删除所有名字中带有三的人
        Cursor c = db.query("stutb", null, "_id>?", new String[]{"0"}, null, null, "name");
        if (c !=null) {
            String[] columns=  c.getColumnNames();//查出 所有的列名
            while (c.moveToNext()){
                for (String column :columns ){
                    Log.d("info",c.getString(c.getColumnIndex(column)));
                }

            }
            c.close();

        }
        db.close();

        //源生态处理
/*
        SQLiteDatabase db =  openOrCreateDatabase("user.db",MODE_PRIVATE,null);
        db.execSQL("Create Table if not exists usertb(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
        db.execSQL("Insert Into usertb (name,age,sex) values('张三',26,'男')");
        db.execSQL("Insert Into usertb (name,age,sex) values('李四',23,'男')");
        db.execSQL("Insert Into usertb (name,age,sex) values('王五',24,'男')");
        db.execSQL("Insert Into usertb (name,age,sex) values('小明',26,'男')");

        //查询 出所有的数据
        Cursor c =  db.rawQuery("SELECT * FROM usertb", null);
        if (c !=null){
            while (c.moveToNext()){
                Log.d("info", "_id:"+ c.getInt(c.getColumnIndex("_id")));
                Log.d("info","name:"+c.getString(c.getColumnIndex("name")));
                Log.d("info", "_id:"+ c.getInt(c.getColumnIndex("_id")));
                Log.d("info","sex:"+c.getString(c.getColumnIndex("sex")));
                Log.d("info","!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }

//            c.moveToFirst();
            c.close();

        }
*/
        db.close();
    }
    public void sqliteHelper(){
        DBOpenHelper helper = new DBOpenHelper(MainActivity.this,"stu.db");
        //helper.getReadableDatabase();//只能读不能更新
        SQLiteDatabase db = helper.getWritableDatabase();
        //db.query() 得到游标创建
        Cursor c = db.rawQuery("SELECT * FROM stutb", null);//获取 游标
        if (c != null){
            String[] columns=  c.getColumnNames();//查出 所有的列名
            while (c.moveToNext()){
                for (String column :columns ){
                    Log.d("info",c.getString(c.getColumnIndex(column)));
                }

            }
            c.close();
        }

    }
}
