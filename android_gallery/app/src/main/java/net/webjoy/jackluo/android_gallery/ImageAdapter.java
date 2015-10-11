package net.webjoy.jackluo.android_gallery;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by jackluo on 9/5/15.
 * 自定义适配器
 */
public class ImageAdapter extends BaseAdapter {

    private int[]res;
    private Context context;
    /**
     * 创建构造方法传入参数
     */
    public ImageAdapter(int []res,Context context){
        this.res = res;
        this.context = context;
    }

    /**
     * 返回已定义的数据总数量
     * 返回数据源的数量
     * @return
     */
    @Override
    public int getCount() {
        //设置一直循环
        return Integer.MAX_VALUE;
        //return res.length;
    }

    /**
     * 告诉适配器取得目前容器的数据ID和对象
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return res[position];
    }

    /**
     * 告诉适配器取得目前容器的数据ID和对象
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 取得目前欲显示 的图像View,传入数组ID值使之读取与成像
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("Main","position="+position+" res的角标"+position%res.length);

        ImageView image = new ImageView(context);
        //设置一直循环
        //image.setBackgroundResource(res[position]);
        image.setBackgroundResource(res[position%res.length]);


        image.setLayoutParams(new Gallery.LayoutParams(200,150));//缩略图大小
        image.setScaleType(ImageView.ScaleType.FIT_XY);//横向的拉伸

        return image;
    }
}
