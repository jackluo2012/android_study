package net.webjoy.jackluo.android_webjoy_viewpage_anim;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.ViewPager.setPageTransformer 实现切换动画
 * 2.修改ViewPager内部代码 + 使用nineoldandroids代替属性动画实现下
 * 3.观察API的规律,自定义我们的切换动画
 * 4.自定义ViewPager实现动画切换效果
 * Translation  Scale
 *   a.拿到当前两个切换的view        -> 通过Map存储与获取
 *   b.我们需要动画的梯度值   ->  通过 offset , offsetPixels
 */

public class MainActivity extends AppCompatActivity {

   // private ViewPagerCompat viewPager;
    private ViewPagerWithTransformAnim viewPager;

    private int[] mImagesIds = new int[]{R.drawable.guide_image1,R.drawable.guide_image2,R.drawable.guide_image3};

    private List<ImageView> mImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setContentView(R.layout.main);
        //viewPager = (ViewPagerCompat) findViewById(R.id.id_viewpager);
        viewPager = (ViewPagerWithTransformAnim) findViewById(R.id.id_viewpager);
        //给viewPager添加切换动画效果
        //viewPager.setPageTransformer(true,new DepthPageTransformer());
        //viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
        //viewPager.setPageTransformer(true,new RotateDownPagerTransformer());
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImagesIds[position]);
                //主要是不要让它变型
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImages.add(imageView);
                viewPager.setViewForPosition(imageView,position);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mImages.get(position));
                viewPager.removeViewFromPosition(position);
            }

            @Override
            public int getCount() {
                return mImagesIds.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view==o;
            }
        });
    }
}
