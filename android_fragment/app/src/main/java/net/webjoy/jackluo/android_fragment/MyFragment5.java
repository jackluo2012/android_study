package net.webjoy.jackluo.android_fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jackluo on 9/3/15.
 */
public class MyFragment5 extends Fragment {

    private String code = "Thank you,Activity!!!";

    public MyListener myListener;

    public interface MyListener{
        public void thank(String code);
    }

    @Override
    public void onAttach(Activity activity) {
        myListener = (MyListener) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //layout布局文件转换成View对象
        /**
         * resource:Fragment需要加载的布局文件
         * root: 加载Layout的父ViewGroup
         * attactToRoot:false,不返回父ViewGroup
         */
        View view =  inflater.inflate(R.layout.fragment2, container, false);

        TextView tv = (TextView) view.findViewById(R.id.text);
        String text = getArguments().get("name")+"";
        Toast.makeText(getActivity(),"已成功接收到"+text,Toast.LENGTH_SHORT).show();
        tv.setText(text);
        Toast.makeText(getActivity(),"向Activity中发送"+code,Toast.LENGTH_SHORT).show();
        myListener.thank(code);
//        text.setText("第一个Fragment");
//        Log.i("Main", "Fragment1---oCreateView");

        return view;

    }
}
