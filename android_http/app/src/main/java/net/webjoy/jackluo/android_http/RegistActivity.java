package net.webjoy.jackluo.android_http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistActivity extends AppCompatActivity {


    private EditText userName;
    private EditText age;
    private Button submit;

    //新建注册页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userName = (EditText) findViewById(R.id.UserName);
        age = (EditText) findViewById(R.id.age);
        submit = (Button) findViewById(R.id.submit_send);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.0.107:8080/";
                //new HttpThreadRegister(url,userName.getText().toString(),age.getText().toString()).start();
                //url = url +"?name="+userName.getText().toString() +"&age="+age.getText().toString();
                new HttpClientThread(url,userName.getText().toString(),age.getText().toString()).start();
            }
        });
    }


}
