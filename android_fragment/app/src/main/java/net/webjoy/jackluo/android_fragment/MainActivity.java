package net.webjoy.jackluo.android_fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        group = (RadioGroup) findViewById(R.id.radiogroup);
        group.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.first: {
                //跳转到activity 2中
                Intent intent = new Intent(this,MainActivity2.class);
                startActivity(intent);

                break;
            }
            case R.id.second: {
                MyFragment2 myFragment2 = new MyFragment2();
                FragmentManager fragmentManager =  getFragmentManager();
                FragmentTransaction beginTransaction  = fragmentManager.beginTransaction();
                beginTransaction.add(R.id.frame,myFragment2);

                beginTransaction.addToBackStack(null);

                beginTransaction.commit();


                break;
            }
            case R.id.thrid: {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);

                break;
            }
            case R.id.fourth: {
                Intent intent = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent);
                break;
            }
        }
    }
}
