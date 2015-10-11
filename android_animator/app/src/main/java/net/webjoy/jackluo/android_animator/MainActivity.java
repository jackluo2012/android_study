package net.webjoy.jackluo.android_animator;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] res = {R.id.imageView_a,R.id.imageView_b,R.id.imageView_c,
                            R.id.imageView_d,R.id.imageView_f,R.id.imageView_g,R.id.imageView_h};

    private List<ImageView> imageViewList = new ArrayList<ImageView>();

    private Boolean flage  = true;

    private Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        for (int i = 0; i< res.length;i++){
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);

            imageViewList.add(imageView);
        }

        button = (Button) findViewById(R.id.button);
    }

    /**
     * 获取 每一个值的尺度
     */
    public void ValueAnimator(){
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setText("" + value);
            }
        });
        animator.start();
    }

    /**
     * 自定义数值
     */
    public void ValueAnimator2(){
        ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                return null;
            }
        });
    }

    @Override
    public void onClick(View v) {

        Log.d("info", v.getId() + "");
        switch (v.getId()){
            case R.id.imageView_a:
                if (flage) {
                    startAnim();
                    flage = false;
                }else {
                    closeAnim();
                    flage = true;
                }
                break;
            case R.id.button:
                ValueAnimator();
                break;
            default:
                Toast.makeText(this,"click"+v.getId(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 回收菜单
     */
    private void closeAnim() {
        for (int i =1;i<res.length;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",i*180,0F);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
        }
    }

    /**
     * 展开菜单
     */
    private void startAnim() {
        for (int i =1;i<res.length;i++){
            ObjectAnimator animator = ObjectAnimator.ofFloat(imageViewList.get(i),"translationY",0F,i*180);
            animator.setDuration(500);
            animator.setInterpolator(new BounceInterpolator());
            animator.setStartDelay(i*300);
            animator.start();
        }
    }
}
