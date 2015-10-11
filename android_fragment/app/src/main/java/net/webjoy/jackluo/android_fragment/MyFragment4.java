package net.webjoy.jackluo.android_fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jackluo on 9/3/15.
 */
public class MyFragment4 extends Fragment {
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

        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText("第二个Fragment");
        Log.i("Main", "Fragment2---oCreateView");

        return view;
    }

    /**
     * 当fragment 被添加到Activity时候会回调这个方法,并且只调用一次
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i("Main","Fragment1---onAttach()");
    }

    /**
     * 当创建fragment时会被回调,只会调用一次
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Main", "Fragment1---onCreate()");
    }

    /**
     * 当Fragment所在的Activity启动完成后调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("Main", "Fragment1---onActivityCreated()");
    }

    /**
     *  启动Fragment
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.i("Main", "Fragment1---onStart()");
    }


    /**
     * 恢复Fragment时会被回调,调用onStart()方法后面一定会调用onResume方法
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.i("Main", "Fragment1---onResume()");
    }

    /**
     * 暂停Fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.i("Main", "Fragment1---onPause()");
    }

    /**
     * 停止Fragment
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.i("Main", "Fragment1---onStop()");
    }

    /**
     * 销毁Fragment所包含的View组件时调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Main", "Fragment1---onDestroyView()");
    }

    /**
     * 销毁Fragment时会被调用
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Main", "Fragment1---onDestroy()");
    }

    /**
     * Fragment从Activity中删除时会调用的方法,并且这个方法只会调用一次
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Main", "Fragment1---onDetach()");
    }
}
