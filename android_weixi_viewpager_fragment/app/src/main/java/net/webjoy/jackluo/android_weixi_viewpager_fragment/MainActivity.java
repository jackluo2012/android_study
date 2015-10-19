package net.webjoy.jackluo.android_weixi_viewpager_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPager;

    private FragmentPagerAdapter mAdapter;

    private List<Fragment> mFragments;

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvents();
        setSelect(2);
    }

    /**
     * 初始化事件
     */
    private void initEvents() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);
   }
    private void setSelect(int i){
        setTab(i);
        viewPager.setCurrentItem(i);
    }

    private void setTab(int i) {
        //让所有图片变
        resetImage();

        //把图片设置成亮的,切换内容区域
        switch (i){
            case 0:

                mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:

                mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:

                mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:

                mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }
    }

    /**
     * 初始化视图
     */
    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.id_viewpager);

        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mFrdImg  = (ImageButton)findViewById(R.id.id_tab_frd_img);
        mAddressImg =(ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton)findViewById(R.id.id_tab_setting_img);

        mFragments = new ArrayList<Fragment>();
        Fragment wexinFragment = new WeixinFragment();
        Fragment frdFragment = new FrdFragment();
        Fragment addressFragment = new AddressFragment();
        Fragment settingFragment = new SettingFragment();
        mFragments.add(wexinFragment);
        mFragments.add(frdFragment);
        mFragments.add(addressFragment);
        mFragments.add(settingFragment);

        //传入一个FragmentManger
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };


        viewPager.setAdapter(mAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int currentPage = viewPager.getCurrentItem();
                setTab(currentPage);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.id_tab_weixin:

                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);

                break;
            case R.id.id_tab_address:
                setSelect(2);

                break;
            case R.id.id_tab_setting:
                setSelect(3);

                break;
        }
    }
    /**
     * 将所有的图片切换为暗色的
     */
    private void resetImage() {
        mWeixinImg.setImageResource(R.drawable.tab_weixin_normal);
        mFrdImg.setImageResource(R.drawable.tab_find_frd_normal);
        mAddressImg.setImageResource(R.drawable.tab_address_normal);
        mSettingImg.setImageResource(R.drawable.tab_settings_normal);
    }
}
