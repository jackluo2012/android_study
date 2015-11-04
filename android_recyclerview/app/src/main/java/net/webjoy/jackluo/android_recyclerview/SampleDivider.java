package net.webjoy.jackluo.android_recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jackluo on 10/31/15.
 * 定义分割条
 */
public class SampleDivider extends RecyclerView.ItemDecoration {

    public SampleDivider(Context context) {
        //装载
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        //获取系统提供的分割条Drawable对象
        mDivider = a.getDrawable(0);
        a.recycle();//回收资源的空间
    }

    //系统默认资源的分割条
    private static final int[] ATTRS = {android.R.attr.listDivider};

    //订义一个存储分割条的对象 Drawable
    private Drawable mDivider;

    /**
     * 在方法绘制所有的分割条
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //获取列表项距离左边缘的距离
        int left = parent.getPaddingLeft();

        //获取列表项距离右边 距离
        int right = parent.getWidth() - parent.getPaddingRight();

        //获取 列表项的总数
        int childerCount =parent.getChildCount();

        //开始绘制所有列表项中的分割线
        for (int i=0;i<childerCount;i++){
            //  获取当前的列表项
            View childer = parent.getChildAt(i);

            //获取当前列表项的参数信息
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childer.getLayoutParams();

            //计算分割条左上角的纵坐标
            int top = childer.getBottom() + params.bottomMargin;

            //计算分割条右下角的纵坐标
            int bottom = top + mDivider.getIntrinsicHeight();

            //设置分割条的绘制的位置
            mDivider.setBounds(left,top,right,bottom);

            //开始绘制当前列表项下方的分割条
            mDivider.draw(c);
        }

        super.onDrawOver(c, parent, state);
    }
}
