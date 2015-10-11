package net.webjoy.jackluo.android_viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jackluo on 9/4/15.
 */
public class MyPagerAdapter extends PagerAdapter{


    /**
     * 构造方法
     */
    private List<View> viewList;
    private List<String> titleList;

    public MyPagerAdapter(List<View> viewList,List<String> titleList){
        this.viewList = viewList;
        this.titleList = titleList;
    }
    /**
     * 返回的是页卡的数量
     * @return
     */
    @Override
    public int getCount() {
        return viewList.size();
    }


    /**
     * View 是否来自对象
     * @param view
     * @param o
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view==o;
    }

    /**
     * 实例化一个卡
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
          //  return super.instantiateItem(container, position);
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    /**
     * 销毁一个页卡
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(viewList.get(position));
    }


    /**
     * 设置viewPager页卡的 标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
