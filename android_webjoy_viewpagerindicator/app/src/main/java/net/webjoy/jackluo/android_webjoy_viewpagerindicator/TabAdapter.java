package net.webjoy.jackluo.android_webjoy_viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jackluo on 10/17/15.
 */
public class TabAdapter extends FragmentPagerAdapter {

    public static String[] TITLES = new String[]{"课程","问答","求课","学习","计划"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        TabFragment fragment = new TabFragment(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
