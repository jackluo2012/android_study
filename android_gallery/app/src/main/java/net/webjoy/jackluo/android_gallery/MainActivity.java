package net.webjoy.jackluo.android_gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,ViewSwitcher.ViewFactory {

    /**
     * 准备数据源
     */
    private int[] res = {R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,
            R.drawable.item5,R.drawable.item6,R.drawable.item7,R.drawable.item8,
            R.drawable.item9,R.drawable.item10,R.drawable.item11,R.drawable.item12};
    private ImageAdapter imageAdapter;
    private Gallery gallery;
    private ArrayAdapter adapter;
    private SimpleAdapter simpleAdapter;
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gallery = (Gallery) findViewById(R.id.gallery);

        imageAdapter = new ImageAdapter(res,this);
        //加载 适配器
        gallery.setAdapter(imageAdapter);

        gallery.setOnItemSelectedListener(this);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.is);
        imageSwitcher.setFactory(this);
        //淡入动画
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
        //淡出动画
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_in));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //image.setBackgroundResource(res[position%res.length]);
        imageSwitcher.setBackgroundResource(res[position%res.length]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public View makeView() {
        ImageView image =  new ImageView(this);
        //等比例缩放 并保持水平居中
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return image;
    }
}
