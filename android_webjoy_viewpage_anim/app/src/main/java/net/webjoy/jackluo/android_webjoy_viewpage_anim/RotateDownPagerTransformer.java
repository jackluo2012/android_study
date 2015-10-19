package net.webjoy.jackluo.android_webjoy_viewpage_anim;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by jackluo on 10/18/15.
 */
public class RotateDownPagerTransformer implements ViewPager.PageTransformer {

    private static final float MAX_ROTATE = 20f;

    private float mRot;



    //A 页角度变化 0 ~ 20; B页角度变化 20 ~ 0;
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            //view.setAlpha(0);
            //改造支持3.0以下
            //不可见的时候
            //ViewHelper.setAlpha(view, 0);
            ViewHelper.setRotation(view,0);

        } else if (position <= 0) { // [-1,0]  A 页
            //0 ~ - 20
            mRot = position * MAX_ROTATE;

            //设置旋转中心
            ViewHelper.setPivotX(view,pageWidth/2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());
            ViewHelper.setRotation(view,mRot);

        } else if (position <= 1) { // (0,1] B 页
            //20 ~ 0
            mRot = position * MAX_ROTATE;

            //设置旋转中心
            ViewHelper.setPivotX(view,pageWidth/2);
            ViewHelper.setPivotY(view, view.getMeasuredHeight());
            ViewHelper.setRotation(view,mRot);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            //view.setAlpha(0);
            ViewHelper.setRotation(view, 0);
        }
    }
}
