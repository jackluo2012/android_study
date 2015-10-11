package net.webjoy.jackluo.android_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jackluo on 9/3/15.
 */
public class MyFragment extends Fragment {


    public String getAaa() {
        return aaa;
    }

    public void setAaa(String aaa) {
        this.aaa = aaa;
    }

    private String aaa;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //layout布局文件转换成View对象
        /**
         * resource:Fragment需要加载的布局文件
         * root: 加载Layout的父ViewGroup
         * attactToRoot:false,不返回父ViewGroup
         */
        View view =  inflater.inflate(R.layout.fragment, container, false);

        TextView text = (TextView) view.findViewById(R.id.text);
        Button button = (Button) view.findViewById(R.id.button);
        button.setText("获取内容");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = getAaa();
                Toast.makeText(getActivity(),val,Toast.LENGTH_SHORT).show();
            }
        });
        text.setText("静态加载Fragment");


        return view;
    }
}
