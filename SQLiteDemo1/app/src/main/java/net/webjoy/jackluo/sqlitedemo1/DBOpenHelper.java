package net.webjoy.jackluo.sqlitedemo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jackluo on 9/13/15.
 */
public class DBOpenHelper extends SQLiteOpenHelper {


    public DBOpenHelper(Context context, String name) {
        super(context, name, null, 1);
    }
/*
    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }
*/
    //首次创建数据库的时候调用一般可以把建表的操作
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table if not exists stutb(_id integer primary key autoincrement,name text not null,sex text not nll,age integer not null)");
        db.execSQL("insert into stutb (name,sex,age) values ('张三','女',18)");
    }

    //当数据库的版本发生变化的时候,会自动执行
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
