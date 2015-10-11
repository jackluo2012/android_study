package net.webjoy.jackluo.android_contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by jackluo on 9/14/15.
 */
public class MyContentProvider extends ContentProvider {


    @Override //在ContentProvider创建后调用
    public boolean onCreate() {
        return false;
    }

    @Override //根据Uri查询出selection指定的条件所有匹配的全部记录,并且可以指定查询哪些列 以什么方式(Order)排序
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override //返回当前Urir的MIME类型,如果该URI对应的多条记录
    //如果 MIME类型字符串 就是以vnd.android.dir/开头
    //如果 URI对应的数据只有一条记录该MIN类型就是以vnd.android.cursor.item/开头
    public String getType(Uri uri) {
        return null;
    }

    @Override   //根据Uri 插入Values对应的数据
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override //根据URi删除selection指定的条件所匹配的全部记录
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override //根据Uri修改selection指定的条件所匹配的全部记录
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
