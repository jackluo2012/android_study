package net.webjoy.jackluo.topbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by jackluo on 10/20/15.
 */
public class Topbar extends RelativeLayout {

    private Button leftButton,rightButton;
    private TextView tvTitle;

    private int leftTextColor;
    private Drawable leftBackground;
    private String leftTitle;

    private int rightTextColor;
    private Drawable rightBackground;
    private String rightTitle;

    private float titleTextSize;
    private int titleTextColor;
    private String title;

    private LayoutParams leftParams,rightParams,titleParams;


    private topbarClickListener listener;

    /**
     * 创建一个接口
     */
    public interface topbarClickListener{
        public void leftClick();
        public void rightClick();
    }


    /**
     * //暴露一个方法给调用者
     * 做映射
     * @param listener
     */
    public void setOnTopbarClickListener(topbarClickListener listener){
        this.listener = listener;
    }


    //重写了构造方法

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Topbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //包含了所有的属性和映射
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
                         //第二个是默认参数
         leftTextColor = ta.getColor(R.styleable.Topbar_leftTextColor, 0);
         leftBackground = ta.getDrawable(R.styleable.Topbar_leftBackground);
         leftTitle = ta.getString(R.styleable.Topbar_leftTitle);

        rightTextColor = ta.getColor(R.styleable.Topbar_rightTextColor, 0);
        rightBackground = ta.getDrawable(R.styleable.Topbar_rightBackground);
        rightTitle = ta.getString(R.styleable.Topbar_rightTitle);

        titleTextSize = ta.getDimension(R.styleable.Topbar_barTextSize, 0);

        titleTextColor = ta.getColor(R.styleable.Topbar_barTextColor, 0);
        title  = ta.getString(R.styleable.Topbar_bartitle);

        ta.recycle();//将值回收,缓存 浪费资源

        leftButton = new Button(context);
        rightButton = new Button(context);
        tvTitle = new TextView(context);

        leftButton.setTextColor(leftTextColor);
        leftButton.setBackground(leftBackground);
        leftButton.setText(leftTitle);

        rightButton.setTextColor(rightTextColor);
        rightButton.setBackground(rightBackground);
        rightButton.setText(rightTitle);
        
        tvTitle.setText(title);
        tvTitle.setTextColor(titleTextColor);
        tvTitle.setTextSize(titleTextSize);
        tvTitle.setGravity(Gravity.CENTER);//设置文字居中显示

        setBackgroundColor(0xFFF59563);
        //定义宽高 ---->>>> 属性
        leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //完成了一个控件的添加
        addView(leftButton, leftParams);

        //定义宽高 ---->>>> 属性
        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,TRUE);
        //完成了一个控件的添加
        addView(rightButton,rightParams);


        //定义宽高 ---->>>> 属性
        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,TRUE);
        //完成了一个控件的添加
        addView(tvTitle,titleParams);


        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "net.webjoy LeftButton", Toast.LENGTH_SHORT).show();
                listener.leftClick();
            }
        });
        rightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "net.webjoy RightButton", Toast.LENGTH_SHORT).show();
                listener.rightClick();
            }
        });
    }
    public void setLeftIsvisable(boolean flag){
        if (flag){
            leftButton.setVisibility(View.VISIBLE);
        }else{
            leftButton.setVisibility(View.GONE);
        }
    }
}
