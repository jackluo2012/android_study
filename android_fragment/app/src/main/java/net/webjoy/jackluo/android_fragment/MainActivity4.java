package net.webjoy.jackluo.android_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jackluo on 9/3/15.
 */
public class MainActivity4 extends Activity implements MyFragment5.MyListener{
    private EditText editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main4);
        //动态处理fragment数据传递
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                MyFragment5 fragment5 = new MyFragment5();
                Bundle bundle = new Bundle();
                bundle.putString("name", text);
                fragment5.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction beginTransaction =  fragmentManager.beginTransaction();
                //动态设置tag
                beginTransaction.add(R.id.layout, fragment5, "fragment5");
                beginTransaction.commit();

                //Toast.makeText(this,"向Fragment发送数据 "+text,Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity4.this,"向Fragment发送数据 "+text,Toast.LENGTH_SHORT).show();
                //fragment5.s
            }
        });
        //静态处理fragment数据传递
        FragmentManager fragmentManager =  getFragmentManager();
        Fragment findFragment =  fragmentManager.findFragmentById(R.id.frag);
        MyFragment frag = (MyFragment) findFragment;
        frag.setAaa("fragment静态传值");

    }

    @Override
    public void thank(String code) {
        Toast.makeText(MainActivity4.this,"成功接收到"+code+",客气了!",Toast.LENGTH_SHORT).show();
    }
}
