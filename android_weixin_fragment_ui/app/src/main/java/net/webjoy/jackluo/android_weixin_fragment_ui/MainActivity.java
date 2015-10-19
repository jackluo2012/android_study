package net.webjoy.jackluo.android_weixin_fragment_ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;

    private ImageButton mWeixinImg;
    private ImageButton mFrdImg;
    private ImageButton mAddressImg;
    private ImageButton mSettingImg;


    private Fragment mTab01;
    private Fragment mTab02;
    private Fragment mTab03;
    private Fragment mTab04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvents();
        setSelect(0);
    }

    /**
     * 初始化视图
     */
    private void initView() {


        mTabWeixin = (LinearLayout) findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout) findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout) findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout) findViewById(R.id.id_tab_setting);

        mWeixinImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
        mFrdImg  = (ImageButton)findViewById(R.id.id_tab_frd_img);
        mAddressImg =(ImageButton) findViewById(R.id.id_tab_address_img);
        mSettingImg = (ImageButton)findViewById(R.id.id_tab_setting_img);


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

        //拿到Fragment 管理器
        FragmentManager fragmentManager =  getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();//开启事物
        //隐藏所有的Fragment
        hideFragment(transaction);
        //把图片设置成亮的,切换内容区域
        switch (i){
            case 0:
                if (mTab01 == null){
                    mTab01 = new WeixinFragment();
                    transaction.add(R.id.id_content,mTab01);
                }else{
                    transaction.show(mTab01);
                }
                mWeixinImg.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                if (mTab02 == null){
                    mTab02 = new FrdFragment();
                    transaction.add(R.id.id_content,mTab02);
                }else{
                    transaction.show(mTab02);
                }
                mFrdImg.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                if (mTab03 == null){
                    mTab03 = new AddressFragment();
                    transaction.add(R.id.id_content,mTab03);
                }else{
                    transaction.show(mTab03);
                }
                mAddressImg.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                if (mTab04 == null){
                    mTab04 = new SettingFragment();
                    transaction.add(R.id.id_content,mTab04);
                }else{
                    transaction.show(mTab04);
                }
                mSettingImg.setImageResource(R.drawable.tab_settings_pressed);
                break;
        }
        transaction.commit();//提交事物
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTab01 !=null){
            transaction.hide(mTab01);
        }
        if (mTab02 !=null){
            transaction.hide(mTab02);
        }
        if (mTab03 !=null){
            transaction.hide(mTab03);
        }
        if (mTab04 !=null){
            transaction.hide(mTab04);
        }

    }

    @Override
    public void onClick(View v) {
        //让所有图片变
        resetImage();
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
