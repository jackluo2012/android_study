package net.webjoy.jackluo.android_viewflipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    private ViewFlipper viewFlipper;
    private int[]resId = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};
    private float startX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        viewFlipper = (ViewFlipper) findViewById(R.id.flipper);
        //动态导入的方式为ViewFlipper加入子View
        for (int i = 0;i < resId.length;i++){
            viewFlipper.addView(getImageView(resId[i]));
        }

/*        //取消自动播放
        //进入动画 添加动画效果
        viewFlipper.setInAnimation(this,R.anim.left_in);
        //出入动画
        viewFlipper.setOutAnimation(this,R.anim.left_out);
        //切换时间 设置为我切换的时间间隔
        viewFlipper.setFlipInterval(3000);
        //开始动画
        viewFlipper.startFlipping();
*/

    }
    /**
     * 监控手势
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            //手指落下
            case MotionEvent.ACTION_DOWN:{
                //获得点击的X
                startX = event.getX();

                break;
            }
            //手指滑动
            case MotionEvent.ACTION_MOVE:{
                //向右滑动 看前一页
                if (event.getX()- startX > 100 ){
                    viewFlipper.setInAnimation(this,R.anim.left_in);
                    viewFlipper.setOutAnimation(this,R.anim.left_out);
                    viewFlipper.showPrevious();//显示 前一页
                }
                //向左滑动
                if (startX - event.getX()>100){
                    viewFlipper.setInAnimation(this,R.anim.right_in);
                    viewFlipper.setOutAnimation(this,R.anim.right_out);
                    viewFlipper.showNext();
                }
                break;
            }
            //手指离开
            case MotionEvent.ACTION_UP:{
                break;
            }
        }



        return super.onTouchEvent(event);
    }

    /**
     * 返回 图片View
     * @param resId
     * @return
     */
    private ImageView getImageView(int resId){
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource(resId);
        imageView.setBackgroundResource(resId);
        return imageView;
    }

}
