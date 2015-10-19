package net.webjoy.jackluo.android_webjoy_viewpage_anim;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

public class DepthPageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        //查看动画的实现
        /**
         * A 页切换到B页
         * A 页的position  0 ~ -1
         * B 页的position 1 ~ 0
         */


        Log.e("TAG","view = "+view +" ,position = "+position);

        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            //view.setAlpha(0);
            //改造支持3.0以下
            ViewHelper.setAlpha(view,0);

        } else if (position <= 0) { // [-1,0]  A 页

            // Use the default slide transition when moving to the left page
            //view.setAlpha(1);
            ViewHelper.setAlpha(view, 1);
            //view.setTranslationX(0);
            ViewHelper.setTranslationX(view,0);
            //view.setScaleX(1);
            ViewHelper.setScaleX(view,1);
            //view.setScaleY(1);
            ViewHelper.setScaleY(view,1);

        } else if (position <= 1) { // (0,1] B 页
            // Fade the page out.
            //view.setAlpha(1 - position);
            ViewHelper.setAlpha(view,1-position); //位置是0

            // Counteract the default slide transition
            //view.setTranslationX(pageWidth * -position);
            ViewHelper.setTranslationX(view,pageWidth* -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            //view.setScaleX(scaleFactor);
            ViewHelper.setScaleX(view, scaleFactor);
            //view.setScaleY(scaleFactor);
            ViewHelper.setScaleY(view,scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            //view.setAlpha(0);
            ViewHelper.setAlpha(view,0);
        }
    }
}