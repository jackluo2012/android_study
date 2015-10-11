package net.webjoy.jackluo.android_contentprovider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WriteContact();

    }
    /**
     * 写入联系人
     */
    public void WriteContact(){
        ContentResolver cr = getContentResolver();
        //向联系人中插入一行数据
        ContentValues values = new ContentValues();
        //
        Uri uri = cr.insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long raw_contact_id = ContentUris.parseId(uri);
        values.clear();
        //插入人名数据
        values.put(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID,raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,"jackluo");
        values.put(ContactsContract.CommonDataKinds.StructuredName.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        uri = cr.insert(ContactsContract.Data.CONTENT_URI,values);
        //插入电话信息
        values.put(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,raw_contact_id);
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"132228191831");
        values.put(ContactsContract.CommonDataKinds.Phone.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        uri = cr.insert(uri,values);
    }


    /**
     * 读取联系人信息
     */
    public void ReadContact(){
        ContentResolver cr = getContentResolver();
        //要查询 URI  ,要做
        Cursor c =  cr.query(ContactsContract.Contacts.CONTENT_URI, new String[]{ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
        if (c !=null){
            while (c.moveToNext()){
                int id = c.getInt(c.getColumnIndex("_id"));
                Log.i("info", "_id:" + id);
                Log.i("info","name:"+c.getString(c.getColumnIndex("display_name")));
                //根据联系人的ID查询出联系人的电话号码
                //查询手机号码URI,查询字段
                Cursor c1 = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE}, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                if (c1 !=null){
                    while (c1.moveToNext()){
                        int type = c1.getInt(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        if (type== ContactsContract.CommonDataKinds.Phone.TYPE_HOME){
                            Log.i("info","家庭电话:"+c1.getString(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        }else if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE){
                            Log.i("info","手机:"+c1.getString(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                        }
                    }
                    c1.close();
                }

                //根据联系人的ID查询出联系人的邮箱
                Cursor c2 = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.Email.TYPE}, ContactsContract.CommonDataKinds.Email.CONTACT_ID+"="+id,null,null);
                if (c2!=null){
                    while (c2.moveToNext()){
                        int type = c2.getInt(c2.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                        if (type == ContactsContract.CommonDataKinds.Email.TYPE_WORK){
                            Log.i("info","工作邮箱:"+c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                        }
                    }
                    c2.close();
                }
            }
            c.close();
        }
    }
}
