package net.webjoy.jackluo.sharedpreferencesdemo1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    CheckBox chk;
    Button login,btncancel;
    SharedPreferencesCompat spfc;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    SharedPreferencesCompat.EditorCompat editorCompat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

/*
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences pref = getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor =     pref.edit();
        editor.putString("name","张三");
        editor.putInt("age", 30);
        editor.putLong("time", System.currentTimeMillis());
        editor.commit();
*/
        //登陆
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        chk = (CheckBox) findViewById(R.id.chkSaveName);
        login = (Button) findViewById(R.id.login);
        btncancel = (Button) findViewById(R.id.cancel);
//        spfc = (SharedPreferencesCompat) getSharedPreferences("UserInfo", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
//        editorCompat = (SharedPreferencesCompat.EditorCompat) sharedPreferences.edit();
        editor = sharedPreferences.edit();
        String name = sharedPreferences.getString("userName","");
        if (name==null){
            chk.setChecked(false);
        }else {
            chk.setChecked(true);
            username.setText(name);
        }

    }
    public void doClick(View v ){
        switch (v.getId()){
            case R.id.login:

                String name = username.getText().toString().trim();
                String passwd = password.getText().toString().trim();
                if ("admin".equals(name) && "luo.com".equals(passwd)){
                    if (chk.isChecked()){
                        editor.putString("userName", name);
                        editor.commit();
                    }else {
                        editor.remove("userName");
                        editor.commit();
                    }
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"禁止登陆",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.cancel:


                break;
        }
    }

}
