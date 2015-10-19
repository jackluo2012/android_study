package net.webjoy.jackluo.android_webjoy_viewpage_anim;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jackluo on 10/18/15.
 */
public class ViewPagerWithTransformAnim extends ViewPager {
    /**
     * 获取 左边 右边 视图
     */
    private View mLeft;
    private View mRight;


    private float mTrans;
    private float mScale;

    /**
     *
     *
     */

    private Map<Integer,View> mChildren = new HashMap<Integer,View>();
    private static final float MIN_SCALE =0.6f;

    /**
     * 添加向左向右方法
     * @param view
     * @param position
     */
    public void setViewForPosition(View view,int position){
        mChildren.put(position, view);
    }

    public void removeViewFromPosition(Integer position){
        mChildren.remove(position);

    }


    public ViewPagerWithTransformAnim(Context context) {
        super(context);
    }


    public ViewPagerWithTransformAnim(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 获取 正在滚动
     * @param position
     * @param offset
     * @param offsetPixels
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {

        //Log.e("TAG","position = " + position +" ,offset = "+ offset +" ,offsetPixels = "+offsetPixels);
        mLeft  = mChildren.get(position);
        mRight = mChildren.get(position+1);

        animStack(mLeft,mRight,offset,offsetPixels);

        super.onPageScrolled(position, offset, offsetPixels);
    }

    private void animStack(View mLeft, View mRight, float offset, int offsetPixels) {
        if (mRight != null) {

            //从0页到1页,offset 0 ~ 1
            mScale = (1 - MIN_SCALE) * offset + MIN_SCALE;
            //从0 ~ 宽度的变化
            mTrans = -getWidth() - getPageMargin() + offsetPixels;

            ViewHelper.setScaleX(mRight, mScale);
            ViewHelper.setScaleY(mRight, mScale);

            //设置变化
            ViewHelper.setTranslationX(mRight, mScale);
        }

        if (mLeft != null){
            mLeft.bringToFront();//左边好始终显示 在前面
        }
    }

}
